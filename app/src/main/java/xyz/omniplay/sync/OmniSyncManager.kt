package xyz.omniplay.sync

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.PowerManager
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.PlaybackParameters
import androidx.media3.common.Player
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.extractor.DefaultExtractorsFactory
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.json.JSONObject
import xyz.omniplay.model.Song
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.Inet4Address
import java.net.InetSocketAddress
import java.net.Socket
import java.net.URL
import java.net.URLEncoder
import java.util.UUID
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.math.abs

/**
 * OmniSync Manager:
 * Handles local network music streaming orchestration for Host and Listener modes.
 * - Host: Embedded HTTP server (port 8998) streaming audio with Range support and broadcasting SSE events.
 * - Listener: Discovers hosts via mDNS/HTTP probe, connects via HTTP (port 8998), and streams synchronously.
 */
class OmniSyncManager private constructor(private val context: Context) {

    companion object {
        const val PORT = 8998
        const val CONTROL_PORT = 8998
        const val STREAM_PORT = 8998
        const val SERVICE_TYPE = "_omnisync._tcp"

        @Volatile
        private var instance: OmniSyncManager? = null

        fun getInstance(context: Context): OmniSyncManager {
            return instance ?: synchronized(this) {
                instance ?: OmniSyncManager(context.applicationContext).also { instance = it }
            }
        }
    }

    interface OmniSyncListener {
        fun onRoleChanged(role: OmniSyncRole)
        fun onHostsDiscovered(hosts: List<OmniSyncHost>)
        fun onPeersChanged(peers: List<OmniSyncPeer>)
        fun onTrackInfoChanged(title: String, artist: String)
        fun onTrackAudioInfoChanged(format: String, quality: String, isHiRes: Boolean) {}
        fun onPlaybackStateChanged(isPlaying: Boolean)
        fun onError(message: String)
        fun onProgressUpdate(currentPositionMs: Long, durationMs: Long) {}
        fun onLatencyUpdate(latencyMs: Long) {}
    }

    init {
        try {
            System.setProperty("http.keepAlive", "false")
        } catch (ignored: Exception) {}
    }

    private val listeners = CopyOnWriteArrayList<OmniSyncListener>()
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val mainHandler = Handler(Looper.getMainLooper())

    private val nsdManager by lazy { context.getSystemService(Context.NSD_SERVICE) as NsdManager }
    private val wifiManager by lazy { context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager }
    private val connectivityManager by lazy { context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager }
    private val powerManager by lazy { context.applicationContext.getSystemService(Context.POWER_SERVICE) as PowerManager }

    private var multicastLock: WifiManager.MulticastLock? = null
    private var wifiLock: WifiManager.WifiLock? = null
    private var wakeLock: PowerManager.WakeLock? = null

    val streamServer = OmniSyncStreamServer(context, PORT)

    var hostSongProvider: (() -> Song?)? = null
    var hostPlaybackPositionProvider: (() -> Long)? = null
    var hostIsPlayingProvider: (() -> Boolean)? = null

    var currentRole: OmniSyncRole = OmniSyncRole.IDLE
        private set

    var currentHost: OmniSyncHost? = null
        private set

    var currentHostRoomName: String? = null
        private set

    var currentTrackTitle: String = ""
        private set

    var currentTrackArtist: String = ""
        private set

    var isStreamPlaying: Boolean = false
        private set

    var listenerVolume: Float = 1.0f
        private set

    val connectedPeers = CopyOnWriteArrayList<OmniSyncPeer>()
    val discoveredHosts = CopyOnWriteArrayList<OmniSyncHost>()

    // Listener state
    private var clientJob: Job? = null
    @Volatile
    private var clientWebSocket: OmniWebSocketClient? = null
    private var listenerPlayer: ExoPlayer? = null
    private var currentStreamUrl: String? = null
    private var currentStreamSongId: Long? = null

    @Volatile
    var currentLatencyMs: Long = 0L
        private set

    @Volatile
    var currentStreamDurationMs: Long = 0L
        private set

    @Volatile
    var currentTrackFormat: String = ""
        private set

    @Volatile
    var currentTrackQuality: String = ""
        private set

    @Volatile
    var currentTrackIsHiRes: Boolean = false
        private set

    private var listenerProgressJob: Job? = null
    private var latencyPingJob: Job? = null

    // NSD discovery
    private var registrationListener: NsdManager.RegistrationListener? = null
    private var discoveryListener: NsdManager.DiscoveryListener? = null
    private var isScanning = false
    private val pendingResolves = ArrayDeque<NsdServiceInfo>()
    private var isResolving = false
    private var resolveTimeoutJob: Job? = null

    val deviceName: String by lazy {
        val model = Build.MODEL ?: "Android Device"
        val brand = Build.BRAND?.replaceFirstChar { it.uppercase() } ?: ""
        if (brand.isNotEmpty() && !model.startsWith(brand, ignoreCase = true)) "$brand $model" else model
    }

    val deviceId: String by lazy {
        UUID.randomUUID().toString().take(8)
    }

    fun addListener(listener: OmniSyncListener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener)
        }
    }

    fun removeListener(listener: OmniSyncListener) {
        listeners.remove(listener)
    }

    private fun notifyRoleChanged(role: OmniSyncRole) {
        mainHandler.post { listeners.forEach { it.onRoleChanged(role) } }
    }

    private fun notifyHostsDiscovered() {
        val list = ArrayList(discoveredHosts)
        mainHandler.post { listeners.forEach { it.onHostsDiscovered(list) } }
    }

    private fun notifyPeersChanged() {
        val list = ArrayList(connectedPeers)
        mainHandler.post { listeners.forEach { it.onPeersChanged(list) } }
    }

    private fun notifyTrackInfo(title: String, artist: String) {
        mainHandler.post { listeners.forEach { it.onTrackInfoChanged(title, artist) } }
    }

    private fun notifyTrackAudioInfo(format: String, quality: String, isHiRes: Boolean) {
        currentTrackFormat = format
        currentTrackQuality = quality
        currentTrackIsHiRes = isHiRes
        mainHandler.post { listeners.forEach { it.onTrackAudioInfoChanged(format, quality, isHiRes) } }
    }

    private fun notifyPlaybackState(isPlaying: Boolean) {
        mainHandler.post { listeners.forEach { it.onPlaybackStateChanged(isPlaying) } }
    }

    private fun notifyError(message: String) {
        mainHandler.post { listeners.forEach { it.onError(message) } }
    }

    private fun notifyProgress(currentMs: Long, totalMs: Long) {
        mainHandler.post { listeners.forEach { it.onProgressUpdate(currentMs, totalMs) } }
    }

    private fun notifyLatency(latencyMs: Long) {
        mainHandler.post { listeners.forEach { it.onLatencyUpdate(latencyMs) } }
    }

    private fun startListenerProgressTracker() {
        listenerProgressJob?.cancel()
        listenerProgressJob = scope.launch(Dispatchers.Main) {
            while (isActive && currentRole == OmniSyncRole.LISTENER) {
                val player = listenerPlayer
                if (player != null) {
                    val pos = player.currentPosition.coerceAtLeast(0L)
                    val dur = player.duration.takeIf { it > 0L } ?: currentStreamDurationMs
                    notifyProgress(pos, dur)
                }
                delay(200L)
            }
        }
    }

    private fun stopListenerProgressTracker() {
        listenerProgressJob?.cancel()
        listenerProgressJob = null
    }

    fun getLocalIPv4Address(): String? {
        try {
            val interfaces = java.net.NetworkInterface.getNetworkInterfaces()?.toList() ?: return null
            val sorted = interfaces.sortedByDescending { iface ->
                when {
                    iface.name.startsWith("wlan", ignoreCase = true) -> 3
                    iface.name.startsWith("ap", ignoreCase = true) || iface.name.startsWith("softap", ignoreCase = true) -> 2
                    iface.name.startsWith("p2p", ignoreCase = true) -> 1
                    else -> 0
                }
            }
            for (iface in sorted) {
                if (iface.isLoopback || !iface.isUp) continue
                val addrs = iface.inetAddresses
                for (addr in addrs) {
                    if (!addr.isLoopbackAddress && addr is Inet4Address) {
                        val host = addr.hostAddress
                        if (!host.isNullOrEmpty() && !host.startsWith("127.") && !host.startsWith("169.254.")) {
                            return host
                        }
                    }
                }
            }
        } catch (ignored: Exception) {}
        return null
    }

    private fun acquireLocks() {
        try {
            if (multicastLock == null) {
                multicastLock = wifiManager.createMulticastLock("omniplay:omnisync_mcast_lock").apply {
                    setReferenceCounted(false)
                }
            }
            if (multicastLock?.isHeld != true) {
                multicastLock?.acquire()
            }
        } catch (ignored: Exception) {}

        try {
            if (wifiLock == null) {
                val mode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    WifiManager.WIFI_MODE_FULL_HIGH_PERF
                } else {
                    @Suppress("DEPRECATION")
                    WifiManager.WIFI_MODE_FULL
                }
                wifiLock = wifiManager.createWifiLock(mode, "omniplay:omnisync_wifi_lock").apply {
                    setReferenceCounted(false)
                }
            }
            if (wifiLock?.isHeld != true) {
                wifiLock?.acquire()
            }
        } catch (ignored: Exception) {}

        try {
            if (wakeLock == null) {
                wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "omniplay:omnisync_wake_lock").apply {
                    setReferenceCounted(false)
                }
            }
            if (wakeLock?.isHeld != true) {
                wakeLock?.acquire(12 * 60 * 60 * 1000L) // 12 hours max
            }
        } catch (ignored: Exception) {}
    }

    private fun releaseLocks() {
        try {
            if (multicastLock?.isHeld == true) {
                multicastLock?.release()
            }
        } catch (ignored: Exception) {}

        try {
            if (wifiLock?.isHeld == true) {
                wifiLock?.release()
            }
        } catch (ignored: Exception) {}

        try {
            if (wakeLock?.isHeld == true) {
                wakeLock?.release()
            }
        } catch (ignored: Exception) {}
    }

    @Volatile
    var currentLivePositionMs: Long = 0L

    @Volatile
    var isHostLivePlaying: Boolean = false

    fun updateHostLivePlayback(song: Song?, positionMs: Long, isPlaying: Boolean) {
        currentLivePositionMs = positionMs
        isHostLivePlaying = isPlaying
        streamServer.currentPositionMs = positionMs
        streamServer.isPlaying = isPlaying
        if (song != null) {
            streamServer.currentSong = song
            streamServer.currentSongUri = song.contentUri
            streamServer.currentDurationMs = song.duration
        }
    }

    // =========================================================================
    // HOST MODE
    // =========================================================================

    fun startHost(roomName: String = deviceName) {
        if (currentRole == OmniSyncRole.HOST) return
        if (currentRole == OmniSyncRole.LISTENER) {
            notifyError("Cannot start hosting while connected as a listener. Disconnect first.")
            return
        }

        currentRole = OmniSyncRole.HOST
        currentHostRoomName = roomName
        connectedPeers.clear()

        acquireLocks()

        streamServer.onPeerJoined = { peer ->
            connectedPeers.removeAll { it.id == peer.id || it.ip == peer.ip }
            connectedPeers.add(peer)
            notifyPeersChanged()
        }
        streamServer.onPeerLeft = { id ->
            connectedPeers.removeAll { it.id == id }
            notifyPeersChanged()
        }

        streamServer.songProvider = hostSongProvider
        streamServer.isPlayingProvider = hostIsPlayingProvider
        streamServer.positionProvider = hostPlaybackPositionProvider
        streamServer.hostName = roomName
        val song = hostSongProvider?.invoke()
        streamServer.currentSong = song
        streamServer.currentSongUri = song?.contentUri
        streamServer.isPlaying = hostIsPlayingProvider?.invoke() == true
        streamServer.currentPositionMs = hostPlaybackPositionProvider?.invoke() ?: 0L
        streamServer.currentDurationMs = song?.duration ?: 0L
        streamServer.start()

        registerNsdService(roomName)

        notifyRoleChanged(OmniSyncRole.HOST)
        notifyPeersChanged()

        if (song != null) {
            broadcastPlay(song, streamServer.currentPositionMs, isPlaying = streamServer.isPlaying)
        }
    }

    private fun registerNsdService(roomName: String) {
        try {
            val serviceInfo = NsdServiceInfo().apply {
                serviceName = roomName
                serviceType = SERVICE_TYPE
                port = PORT
            }

            registrationListener = object : NsdManager.RegistrationListener {
                override fun onServiceRegistered(serviceInfo: NsdServiceInfo) {}
                override fun onRegistrationFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {}
                override fun onServiceUnregistered(serviceInfo: NsdServiceInfo) {}
                override fun onUnregistrationFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {}
            }

            nsdManager.registerService(serviceInfo, NsdManager.PROTOCOL_DNS_SD, registrationListener)
        } catch (ignored: Exception) {}
    }

    fun stopHost() {
        if (currentRole != OmniSyncRole.HOST) return

        try {
            registrationListener?.let { nsdManager.unregisterService(it) }
        } catch (ignored: Exception) {}
        registrationListener = null

        streamServer.stop()
        connectedPeers.clear()
        releaseLocks()

        currentRole = OmniSyncRole.IDLE
        currentHostRoomName = null
        notifyRoleChanged(OmniSyncRole.IDLE)
        notifyPeersChanged()
    }

    fun broadcastPlay(song: Song?, positionMs: Long, isPlaying: Boolean = true) {
        if (currentRole != OmniSyncRole.HOST) return
        if (song == null) return

        streamServer.currentSong = song
        streamServer.currentSongUri = song.contentUri
        streamServer.isPlaying = isPlaying
        streamServer.currentPositionMs = positionMs
        streamServer.currentDurationMs = song.duration

        val hostIp = getLocalIPv4Address()
        val json = JSONObject().apply {
            put("action", "PLAY")
            hostIp?.let { put("hostIp", it) }
            put("streamPort", PORT)
            put("songId", song.id)
            put("songTitle", song.title)
            put("songArtist", song.artist)
            put("songFormat", song.format)
            put("songQuality", song.audioQuality)
            put("isHiRes", song.isHiRes)
            put("songDuration", song.duration)
            put("positionMs", positionMs)
            put("isPlaying", isPlaying)
            put("timestamp", System.currentTimeMillis())
        }.toString()

        streamServer.broadcastEvent(json)
    }

    fun broadcastPause(positionMs: Long = -1L) {
        if (currentRole != OmniSyncRole.HOST) return
        isHostLivePlaying = false
        val pos = if (positionMs >= 0L) positionMs else (hostPlaybackPositionProvider?.invoke() ?: streamServer.currentPositionMs)
        currentLivePositionMs = pos
        streamServer.isPlaying = false
        streamServer.currentPositionMs = pos

        val json = JSONObject().apply {
            put("action", "PAUSE")
            put("positionMs", pos)
            put("timestamp", System.currentTimeMillis())
        }.toString()

        streamServer.broadcastEvent(json)
    }

    fun broadcastSeek(positionMs: Long) {
        if (currentRole != OmniSyncRole.HOST) return
        currentLivePositionMs = positionMs
        streamServer.currentPositionMs = positionMs

        val json = JSONObject().apply {
            put("action", "SEEK")
            put("positionMs", positionMs)
            put("timestamp", System.currentTimeMillis())
        }.toString()

        streamServer.broadcastEvent(json)
    }

    // =========================================================================
    // LISTENER MODE
    // =========================================================================

    fun startScanningHosts() {
        if (isScanning) return
        isScanning = true
        discoveredHosts.clear()
        pendingResolves.clear()
        notifyHostsDiscovered()

        acquireLocks()

        discoveryListener = object : NsdManager.DiscoveryListener {
            override fun onDiscoveryStarted(regType: String) {}
            override fun onServiceFound(serviceInfo: NsdServiceInfo) {
                if (serviceInfo.serviceType.contains("_omnisync")) {
                    resolveService(serviceInfo)
                }
            }
            override fun onServiceLost(serviceInfo: NsdServiceInfo) {
                discoveredHosts.removeAll { it.name == serviceInfo.serviceName }
                notifyHostsDiscovered()
            }
            override fun onDiscoveryStopped(serviceType: String) {}
            override fun onStartDiscoveryFailed(serviceType: String, errorCode: Int) {
                try { nsdManager.stopServiceDiscovery(this) } catch (ignored: Exception) {}
            }
            override fun onStopDiscoveryFailed(serviceType: String, errorCode: Int) {}
        }

        try {
            nsdManager.discoverServices(SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, discoveryListener)
        } catch (ignored: Exception) {}

        // Fallback HTTP probe for Wi-Fi Gateway & Hotspot IPs
        scope.launch {
            try {
                val candidates = mutableListOf<String>()
                val dhcp = wifiManager.dhcpInfo
                val gatewayInt = dhcp?.gateway ?: 0
                if (gatewayInt != 0) {
                    val gwIp = String.format(
                        java.util.Locale.US,
                        "%d.%d.%d.%d",
                        gatewayInt and 0xff,
                        gatewayInt shr 8 and 0xff,
                        gatewayInt shr 16 and 0xff,
                        gatewayInt shr 24 and 0xff
                    )
                    if (gwIp != "0.0.0.0") candidates.add(gwIp)
                }
                candidates.add("192.168.43.1")
                candidates.add("192.168.49.1")

                for (ip in candidates.distinct()) {
                    if (discoveredHosts.any { it.address == ip }) continue
                    try {
                        Socket().use { s ->
                            s.connect(InetSocketAddress(ip, PORT), 400)
                            val host = OmniSyncHost(
                                name = ip,
                                address = ip,
                                port = PORT,
                                streamPort = PORT
                            )
                            if (!discoveredHosts.any { it.address == ip }) {
                                discoveredHosts.add(host)
                                notifyHostsDiscovered()
                            }
                        }
                    } catch (ignored: Exception) {}
                }
            } catch (ignored: Exception) {}
        }
    }

    @Synchronized
    private fun resolveService(serviceInfo: NsdServiceInfo) {
        pendingResolves.add(serviceInfo)
        processNextResolve()
    }

    @Synchronized
    private fun processNextResolve() {
        if (isResolving || pendingResolves.isEmpty()) return
        val next = pendingResolves.removeFirst()
        isResolving = true

        resolveTimeoutJob?.cancel()
        resolveTimeoutJob = scope.launch {
            delay(4000L)
            synchronized(this@OmniSyncManager) {
                if (isResolving) {
                    isResolving = false
                    processNextResolve()
                }
            }
        }

        try {
            nsdManager.resolveService(next, object : NsdManager.ResolveListener {
                override fun onServiceResolved(resolved: NsdServiceInfo) {
                    try {
                        resolveTimeoutJob?.cancel()
                        resolveTimeoutJob = null
                        val addrs = try {
                            val hostName = resolved.host?.hostName ?: resolved.host?.hostAddress
                            if (hostName != null) java.net.InetAddress.getAllByName(hostName) else arrayOf(resolved.host)
                        } catch (e: Exception) {
                            arrayOf(resolved.host)
                        }
                        val ipv4 = addrs?.firstOrNull { it is Inet4Address }?.hostAddress
                        val rawHost = ipv4 ?: resolved.host?.hostAddress
                        if (rawHost != null) {
                            val host = if (rawHost.contains('%')) rawHost.substringBefore('%') else rawHost
                            val room = OmniSyncHost(
                                name = resolved.serviceName,
                                address = host,
                                port = resolved.port,
                                streamPort = resolved.port
                            )
                            discoveredHosts.removeAll { it.address == host || it.name == room.name }
                            discoveredHosts.add(room)
                            notifyHostsDiscovered()
                        }
                    } finally {
                        synchronized(this@OmniSyncManager) {
                            isResolving = false
                            processNextResolve()
                        }
                    }
                }

                override fun onResolveFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
                    resolveTimeoutJob?.cancel()
                    resolveTimeoutJob = null
                    synchronized(this@OmniSyncManager) {
                        isResolving = false
                        processNextResolve()
                    }
                }
            })
        } catch (e: Exception) {
            isResolving = false
            processNextResolve()
        }
    }

    fun stopScanningHosts() {
        if (!isScanning) return
        isScanning = false
        try {
            discoveryListener?.let { nsdManager.stopServiceDiscovery(it) }
        } catch (ignored: Exception) {}
        discoveryListener = null
        pendingResolves.clear()
        isResolving = false
        resolveTimeoutJob?.cancel()
        if (currentRole == OmniSyncRole.IDLE) {
            releaseLocks()
        }
    }

    fun connectToHost(host: OmniSyncHost) {
        if (currentRole == OmniSyncRole.HOST) {
            notifyError("Cannot join as listener while active as host. Stop hosting first.")
            return
        }
        stopScanningHosts()

        currentRole = OmniSyncRole.LISTENER
        currentHost = host
        currentHostRoomName = host.name
        notifyRoleChanged(OmniSyncRole.LISTENER)

        acquireLocks()

        clientJob?.cancel()
        clientWebSocket?.close()
        clientWebSocket = null

        clientJob = scope.launch(Dispatchers.IO) {
            val targetPort = if (host.port > 0) host.port else PORT
            val baseUrl = "http://${host.address}:$targetPort"

            // 1. Initial quick probe for room state
            var initialStatus: JSONObject? = null
            var lastError: Exception? = null
            for (attempt in 1..3) {
                try {
                    val statusUrl = URL("$baseUrl/status")
                    val conn = (statusUrl.openConnection() as HttpURLConnection).apply {
                        connectTimeout = 2500
                        readTimeout = 2500
                        requestMethod = "GET"
                        setRequestProperty("Connection", "close")
                        setRequestProperty("Accept", "application/json")
                        useCaches = false
                    }
                    val code = conn.responseCode
                    if (code == 200) {
                        val body = conn.inputStream.bufferedReader().readText()
                        conn.disconnect()
                        initialStatus = JSONObject(body)
                        break
                    } else {
                        conn.disconnect()
                        throw Exception("Host returned HTTP status $code")
                    }
                } catch (e: Exception) {
                    if (e is CancellationException) return@launch
                    lastError = e
                    delay(250L)
                }
            }

            if (initialStatus == null) {
                val e = lastError
                val msg = if (e?.message?.contains("EHOSTUNREACH", ignoreCase = true) == true ||
                    e?.message?.contains("No route to host", ignoreCase = true) == true) {
                    "Cannot reach host at ${host.address}:$targetPort. Ensure Host is actively broadcasting and on the same Wi-Fi/Hotspot."
                } else {
                    "Failed to connect to host: ${e?.localizedMessage ?: "Host unavailable"}"
                }
                disconnectListener(msg)
                return@launch
            }

            val title = initialStatus.optString("title", "")
            val artist = initialStatus.optString("artist", "")
            val songId = initialStatus.optLong("songId", 0L)
            val pos = initialStatus.optLong("position", 0L)
            val isHostPlaying = initialStatus.optBoolean("isPlaying", false)
            val rName = initialStatus.optString("hostName", host.name)
            currentHostRoomName = rName

            if (title.isNotEmpty()) {
                val streamUrl = "$baseUrl/stream?id=$songId"
                playListenerStream(streamUrl, songId, pos, title, artist, startPlaying = isHostPlaying)
            }

            // 2. Real-Time RFC 6455 WebSocket Live Synchronization Channel
            var reconnectAttempts = 0
            while (isActive && currentRole == OmniSyncRole.LISTENER) {
                try {
                    val encodedName = try { URLEncoder.encode(deviceName, "UTF-8") } catch (e: Exception) { deviceName }
                    val wsClient = OmniWebSocketClient(
                        host = host.address,
                        port = targetPort,
                        path = "/ws?id=$deviceId&name=$encodedName",
                        timeoutMs = 4000
                    )
                    clientWebSocket = wsClient

                    wsClient.onOpen = {
                        reconnectAttempts = 0
                        val regJson = JSONObject().apply {
                            put("action", "REGISTER")
                            put("id", deviceId)
                            put("name", deviceName)
                        }.toString()
                        try { wsClient.send(regJson) } catch (ignored: Exception) {}

                        latencyPingJob?.cancel()
                        latencyPingJob = scope.launch {
                            while (isActive && currentRole == OmniSyncRole.LISTENER && wsClient.isConnected) {
                                val pingJson = JSONObject().apply {
                                    put("action", "PING")
                                    put("clientTime", System.currentTimeMillis())
                                }.toString()
                                try { wsClient.send(pingJson) } catch (ignored: Exception) {}
                                delay(1200L)
                            }
                        }
                    }

                    wsClient.onMessage = { message ->
                        try {
                            val json = JSONObject(message)
                            handleListenerCommand(json, host)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }

                    wsClient.onClose = { code, reason ->
                        if (code == 1000 && reason.contains("STOP", ignoreCase = true)) {
                            disconnectListener("Host stopped broadcasting")
                        }
                    }

                    wsClient.connect() // Blocks on socket read loop until disconnected
                } catch (e: Exception) {
                    if (e is CancellationException) return@launch
                    reconnectAttempts++
                }

                if (!isActive || currentRole != OmniSyncRole.LISTENER) break

                // If disconnected repeatedly, verify if host is still running
                if (reconnectAttempts >= 3) {
                    var hostAlive = false
                    try {
                        Socket().use { s ->
                            s.connect(InetSocketAddress(host.address, targetPort), 1500)
                            hostAlive = true
                        }
                    } catch (ignored: Exception) {}

                    if (!hostAlive) {
                        disconnectListener("Host disconnected")
                        break
                    }
                }

                delay(1000L) // Wait 1 second before reconnecting
            }
        }
    }

    private fun handleListenerCommand(json: JSONObject, host: OmniSyncHost) {
        val targetPort = if (host.port > 0) host.port else PORT
        when (json.optString("action")) {
            "WELCOME" -> {
                val rName = json.optString("hostName")
                if (rName.isNotEmpty()) {
                    currentHostRoomName = rName
                }
                val title = json.optString("title")
                val artist = json.optString("artist")
                val format = json.optString("format")
                val quality = json.optString("quality")
                val isHiRes = json.optBoolean("isHiRes", false)
                val songId = json.optLong("songId", 0L)
                val pos = json.optLong("positionMs", 0L)
                val dur = json.optLong("durationMs", 0L)
                val isHostPlaying = json.optBoolean("isPlaying", false)
                val hostTimestamp = json.optLong("timestamp", 0L)
                if (dur > 0L) currentStreamDurationMs = dur
                if (format.isNotEmpty() || quality.isNotEmpty()) {
                    notifyTrackAudioInfo(format, quality, isHiRes)
                }
                if (title.isNotEmpty()) {
                    val streamUrl = "http://${host.address}:$targetPort/stream?id=$songId"
                    playListenerStream(streamUrl, songId, pos, title, artist, startPlaying = isHostPlaying, durationMs = dur, hostTimestamp = hostTimestamp)
                }
            }
            "PLAY" -> {
                val ipFromCmd = json.optString("hostIp")
                val streamIp = if (ipFromCmd.isNotEmpty()) ipFromCmd else host.address
                val port = json.optInt("streamPort", targetPort)
                val songId = json.optLong("songId", 0L)
                val positionMs = json.optLong("positionMs", 0L)
                val songTitle = json.optString("songTitle", "OmniSync Track")
                val songArtist = json.optString("songArtist", "Host Broadcast")
                val songFormat = json.optString("songFormat")
                val songQuality = json.optString("songQuality")
                val isHiRes = json.optBoolean("isHiRes", false)
                val songDuration = json.optLong("songDuration", 0L)
                val isHostPlaying = json.optBoolean("isPlaying", true)
                val hostTimestamp = json.optLong("timestamp", 0L)
                if (songDuration > 0L) currentStreamDurationMs = songDuration
                if (songFormat.isNotEmpty() || songQuality.isNotEmpty()) {
                    notifyTrackAudioInfo(songFormat, songQuality, isHiRes)
                }

                val formattedHost = if (streamIp.contains(":") && !streamIp.startsWith("[")) "[$streamIp]" else streamIp
                val streamUrl = "http://$formattedHost:$port/stream?id=$songId"

                playListenerStream(streamUrl, songId, positionMs, songTitle, songArtist, startPlaying = isHostPlaying, durationMs = songDuration, hostTimestamp = hostTimestamp)
            }
            "PAUSE" -> {
                val pos = json.optLong("positionMs", -1L)
                pauseListenerStream(pos)
            }
            "SEEK" -> {
                val pos = json.optLong("positionMs", 0L)
                seekListenerStream(pos)
            }
            "PONG" -> {
                val clientTime = json.optLong("clientTime", 0L)
                if (clientTime > 0L) {
                    val rtt = (System.currentTimeMillis() - clientTime).coerceAtLeast(0L)
                    val latency = (rtt / 2L).coerceAtLeast(1L)
                    currentLatencyMs = latency
                    notifyLatency(latency)
                }
            }
            "PING" -> {
                val isHostPlaying = json.optBoolean("isPlaying", false)
                val hostPos = json.optLong("positionMs", 0L)
                val hostSongId = json.optLong("songId", 0L)
                syncDrift(hostPos, isHostPlaying, hostSongId)
            }
            "STOP" -> {
                disconnectListener("Host stopped broadcasting")
            }
        }
    }

    private fun syncDrift(hostPos: Long, isHostPlaying: Boolean, hostSongId: Long = 0L) {
        mainHandler.post {
            val player = listenerPlayer ?: return@post
            if (isHostPlaying != isStreamPlaying) {
                if (isHostPlaying) {
                    player.play()
                    isStreamPlaying = true
                    notifyPlaybackState(true)
                } else {
                    player.pause()
                    isStreamPlaying = false
                    notifyPlaybackState(false)
                }
            }
            if (isHostPlaying && hostPos > 0L) {
                val current = player.currentPosition
                val transitTime = currentLatencyMs.coerceAtLeast(0L)
                val expectedHostPos = hostPos + transitTime
                val drift = current - expectedHostPos

                if (abs(drift) > 250L) {
                    // Hard seek if drift is noticeably large (> 250ms)
                    player.seekTo(expectedHostPos)
                    try { player.playbackParameters = PlaybackParameters(1.0f) } catch (ignored: Exception) {}
                } else if (abs(drift) > 30L) {
                    // Micro-speed adjustment for smooth, pop-free acoustic alignment
                    val speed = if (drift < 0) 1.04f else 0.96f
                    try {
                        if (player.playbackParameters.speed != speed) {
                            player.playbackParameters = PlaybackParameters(speed)
                        }
                    } catch (ignored: Exception) {}
                } else {
                    try {
                        if (player.playbackParameters.speed != 1.0f) {
                            player.playbackParameters = PlaybackParameters(1.0f)
                        }
                    } catch (ignored: Exception) {}
                }
            }
        }
    }

    private var pendingSeekPositionMs: Long = 0L

    private fun getOrCreateListenerPlayer(): ExoPlayer {
        listenerPlayer?.let { return it }

        val extractorsFactory = DefaultExtractorsFactory()
            .setConstantBitrateSeekingEnabled(true)
            .setConstantBitrateSeekingAlwaysEnabled(true)

        val mediaSourceFactory = DefaultMediaSourceFactory(context, extractorsFactory)

        val loadControl = DefaultLoadControl.Builder()
            .setBufferDurationsMs(
                /* minBufferMs = */ 1000,
                /* maxBufferMs = */ 3000,
                /* bufferForPlaybackMs = */ 100,
                /* bufferForPlaybackAfterRebufferMs = */ 250
            )
            .setPrioritizeTimeOverSizeThresholds(true)
            .build()

        val player = ExoPlayer.Builder(context)
            .setMediaSourceFactory(mediaSourceFactory)
            .setLoadControl(loadControl)
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
                    .setUsage(C.USAGE_MEDIA)
                    .build(),
                true
            )
            .build()

        player.volume = listenerVolume
        player.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_READY -> {
                        val target = pendingSeekPositionMs
                        if (target > 50L) {
                            pendingSeekPositionMs = 0L
                            player.seekTo(target)
                        } else {
                            pendingSeekPositionMs = 0L
                        }
                        isStreamPlaying = player.isPlaying
                        notifyPlaybackState(isStreamPlaying)
                    }
                    Player.STATE_ENDED -> {
                        isStreamPlaying = false
                        notifyPlaybackState(false)
                    }
                    else -> {}
                }
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                isStreamPlaying = isPlaying
                notifyPlaybackState(isPlaying)
            }

            override fun onPlayerError(error: PlaybackException) {
                // Ignore transient errors, retry prepare if actively listening
                try {
                    if (currentRole == OmniSyncRole.LISTENER) {
                        player.prepare()
                        if (isStreamPlaying) {
                            player.play()
                        }
                    }
                } catch (ignored: Exception) {}
            }
        })
        listenerPlayer = player
        return player
    }

    private fun playListenerStream(
        url: String,
        songId: Long,
        positionMs: Long,
        title: String,
        artist: String,
        startPlaying: Boolean = true,
        durationMs: Long = 0L,
        hostTimestamp: Long = 0L
    ) {
        mainHandler.post {
            try {
                if (currentRole != OmniSyncRole.LISTENER) return@post
                val isNewSong = (currentStreamSongId != songId || currentStreamUrl != url || currentTrackTitle != title)

                currentTrackTitle = title
                currentTrackArtist = artist
                currentStreamSongId = songId
                if (durationMs > 0L) currentStreamDurationMs = durationMs
                notifyTrackInfo(title, artist)

                val player = getOrCreateListenerPlayer()
                startListenerProgressTracker()

                val transitLag = if (hostTimestamp > 0L) {
                    (System.currentTimeMillis() - hostTimestamp).coerceIn(0L, 5000L)
                } else {
                    currentLatencyMs.coerceAtLeast(0L)
                }
                val targetPos = if (positionMs > 0L) positionMs + transitLag else 0L

                if (isNewSong) {
                    currentStreamUrl = url
                    pendingSeekPositionMs = targetPos
                    val mediaItem = MediaItem.fromUri(url)
                    player.setMediaItem(mediaItem, targetPos)
                    player.prepare()
                    if (startPlaying) {
                        player.play()
                        isStreamPlaying = true
                    } else {
                        player.pause()
                        isStreamPlaying = false
                    }
                } else {
                    val expectedPos = if (positionMs > 0L) targetPos else player.currentPosition
                    if (positionMs > 0L && abs(player.currentPosition - expectedPos) > 100L) {
                        player.seekTo(expectedPos)
                    }
                    if (startPlaying) {
                        if (!player.isPlaying) player.play()
                        isStreamPlaying = true
                    } else {
                        if (player.isPlaying) player.pause()
                        isStreamPlaying = false
                    }
                    notifyProgress(expectedPos, currentStreamDurationMs)
                }
                notifyPlaybackState(isStreamPlaying)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun pauseListenerStream(pos: Long = -1L) {
        mainHandler.post {
            try {
                if (currentRole != OmniSyncRole.LISTENER) return@post
                val player = listenerPlayer ?: return@post
                player.pause()
                val targetPos = if (pos >= 0L) pos else player.currentPosition
                if (pos >= 0L && abs(player.currentPosition - pos) > 50L) {
                    player.seekTo(pos)
                }
                isStreamPlaying = false
                notifyPlaybackState(false)
                notifyProgress(targetPos, currentStreamDurationMs)
            } catch (ignored: Exception) {}
        }
    }

    private fun seekListenerStream(positionMs: Long) {
        mainHandler.post {
            try {
                if (currentRole != OmniSyncRole.LISTENER) return@post
                val player = listenerPlayer ?: return@post
                player.seekTo(positionMs)
                notifyProgress(positionMs, currentStreamDurationMs)
            } catch (ignored: Exception) {}
        }
    }

    fun setListenerVolume(volume: Float) {
        listenerVolume = volume.coerceIn(0f, 1f)
        mainHandler.post {
            try {
                listenerPlayer?.volume = listenerVolume
            } catch (ignored: Exception) {}
        }
    }

    fun toggleListenerPlayback() {
        mainHandler.post {
            val player = listenerPlayer ?: return@post
            if (player.isPlaying) {
                player.pause()
                isStreamPlaying = false
                notifyPlaybackState(false)
            } else {
                player.play()
                isStreamPlaying = true
                notifyPlaybackState(true)
            }
        }
    }

    fun disconnectListener(reason: String? = null) {
        mainHandler.post {
            if (currentRole != OmniSyncRole.LISTENER) return@post

            val hostToUnregister = currentHost
            currentRole = OmniSyncRole.IDLE
            currentHost = null
            currentHostRoomName = null
            currentTrackTitle = ""
            currentTrackArtist = ""
            currentStreamSongId = null
            currentStreamUrl = null
            isStreamPlaying = false

            try {
                clientWebSocket?.close()
                clientWebSocket = null
            } catch (ignored: Exception) {}

            stopListenerProgressTracker()
            latencyPingJob?.cancel()
            latencyPingJob = null
            currentLatencyMs = 0L
            currentStreamDurationMs = 0L
            notifyLatency(0L)
            notifyProgress(0L, 0L)

            try {
                listenerPlayer?.stop()
                listenerPlayer?.clearMediaItems()
                listenerPlayer?.release()
            } catch (ignored: Exception) {}
            listenerPlayer = null

            releaseLocks()

            notifyRoleChanged(OmniSyncRole.IDLE)
            notifyPlaybackState(false)
            notifyTrackInfo("", "")
            notifyTrackAudioInfo("", "", false)
            if (!reason.isNullOrEmpty()) {
                notifyError(reason)
            }

            scope.launch(Dispatchers.IO) {
                try {
                    hostToUnregister?.let {
                        val port = if (it.port > 0) it.port else PORT
                        val unregUrl = URL("http://${it.address}:$port/unregister?id=$deviceId")
                        val conn = (unregUrl.openConnection() as HttpURLConnection).apply {
                            connectTimeout = 2000
                            readTimeout = 2000
                            requestMethod = "GET"
                            setRequestProperty("Connection", "close")
                            useCaches = false
                        }
                        conn.responseCode
                        conn.disconnect()
                    }
                } catch (ignored: Exception) {}
            }
        }

        clientJob?.cancel()
        clientJob = null
    }
}

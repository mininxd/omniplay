package xyz.omniplay.sync

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.PowerManager
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import xyz.omniplay.model.Song
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Inet4Address
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import java.util.UUID
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.math.abs

/**
 * OmniSync Manager:
 * Handles local network music streaming orchestration for Host and Listener modes.
 * - Host: Streams playing audio over HTTP and broadcasts sync controls (Play, Pause, Seek).
 * - Listener: Discovers hosts via mDNS/NSD (and fallback IP probing), connects to stream, and plays synchronously.
 */
class OmniSyncManager private constructor(private val context: Context) {

    companion object {
        const val SERVICE_TYPE = "_omnisync._tcp"
        const val CONTROL_PORT = 8999
        const val STREAM_PORT = 8998

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
        fun onPlaybackStateChanged(isPlaying: Boolean)
        fun onError(message: String)
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

    val streamServer = OmniSyncStreamServer(context, STREAM_PORT)

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

    // Host networking
    private var hostControlServerJob: Job? = null
    private var hostControlServerSocket: ServerSocket? = null
    private val peerWriters = CopyOnWriteArrayList<PrintWriter>()

    // Listener networking
    private var clientSocket: Socket? = null
    private var clientJob: Job? = null
    private var listenerPlayer: MediaPlayer? = null
    private var currentStreamUrl: String? = null

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

    private fun notifyPlaybackState(isPlaying: Boolean) {
        mainHandler.post { listeners.forEach { it.onPlaybackStateChanged(isPlaying) } }
    }

    private fun notifyError(message: String) {
        mainHandler.post { listeners.forEach { it.onError(message) } }
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

    // =========================================================================
    // HOST MODE
    // =========================================================================

    fun startHost(roomName: String = "$deviceName's OmniSync") {
        if (currentRole == OmniSyncRole.HOST) return
        if (currentRole == OmniSyncRole.LISTENER) {
            disconnectListener()
        }

        currentRole = OmniSyncRole.HOST
        currentHostRoomName = roomName
        connectedPeers.clear()
        peerWriters.clear()

        acquireLocks()

        streamServer.hostName = roomName
        val song = hostSongProvider?.invoke()
        streamServer.currentSong = song
        streamServer.currentSongUri = song?.contentUri
        streamServer.isPlaying = hostIsPlayingProvider?.invoke() == true
        streamServer.currentPositionMs = hostPlaybackPositionProvider?.invoke() ?: 0L
        streamServer.currentDurationMs = song?.duration ?: 0L
        streamServer.start()

        startHostControlServer(roomName)
        registerNsdService(roomName)

        notifyRoleChanged(OmniSyncRole.HOST)
        notifyPeersChanged()

        if (song != null && streamServer.isPlaying) {
            broadcastPlay(song, streamServer.currentPositionMs)
        }
    }

    private fun startHostControlServer(roomName: String) {
        hostControlServerJob?.cancel()
        try {
            hostControlServerSocket?.close()
        } catch (ignored: Exception) {}

        hostControlServerJob = scope.launch {
            try {
                val server = ServerSocket().apply {
                    reuseAddress = true
                    bind(InetSocketAddress(CONTROL_PORT))
                }
                hostControlServerSocket = server

                while (isActive && !server.isClosed) {
                    try {
                        val client = server.accept()
                        client.tcpNoDelay = true
                        launch(Dispatchers.IO) {
                            handleHostPeerConnection(client, roomName)
                        }
                    } catch (e: Exception) {
                        if (!isActive || server.isClosed) break
                    }
                }
            } catch (e: Exception) {
                if (e !is CancellationException) {
                    notifyError("Host control server error: ${e.localizedMessage}")
                }
            }
        }
    }

    private suspend fun handleHostPeerConnection(socket: Socket, roomName: String) {
        var currentPeer: OmniSyncPeer? = null
        var currentWriter: PrintWriter? = null

        try {
            val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
            val writer = PrintWriter(socket.getOutputStream(), true)
            currentWriter = writer
            peerWriters.add(writer)

            val clientIp = socket.inetAddress.hostAddress ?: "Unknown"

            while (scope.isActive && !socket.isClosed) {
                val line = withContext(Dispatchers.IO) { reader.readLine() } ?: break
                val json = try { JSONObject(line) } catch (e: Exception) { null } ?: continue
                val action = json.optString("action")

                if (action == "HELLO") {
                    val pId = json.optString("id", UUID.randomUUID().toString())
                    val pName = json.optString("name", "Listener ($clientIp)")
                    val peer = OmniSyncPeer(id = pId, name = pName, ip = clientIp)
                    currentPeer = peer

                    connectedPeers.removeAll { it.id == pId || it.ip == clientIp }
                    connectedPeers.add(peer)
                    notifyPeersChanged()

                    val hostIp = (socket.localAddress as? Inet4Address)?.hostAddress
                        ?: getLocalIPv4Address()
                        ?: socket.localAddress.hostAddress

                    val welcome = JSONObject().apply {
                        put("action", "WELCOME")
                        put("hostName", roomName)
                        put("hostIp", hostIp)
                        put("streamPort", STREAM_PORT)
                    }
                    writer.println(welcome.toString())

                    // If host is currently playing, send PLAY event immediately
                    val playingSong = hostSongProvider?.invoke()
                    val isPlaying = hostIsPlayingProvider?.invoke() == true
                    val pos = hostPlaybackPositionProvider?.invoke() ?: 0L
                    if (playingSong != null && isPlaying) {
                        val playCmd = JSONObject().apply {
                            put("action", "PLAY")
                            put("hostIp", hostIp)
                            put("streamPort", STREAM_PORT)
                            put("songTitle", playingSong.title)
                            put("songArtist", playingSong.artist)
                            put("songDuration", playingSong.duration)
                            put("positionMs", pos)
                        }
                        writer.println(playCmd.toString())
                    }
                }
            }
        } catch (ignored: Exception) {
        } finally {
            currentWriter?.let { peerWriters.remove(it) }
            currentPeer?.let {
                connectedPeers.remove(it)
                notifyPeersChanged()
            }
            try { socket.close() } catch (ignored: Exception) {}
        }
    }

    private fun registerNsdService(roomName: String) {
        try {
            val serviceInfo = NsdServiceInfo().apply {
                serviceName = roomName
                serviceType = SERVICE_TYPE
                port = CONTROL_PORT
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

        scope.launch {
            val stopCmd = JSONObject().apply { put("action", "STOP") }.toString()
            peerWriters.forEach { try { it.println(stopCmd) } catch (ignored: Exception) {} }
            peerWriters.clear()
        }

        try {
            registrationListener?.let { nsdManager.unregisterService(it) }
        } catch (ignored: Exception) {}
        registrationListener = null

        hostControlServerJob?.cancel()
        hostControlServerJob = null
        try {
            hostControlServerSocket?.close()
        } catch (ignored: Exception) {}
        hostControlServerSocket = null

        streamServer.stop()
        connectedPeers.clear()
        releaseLocks()

        currentRole = OmniSyncRole.IDLE
        currentHostRoomName = null
        notifyRoleChanged(OmniSyncRole.IDLE)
        notifyPeersChanged()
    }

    fun broadcastPlay(song: Song?, positionMs: Long) {
        if (currentRole != OmniSyncRole.HOST) return
        if (song == null) return

        streamServer.currentSong = song
        streamServer.currentSongUri = song.contentUri
        streamServer.isPlaying = true
        streamServer.currentPositionMs = positionMs
        streamServer.currentDurationMs = song.duration

        val hostIp = getLocalIPv4Address()
        val json = JSONObject().apply {
            put("action", "PLAY")
            hostIp?.let { put("hostIp", it) }
            put("streamPort", STREAM_PORT)
            put("songTitle", song.title)
            put("songArtist", song.artist)
            put("songDuration", song.duration)
            put("positionMs", positionMs)
        }.toString()

        scope.launch {
            peerWriters.forEach { writer ->
                try { writer.println(json) } catch (ignored: Exception) {}
            }
        }
    }

    fun broadcastPause() {
        if (currentRole != OmniSyncRole.HOST) return
        streamServer.isPlaying = false

        val json = JSONObject().apply {
            put("action", "PAUSE")
        }.toString()

        scope.launch {
            peerWriters.forEach { writer ->
                try { writer.println(json) } catch (ignored: Exception) {}
            }
        }
    }

    fun broadcastSeek(positionMs: Long) {
        if (currentRole != OmniSyncRole.HOST) return
        streamServer.currentPositionMs = positionMs

        val json = JSONObject().apply {
            put("action", "SEEK")
            put("positionMs", positionMs)
        }.toString()

        scope.launch {
            peerWriters.forEach { writer ->
                try { writer.println(json) } catch (ignored: Exception) {}
            }
        }
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

        // Fallback probe for Wi-Fi Gateway & Hotspot IPs (when mDNS is filtered by routers)
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
                            s.connect(InetSocketAddress(ip, CONTROL_PORT), 500)
                            val host = OmniSyncHost(
                                name = "OmniSync Host ($ip)",
                                address = ip,
                                port = CONTROL_PORT,
                                streamPort = STREAM_PORT
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
                                streamPort = STREAM_PORT
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
            stopHost()
        }
        stopScanningHosts()

        currentRole = OmniSyncRole.LISTENER
        currentHost = host
        currentHostRoomName = host.name
        notifyRoleChanged(OmniSyncRole.LISTENER)

        acquireLocks()

        clientJob?.cancel()
        try { clientSocket?.close() } catch (ignored: Exception) {}

        clientJob = scope.launch {
            try {
                val socket = Socket()
                try {
                    val cm = connectivityManager
                    val wifiNetwork = cm?.allNetworks?.firstOrNull { network ->
                        val caps = cm.getNetworkCapabilities(network)
                        caps != null && caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    }
                    wifiNetwork?.bindSocket(socket)
                } catch (ignored: Exception) {}

                socket.connect(InetSocketAddress(host.address, host.port), 5000)
                clientSocket = socket

                val writer = PrintWriter(socket.getOutputStream(), true)
                val reader = BufferedReader(InputStreamReader(socket.getInputStream()))

                // Handshake
                val hello = JSONObject().apply {
                    put("action", "HELLO")
                    put("id", deviceId)
                    put("name", deviceName)
                }
                writer.println(hello.toString())

                while (isActive && !socket.isClosed) {
                    val line = withContext(Dispatchers.IO) { reader.readLine() } ?: break
                    val json = try { JSONObject(line) } catch (e: Exception) { null } ?: continue
                    handleListenerCommand(json, host)
                }
            } catch (e: Exception) {
                if (e !is CancellationException) {
                    val msg = if (e.message?.contains("EHOSTUNREACH", ignoreCase = true) == true ||
                        e.message?.contains("No route to host", ignoreCase = true) == true) {
                        "Cannot reach host (${host.address}). Ensure Host is actively broadcasting and on the same Wi-Fi/Hotspot (check AP Isolation in router)."
                    } else {
                        "Disconnected from host: ${e.localizedMessage}"
                    }
                    notifyError(msg)
                }
            } finally {
                if (currentRole == OmniSyncRole.LISTENER) {
                    disconnectListener()
                }
            }
        }
    }

    private fun handleListenerCommand(json: JSONObject, host: OmniSyncHost) {
        when (json.optString("action")) {
            "WELCOME" -> {
                val rName = json.optString("hostName")
                if (rName.isNotEmpty()) {
                    currentHostRoomName = rName
                }
            }
            "PLAY" -> {
                val ipFromCmd = json.optString("hostIp")
                val streamIp = if (ipFromCmd.isNotEmpty()) ipFromCmd else host.address
                val port = json.optInt("streamPort", host.streamPort)
                val positionMs = json.optLong("positionMs", 0L)
                val songTitle = json.optString("songTitle", "OmniSync Track")
                val songArtist = json.optString("songArtist", "Host Broadcast")

                val formattedHost = if (streamIp.contains(":") && !streamIp.startsWith("[")) "[$streamIp]" else streamIp
                val streamUrl = "http://$formattedHost:$port/stream"

                playListenerStream(streamUrl, positionMs, songTitle, songArtist)
            }
            "PAUSE" -> {
                pauseListenerStream()
            }
            "SEEK" -> {
                val pos = json.optLong("positionMs", 0L)
                seekListenerStream(pos)
            }
            "STOP" -> {
                disconnectListener()
            }
        }
    }

    @Synchronized
    private fun playListenerStream(url: String, positionMs: Long, title: String, artist: String) {
        currentTrackTitle = title
        currentTrackArtist = artist
        notifyTrackInfo(title, artist)

        scope.launch(Dispatchers.Main) {
            try {
                var player = listenerPlayer
                if (player == null) {
                    player = MediaPlayer().apply {
                        setAudioAttributes(
                            AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .build()
                        )
                        setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK)
                        setOnErrorListener { _, what, extra ->
                            notifyError("Stream error ($what, $extra)")
                            true
                        }
                    }
                    listenerPlayer = player
                }

                if (currentStreamUrl != url) {
                    currentStreamUrl = url
                    player.reset()
                    player.setDataSource(url)
                    player.setOnPreparedListener { mp ->
                        if (positionMs > 1000) {
                            try { mp.seekTo(positionMs.toInt()) } catch (ignored: Exception) {}
                        }
                        mp.setVolume(listenerVolume, listenerVolume)
                        mp.start()
                        isStreamPlaying = true
                        notifyPlaybackState(true)
                    }
                    player.prepareAsync()
                } else {
                    if (positionMs > 0 && abs(player.currentPosition - positionMs) > 3000) {
                        try { player.seekTo(positionMs.toInt()) } catch (ignored: Exception) {}
                    }
                    player.setVolume(listenerVolume, listenerVolume)
                    player.start()
                    isStreamPlaying = true
                    notifyPlaybackState(true)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                notifyError("Stream playback error: ${e.localizedMessage}")
            }
        }
    }

    private fun pauseListenerStream() {
        scope.launch(Dispatchers.Main) {
            try {
                listenerPlayer?.pause()
                isStreamPlaying = false
                notifyPlaybackState(false)
            } catch (ignored: Exception) {}
        }
    }

    private fun seekListenerStream(positionMs: Long) {
        scope.launch(Dispatchers.Main) {
            try {
                listenerPlayer?.seekTo(positionMs.toInt())
            } catch (ignored: Exception) {}
        }
    }

    fun setListenerVolume(volume: Float) {
        listenerVolume = volume.coerceIn(0f, 1f)
        scope.launch(Dispatchers.Main) {
            try {
                listenerPlayer?.setVolume(listenerVolume, listenerVolume)
            } catch (ignored: Exception) {}
        }
    }

    fun toggleListenerPlayback() {
        if (isStreamPlaying) {
            pauseListenerStream()
        } else {
            scope.launch(Dispatchers.Main) {
                try {
                    listenerPlayer?.start()
                    isStreamPlaying = true
                    notifyPlaybackState(true)
                } catch (ignored: Exception) {}
            }
        }
    }

    fun disconnectListener() {
        if (currentRole != OmniSyncRole.LISTENER) return

        clientJob?.cancel()
        clientJob = null
        try { clientSocket?.close() } catch (ignored: Exception) {}
        clientSocket = null

        scope.launch(Dispatchers.Main) {
            try {
                listenerPlayer?.stop()
                listenerPlayer?.reset()
                listenerPlayer?.release()
            } catch (ignored: Exception) {}
            listenerPlayer = null
            currentStreamUrl = null
        }

        releaseLocks()

        currentRole = OmniSyncRole.IDLE
        currentHost = null
        currentHostRoomName = null
        currentTrackTitle = ""
        currentTrackArtist = ""
        isStreamPlaying = false

        notifyRoleChanged(OmniSyncRole.IDLE)
        notifyPlaybackState(false)
        notifyTrackInfo("", "")
    }
}

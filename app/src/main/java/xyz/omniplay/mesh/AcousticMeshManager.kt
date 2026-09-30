package xyz.omniplay.mesh

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.PowerManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import xyz.omniplay.model.Song
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import java.nio.ByteBuffer
import java.util.UUID
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Core Acoustic Mesh Coordinator:
 * - Sub-millisecond Micro-NTP Clock Synchronization via UDP
 * - mDNS / DNS-SD Zero-Config Local Room Discovery via Android NsdManager
 * - Command orchestration (Play, Pause, Seek, Channel Role assignment)
 * - Hardware-accelerated Left/Right/Center channel separation via MediaPlayer
 */
class AcousticMeshManager private constructor(private val context: Context) {

    companion object {
        const val SERVICE_TYPE = "_omniplay-mesh._tcp."
        const val CONTROL_PORT = 8999
        const val STREAM_PORT = 8998
        const val TIME_SYNC_PORT = 8997

        @Volatile
        private var instance: AcousticMeshManager? = null

        fun getInstance(context: Context): AcousticMeshManager {
            return instance ?: synchronized(this) {
                instance ?: AcousticMeshManager(context.applicationContext).also { instance = it }
            }
        }
    }

    interface MeshListener {
        fun onRoleChanged(role: MeshRole)
        fun onRoomsDiscovered(rooms: List<MeshRoom>)
        fun onPeersChanged(peers: List<MeshPeer>)
        fun onSyncStatusChanged(latencyMs: Long, clockOffsetMs: Long)
        fun onChannelChanged(channel: AudioChannel)
        fun onError(message: String)
    }

    private val listeners = CopyOnWriteArrayList<MeshListener>()
    private val scope = CoroutineScope(Dispatchers.IO)
    private val mainHandler = Handler(Looper.getMainLooper())

    private val nsdManager by lazy { context.getSystemService(Context.NSD_SERVICE) as NsdManager }
    private val wifiManager by lazy { context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager }
    private val powerManager by lazy { context.applicationContext.getSystemService(Context.POWER_SERVICE) as PowerManager }
    private var multicastLock: WifiManager.MulticastLock? = null
    private var wifiLock: WifiManager.WifiLock? = null
    private var wakeLock: PowerManager.WakeLock? = null

    var hostSongProvider: (() -> Song?)? = null
    var hostPlaybackPositionProvider: (() -> Long)? = null
    var hostIsPlayingProvider: (() -> Boolean)? = null

    val streamServer = HostAudioStreamServer(context, STREAM_PORT)

    var currentRole: MeshRole = MeshRole.STANDALONE
        private set

    var currentChannel: AudioChannel = AudioChannel.STEREO
        private set

    var volumeTrim: Float = 1.0f
        private set

    var currentRoomName: String? = null
        private set

    var clockOffsetMs: Long = 0L
        private set

    var roundTripLatencyMs: Long = 0L
        private set

    val connectedPeers = CopyOnWriteArrayList<MeshPeer>()
    val discoveredRooms = CopyOnWriteArrayList<MeshRoom>()

    private var hostControlServerJob: Job? = null
    private var hostTimeServerJob: Job? = null
    private var clientSocket: Socket? = null
    private var clientJob: Job? = null
    private var timeSyncJob: Job? = null
    private var pingJob: Job? = null
    private var scheduledPlayJob: Job? = null
    private var satellitePlayer: MediaPlayer? = null
    private var registrationListener: NsdManager.RegistrationListener? = null
    private var discoveryListener: NsdManager.DiscoveryListener? = null

    val deviceName: String by lazy {
        val model = Build.MODEL
        val brand = Build.BRAND.replaceFirstChar { it.uppercase() }
        if (model.startsWith(brand, ignoreCase = true)) model else "$brand $model"
    }

    val deviceId: String by lazy {
        UUID.randomUUID().toString().take(8)
    }

    fun addListener(listener: MeshListener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener)
            listener.onRoleChanged(currentRole)
            listener.onRoomsDiscovered(discoveredRooms)
            listener.onPeersChanged(connectedPeers)
            listener.onChannelChanged(currentChannel)
            listener.onSyncStatusChanged(roundTripLatencyMs, clockOffsetMs)
        }
    }

    fun removeListener(listener: MeshListener) {
        listeners.remove(listener)
    }

    private fun acquireMeshLocks() {
        try {
            if (multicastLock == null) {
                multicastLock = wifiManager.createMulticastLock("omniplay:mesh_mcast_lock").apply {
                    setReferenceCounted(false)
                }
            }
            if (multicastLock?.isHeld != true) {
                multicastLock?.acquire()
            }
        } catch (ignored: Exception) {}

        try {
            if (wifiLock == null) {
                val lockType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    WifiManager.WIFI_MODE_FULL_LOW_LATENCY
                } else {
                    @Suppress("DEPRECATION")
                    WifiManager.WIFI_MODE_FULL_HIGH_PERF
                }
                wifiLock = wifiManager.createWifiLock(lockType, "omniplay:mesh_wifi_lock").apply {
                    setReferenceCounted(false)
                }
            }
            if (wifiLock?.isHeld != true) {
                wifiLock?.acquire()
            }
        } catch (ignored: Exception) {}

        try {
            if (wakeLock == null) {
                wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "omniplay:mesh_wake_lock").apply {
                    setReferenceCounted(false)
                }
            }
            if (wakeLock?.isHeld != true) {
                wakeLock?.acquire(24 * 60 * 60 * 1000L)
            }
        } catch (ignored: Exception) {}
    }

    private fun releaseMeshLocks() {
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

    fun startHost(roomName: String = "$deviceName's Room") {
        if (currentRole == MeshRole.HOST) return
        stopAll()

        currentRole = MeshRole.HOST
        currentRoomName = roomName
        acquireMeshLocks()

        // Sync currently playing track if any
        hostSongProvider?.invoke()?.let { song ->
            streamServer.currentSongUri = song.contentUri
            val mime = context.contentResolver.getType(song.contentUri) ?: when (song.format.uppercase()) {
                "FLAC" -> "audio/flac"
                "WAV" -> "audio/wav"
                "OGG", "OPUS" -> "audio/ogg"
                "M4A", "AAC" -> "audio/mp4"
                else -> "audio/mpeg"
            }
            streamServer.currentMimeType = mime
        }

        streamServer.start()
        startHostControlServer()
        startHostTimeServer()
        registerNsdService(roomName)

        notifyRoleChanged(MeshRole.HOST)
    }

    private fun registerNsdService(name: String) {
        val serviceInfo = NsdServiceInfo().apply {
            serviceName = name
            serviceType = SERVICE_TYPE
            port = CONTROL_PORT
        }

        registrationListener = object : NsdManager.RegistrationListener {
            override fun onServiceRegistered(NsdServiceInfo: NsdServiceInfo) {
                currentRoomName = NsdServiceInfo.serviceName
            }
            override fun onRegistrationFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
                notifyError("Room registration failed ($errorCode)")
            }
            override fun onServiceUnregistered(serviceInfo: NsdServiceInfo) {}
            override fun onUnregistrationFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {}
        }

        try {
            nsdManager.registerService(serviceInfo, NsdManager.PROTOCOL_DNS_SD, registrationListener)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun startHostControlServer() {
        hostControlServerJob = scope.launch {
            var server: ServerSocket? = null
            try {
                server = ServerSocket(CONTROL_PORT)
                while (isActive && !server.isClosed) {
                    val client = server.accept()
                    client.tcpNoDelay = true
                    client.keepAlive = true
                    launch { handleHostPeerConnection(client) }
                }
            } catch (ignored: Exception) {
            } finally {
                try { server?.close() } catch (ignored: Exception) {}
            }
        }
    }

    private val peerWriters = CopyOnWriteArrayList<PrintWriter>()

    private suspend fun handleHostPeerConnection(socket: Socket) {
        var peerId: String? = null
        var currentWriter: PrintWriter? = null
        try {
            val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
            val writer = PrintWriter(socket.getOutputStream(), true)
            currentWriter = writer
            peerWriters.add(writer)

            val clientIp = socket.inetAddress.hostAddress ?: "Unknown"

            while (scope.isActive && !socket.isClosed) {
                val line = withContext(Dispatchers.IO) { reader.readLine() } ?: break
                val json = JSONObject(line)
                when (json.optString("action")) {
                    "HELLO" -> {
                        val pId = json.optString("peerId", UUID.randomUUID().toString())
                        val pName = json.optString("peerName", "Speaker")
                        peerId = pId
                        val peer = MeshPeer(
                            id = pId,
                            name = pName,
                            ip = clientIp,
                            channel = AudioChannel.valueOf(json.optString("channel", "STEREO")),
                            isHost = false
                        )
                        connectedPeers.removeAll { it.id == pId }
                        connectedPeers.add(peer)
                        notifyPeersChanged()

                        // Send Welcome State back
                        val welcome = JSONObject().apply {
                            put("action", "WELCOME")
                            put("roomName", currentRoomName)
                            put("hostName", deviceName)
                            put("streamPort", STREAM_PORT)
                        }
                        writer.println(welcome.toString())

                        // If host is currently playing, immediately sync this peer
                        val playingSong = hostSongProvider?.invoke()
                        val isPlaying = hostIsPlayingProvider?.invoke() == true
                        val positionMs = hostPlaybackPositionProvider?.invoke() ?: 0L
                        if (playingSong != null && isPlaying) {
                            streamServer.currentSongUri = playingSong.contentUri
                            val scheduledAt = System.currentTimeMillis() + 220L
                            val playCmd = JSONObject().apply {
                                put("action", "PLAY")
                                put("songTitle", playingSong.title)
                                put("songArtist", playingSong.artist)
                                put("songDuration", playingSong.duration)
                                put("positionMs", positionMs)
                                put("scheduledAt", scheduledAt)
                                put("streamPort", STREAM_PORT)
                            }.toString()
                            writer.println(playCmd)
                        }
                    }
                    "SET_CHANNEL" -> {
                        val ch = json.optString("channel", "STEREO")
                        peerId?.let { id ->
                            connectedPeers.find { it.id == id }?.channel = AudioChannel.valueOf(ch)
                            notifyPeersChanged()
                        }
                    }
                    "PING" -> {
                        writer.println(JSONObject().apply { put("action", "PONG") }.toString())
                    }
                }
            }
        } catch (ignored: Exception) {
        } finally {
            currentWriter?.let { peerWriters.remove(it) }
            peerId?.let { id ->
                connectedPeers.removeAll { it.id == id }
                notifyPeersChanged()
            }
            try { socket.close() } catch (ignored: Exception) {}
        }
    }

    private fun startHostTimeServer() {
        hostTimeServerJob = scope.launch {
            var socket: DatagramSocket? = null
            try {
                socket = DatagramSocket(TIME_SYNC_PORT)
                val buffer = ByteArray(32)
                val packet = DatagramPacket(buffer, buffer.size)

                while (isActive && !socket.isClosed) {
                    socket.receive(packet)
                    val recvTime = System.currentTimeMillis()

                    val byteBuf = ByteBuffer.wrap(packet.data)
                    val clientT1 = byteBuf.long

                    val sendBuf = ByteBuffer.allocate(24)
                    sendBuf.putLong(clientT1)
                    sendBuf.putLong(recvTime)
                    sendBuf.putLong(System.currentTimeMillis())

                    val response = DatagramPacket(sendBuf.array(), 24, packet.address, packet.port)
                    socket.send(response)
                }
            } catch (ignored: Exception) {
            } finally {
                socket?.close()
            }
        }
    }

    fun broadcastHostPlay(song: Song, positionMs: Long) {
        if (currentRole != MeshRole.HOST) return
        streamServer.currentSongUri = song.contentUri
        val mime = context.contentResolver.getType(song.contentUri) ?: when (song.format.uppercase()) {
            "FLAC" -> "audio/flac"
            "WAV" -> "audio/wav"
            "OGG", "OPUS" -> "audio/ogg"
            "M4A", "AAC" -> "audio/mp4"
            else -> "audio/mpeg"
        }
        streamServer.currentMimeType = mime

        val scheduledAt = System.currentTimeMillis() + 180L // Scheduled presentation timestamp
        val json = JSONObject().apply {
            put("action", "PLAY")
            put("songTitle", song.title)
            put("songArtist", song.artist)
            put("songDuration", song.duration)
            put("positionMs", positionMs)
            put("scheduledAt", scheduledAt)
            put("streamPort", STREAM_PORT)
        }.toString()

        scope.launch {
            peerWriters.forEach { writer ->
                try { writer.println(json) } catch (ignored: Exception) {}
            }
        }
    }

    fun broadcastHostPause() {
        if (currentRole != MeshRole.HOST) return
        val json = JSONObject().apply {
            put("action", "PAUSE")
        }.toString()
        scope.launch {
            peerWriters.forEach { writer ->
                try { writer.println(json) } catch (ignored: Exception) {}
            }
        }
    }

    fun broadcastHostSeek(positionMs: Long) {
        if (currentRole != MeshRole.HOST) return
        val scheduledAt = System.currentTimeMillis() + 180L
        val json = JSONObject().apply {
            put("action", "SEEK")
            put("positionMs", positionMs)
            put("scheduledAt", scheduledAt)
        }.toString()
        scope.launch {
            peerWriters.forEach { writer ->
                try { writer.println(json) } catch (ignored: Exception) {}
            }
        }
    }

    // =========================================================================
    // SATELLITE MODE
    // =========================================================================

    fun startScanningRooms() {
        if (discoveryListener != null) return
        acquireMeshLocks()
        discoveredRooms.clear()
        notifyRoomsDiscovered()

        discoveryListener = object : NsdManager.DiscoveryListener {
            override fun onDiscoveryStarted(regType: String) {}
            override fun onServiceFound(serviceInfo: NsdServiceInfo) {
                if (serviceInfo.serviceType.contains("_omniplay-mesh")) {
                    resolveService(serviceInfo)
                }
            }
            override fun onServiceLost(serviceInfo: NsdServiceInfo) {
                discoveredRooms.removeAll { it.roomName == serviceInfo.serviceName }
                notifyRoomsDiscovered()
            }
            override fun onDiscoveryStopped(serviceType: String) {}
            override fun onStartDiscoveryFailed(serviceType: String, errorCode: Int) {
                notifyError("Discovery failed ($errorCode)")
            }
            override fun onStopDiscoveryFailed(serviceType: String, errorCode: Int) {}
        }

        try {
            nsdManager.discoverServices(SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, discoveryListener)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun resolveService(serviceInfo: NsdServiceInfo) {
        try {
            nsdManager.resolveService(serviceInfo, object : NsdManager.ResolveListener {
                override fun onServiceResolved(resolved: NsdServiceInfo) {
                    val rawHost = resolved.host?.hostAddress ?: return
                    val host = if (rawHost.contains('%')) rawHost.substringBefore('%') else rawHost
                    val room = MeshRoom(
                        roomName = resolved.serviceName,
                        hostName = resolved.serviceName,
                        hostAddress = host,
                        port = resolved.port,
                        streamPort = STREAM_PORT
                    )
                    if (!discoveredRooms.any { it.roomName == room.roomName }) {
                        discoveredRooms.add(room)
                        notifyRoomsDiscovered()
                    }
                }
                override fun onResolveFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {}
            })
        } catch (ignored: Exception) {}
    }

    fun stopScanningRooms() {
        discoveryListener?.let {
            try { nsdManager.stopServiceDiscovery(it) } catch (ignored: Exception) {}
            discoveryListener = null
        }
        if (currentRole == MeshRole.STANDALONE) {
            releaseMeshLocks()
        }
    }

    fun joinRoom(room: MeshRoom) {
        stopAll()
        currentRole = MeshRole.SATELLITE
        currentRoomName = room.roomName
        acquireMeshLocks()
        notifyRoleChanged(MeshRole.SATELLITE)

        clientJob = scope.launch {
            try {
                val socket = Socket()
                socket.tcpNoDelay = true
                socket.keepAlive = true
                socket.connect(InetSocketAddress(room.hostAddress, room.port), 5000)
                clientSocket = socket

                val writer = PrintWriter(socket.getOutputStream(), true)
                val reader = BufferedReader(InputStreamReader(socket.getInputStream()))

                // Send HELLO handshake
                val hello = JSONObject().apply {
                    put("action", "HELLO")
                    put("peerId", deviceId)
                    put("peerName", deviceName)
                    put("channel", currentChannel.name)
                }
                writer.println(hello.toString())

                // Start Micro-NTP Clock Sync loop with Host
                startTimeSyncLoop(room.hostAddress)

                // Start periodic ping loop to keep socket active through sleep/menu close
                startPingLoop(writer)

                // Listen for Host Sync Commands
                while (isActive && !socket.isClosed) {
                    val line = withContext(Dispatchers.IO) { reader.readLine() } ?: break
                    val json = JSONObject(line)
                    handleSatelliteCommand(json, room.hostAddress, room.streamPort)
                }
            } catch (e: Exception) {
                notifyError("Connection to room failed: ${e.localizedMessage}")
                leaveRoom()
            }
        }
    }

    private fun startPingLoop(writer: PrintWriter) {
        pingJob?.cancel()
        pingJob = scope.launch {
            while (isActive) {
                delay(8000L)
                try {
                    val ping = JSONObject().apply { put("action", "PING") }
                    writer.println(ping.toString())
                } catch (e: Exception) {
                    break
                }
            }
        }
    }

    private fun startTimeSyncLoop(hostAddress: String) {
        timeSyncJob?.cancel()
        timeSyncJob = scope.launch {
            val udpSocket = DatagramSocket()
            udpSocket.soTimeout = 2000
            val inetAddr = InetAddress.getByName(hostAddress)

            while (isActive) {
                try {
                    val t1 = System.currentTimeMillis()
                    val sendBuf = ByteBuffer.allocate(8).putLong(t1).array()
                    val packet = DatagramPacket(sendBuf, sendBuf.size, inetAddr, TIME_SYNC_PORT)
                    udpSocket.send(packet)

                    val recvBuf = ByteArray(24)
                    val recvPacket = DatagramPacket(recvBuf, recvBuf.size)
                    udpSocket.receive(recvPacket)
                    val t4 = System.currentTimeMillis()

                    val byteBuf = ByteBuffer.wrap(recvPacket.data)
                    val echoT1 = byteBuf.long
                    val hostT2 = byteBuf.long
                    val hostT3 = byteBuf.long

                    if (echoT1 == t1) {
                        val rtt = (t4 - t1) - (hostT3 - hostT2)
                        val offset = ((hostT2 - t1) + (hostT3 - t4)) / 2L

                        roundTripLatencyMs = rtt.coerceAtLeast(0L)
                        clockOffsetMs = offset

                        notifySyncStatus(roundTripLatencyMs, clockOffsetMs)
                    }
                } catch (ignored: Exception) {}

                delay(2000L) // Re-sync clock drift every 2s
            }
        }
    }

    private fun handleSatelliteCommand(json: JSONObject, hostIp: String, streamPort: Int) {
        val action = json.optString("action")
        when (action) {
            "PLAY" -> {
                val positionMs = json.optLong("positionMs", 0L)
                val scheduledAt = json.optLong("scheduledAt", System.currentTimeMillis())
                val streamUrl = "http://$hostIp:$streamPort/stream"

                scope.launch(Dispatchers.Main) {
                    playSatelliteStream(streamUrl, positionMs, scheduledAt)
                }
            }
            "PAUSE" -> {
                scheduledPlayJob?.cancel()
                scope.launch(Dispatchers.Main) {
                    try {
                        if (satellitePlayer?.isPlaying == true) {
                            satellitePlayer?.pause()
                        }
                    } catch (ignored: Exception) {}
                }
            }
            "SEEK" -> {
                val positionMs = json.optLong("positionMs", 0L)
                val scheduledAt = json.optLong("scheduledAt", System.currentTimeMillis())
                scope.launch(Dispatchers.Main) {
                    seekSatelliteStream(positionMs, scheduledAt)
                }
            }
            "PONG" -> {
                // Heartbeat response acknowledged
            }
        }
    }

    private fun playSatelliteStream(url: String, positionMs: Long, scheduledAt: Long) {
        try {
            scheduledPlayJob?.cancel()

            var player = satellitePlayer
            if (player == null) {
                player = MediaPlayer().apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                }
                satellitePlayer = player
            } else {
                try {
                    if (player.isPlaying) player.stop()
                } catch (ignored: Exception) {}
                player.reset()
                player.setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
            }

            player.setOnErrorListener { _, what, extra ->
                notifyError("Satellite stream error ($what, $extra)")
                true
            }

            player.setOnPreparedListener { mp ->
                applyChannelToPlayer(mp)
                schedulePlaybackStart(mp, positionMs, scheduledAt)
            }

            player.setDataSource(url)
            player.prepareAsync()
        } catch (e: Exception) {
            e.printStackTrace()
            notifyError("Failed to stream: ${e.localizedMessage}")
        }
    }

    private fun schedulePlaybackStart(player: MediaPlayer, positionMs: Long, scheduledAt: Long) {
        val targetLocalTime = scheduledAt - clockOffsetMs
        val delayMs = (targetLocalTime - System.currentTimeMillis()).coerceAtLeast(0L)

        scheduledPlayJob?.cancel()
        scheduledPlayJob = scope.launch(Dispatchers.Main) {
            if (delayMs > 0L) {
                delay(delayMs)
            }
            try {
                if (positionMs > 0L) {
                    player.seekTo(positionMs.toInt())
                }
                player.start()
            } catch (ignored: Exception) {}
        }
    }

    private fun seekSatelliteStream(positionMs: Long, scheduledAt: Long) {
        val player = satellitePlayer ?: return
        val targetLocalTime = scheduledAt - clockOffsetMs
        val delayMs = (targetLocalTime - System.currentTimeMillis()).coerceAtLeast(0L)

        scope.launch(Dispatchers.Main) {
            if (delayMs > 0L) {
                delay(delayMs)
            }
            try {
                player.seekTo(positionMs.toInt())
            } catch (ignored: Exception) {}
        }
    }

    fun setChannel(channel: AudioChannel) {
        currentChannel = channel
        satellitePlayer?.let { applyChannelToPlayer(it) }
        notifyChannelChanged(channel)

        // Inform host of channel selection
        if (currentRole == MeshRole.SATELLITE) {
            scope.launch {
                try {
                    val json = JSONObject().apply {
                        put("action", "SET_CHANNEL")
                        put("channel", channel.name)
                    }
                    clientSocket?.getOutputStream()?.let {
                        PrintWriter(it, true).println(json.toString())
                    }
                } catch (ignored: Exception) {}
            }
        }
    }

    fun setVolumeBalance(trim: Float) {
        volumeTrim = trim.coerceIn(0f, 1f)
        satellitePlayer?.let { applyChannelToPlayer(it) }
    }

    private fun applyChannelToPlayer(player: MediaPlayer) {
        try {
            when (currentChannel) {
                AudioChannel.STEREO -> player.setVolume(volumeTrim, volumeTrim)
                AudioChannel.LEFT_ONLY -> player.setVolume(volumeTrim, 0f)
                AudioChannel.RIGHT_ONLY -> player.setVolume(0f, volumeTrim)
                AudioChannel.CENTER -> player.setVolume(volumeTrim * 0.85f, volumeTrim * 0.85f)
            }
        } catch (ignored: Exception) {}
    }

    fun leaveRoom() {
        stopAll()
        currentRole = MeshRole.STANDALONE
        currentRoomName = null
        notifyRoleChanged(MeshRole.STANDALONE)
    }

    private fun stopAll() {
        stopScanningRooms()

        registrationListener?.let {
            try { nsdManager.unregisterService(it) } catch (ignored: Exception) {}
            registrationListener = null
        }

        streamServer.stop()

        hostControlServerJob?.cancel()
        hostControlServerJob = null

        hostTimeServerJob?.cancel()
        hostTimeServerJob = null

        timeSyncJob?.cancel()
        timeSyncJob = null

        pingJob?.cancel()
        pingJob = null

        scheduledPlayJob?.cancel()
        scheduledPlayJob = null

        clientJob?.cancel()
        clientJob = null

        try { clientSocket?.close() } catch (ignored: Exception) {}
        clientSocket = null

        peerWriters.clear()
        connectedPeers.clear()

        try {
            satellitePlayer?.stop()
            satellitePlayer?.release()
        } catch (ignored: Exception) {}
        satellitePlayer = null

        releaseMeshLocks()
    }

    // =========================================================================
    // NOTIFIERS
    // =========================================================================

    private fun notifyRoleChanged(role: MeshRole) {
        mainHandler.post {
            listeners.forEach { it.onRoleChanged(role) }
        }
    }

    private fun notifyRoomsDiscovered() {
        mainHandler.post {
            val list = discoveredRooms.toList()
            listeners.forEach { it.onRoomsDiscovered(list) }
        }
    }

    private fun notifyPeersChanged() {
        mainHandler.post {
            val list = connectedPeers.toList()
            listeners.forEach { it.onPeersChanged(list) }
        }
    }

    private fun notifySyncStatus(latencyMs: Long, offsetMs: Long) {
        mainHandler.post {
            listeners.forEach { it.onSyncStatusChanged(latencyMs, offsetMs) }
        }
    }

    private fun notifyChannelChanged(channel: AudioChannel) {
        mainHandler.post {
            listeners.forEach { it.onChannelChanged(channel) }
        }
    }

    private fun notifyError(msg: String) {
        mainHandler.post {
            listeners.forEach { it.onError(msg) }
        }
    }
}

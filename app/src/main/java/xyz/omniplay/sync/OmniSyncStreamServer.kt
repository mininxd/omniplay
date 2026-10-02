package xyz.omniplay.sync

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.json.JSONObject
import xyz.omniplay.model.Song
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintWriter
import java.io.RandomAccessFile
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import java.net.URLDecoder
import java.util.UUID
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.math.min

/**
 * Unified HTTP audio streaming, status, and event server for OmniSync.
 * Operates entirely over standard HTTP (port 8998) with Range requests, SSE, and CORS.
 */
class OmniSyncStreamServer(
    private val context: Context,
    val port: Int = OmniSyncManager.PORT
) {
    private var serverSocket: ServerSocket? = null
    private var serverJob: Job? = null
    private var heartbeatJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val sseWriters = CopyOnWriteArrayList<PrintWriter>()
    private val webSocketSessions = CopyOnWriteArrayList<WebSocketSession>()
    private val activeSockets = CopyOnWriteArrayList<Socket>()

    var onPeerJoined: ((OmniSyncPeer) -> Unit)? = null
    var onPeerLeft: ((String) -> Unit)? = null

    var songProvider: (() -> Song?)? = null
    var isPlayingProvider: (() -> Boolean)? = null
    var positionProvider: (() -> Long)? = null

    @Volatile
    var currentSong: Song? = null

    @Volatile
    var currentSongUri: Uri? = null

    @Volatile
    var currentMimeType: String = "audio/mpeg"

    @Volatile
    var hostName: String = "Host"

    @Volatile
    var isPlaying: Boolean = false

    @Volatile
    var currentPositionMs: Long = 0L

    @Volatile
    var currentDurationMs: Long = 0L

    fun start() {
        stop()
        serverJob = scope.launch {
            try {
                val server = ServerSocket().apply {
                    reuseAddress = true
                    bind(InetSocketAddress(port))
                }
                serverSocket = server
                while (isActive && !server.isClosed) {
                    try {
                        val client = server.accept()
                        client.tcpNoDelay = true
                        try { client.setSoLinger(true, 2) } catch (ignored: Exception) {}
                        activeSockets.add(client)
                        launch(Dispatchers.IO) {
                            try {
                                handleClient(client)
                            } catch (ignored: Exception) {
                            } finally {
                                activeSockets.remove(client)
                                try { socketSafeClose(client) } catch (ignored: Exception) {}
                            }
                        }
                    } catch (e: Exception) {
                        if (!isActive || server.isClosed) break
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // Periodic heartbeat ping: keeps connection alive & synchronizes clock/drift
        heartbeatJob = scope.launch {
            while (isActive) {
                delay(1000L)
                if (webSocketSessions.isNotEmpty() || sseWriters.isNotEmpty()) {
                    try {
                        val song = currentSong ?: (try { songProvider?.invoke() } catch (e: Exception) { null })
                        val isLive = (try { isPlayingProvider?.invoke() } catch (e: Exception) { null }) ?: isPlaying
                        val pos = (try { positionProvider?.invoke() } catch (e: Exception) { null }) ?: currentPositionMs
                        val ping = JSONObject().apply {
                            put("action", "PING")
                            put("positionMs", pos)
                            put("isPlaying", isLive)
                            put("songId", song?.id ?: 0L)
                            put("timestamp", System.currentTimeMillis())
                        }.toString()
                        broadcastEvent(ping)
                    } catch (ignored: Exception) {}
                }
            }
        }
    }

    private fun socketSafeClose(socket: Socket) {
        try { socket.shutdownOutput() } catch (ignored: Exception) {}
        try { socket.close() } catch (ignored: Exception) {}
    }

    fun broadcastEvent(jsonString: String) {
        // 1. Broadcast to WebSocket clients (RFC 6455)
        for (session in webSocketSessions) {
            try {
                session.sendText(jsonString)
            } catch (e: Exception) {
                webSocketSessions.remove(session)
                session.peerId?.let { onPeerLeft?.invoke(it) }
                try { session.socket.close() } catch (ignored: Exception) {}
            }
        }

        // 2. Broadcast to legacy SSE clients
        val sseData = "data: $jsonString\n\n"
        for (writer in sseWriters) {
            try {
                writer.print(sseData)
                writer.flush()
            } catch (e: Exception) {
                sseWriters.remove(writer)
            }
        }
    }

    private fun handleClient(socket: Socket) {
        try {
            socket.soTimeout = 15000
            val rawInput = socket.getInputStream()
            val output = socket.getOutputStream()

            val requestLine = OmniWebSocket.readHttpLine(rawInput) ?: return
            val parts = requestLine.split(" ")
            if (parts.size < 2) return
            val method = parts[0]
            val path = parts[1]

            val isHeadRequest = method.equals("HEAD", ignoreCase = true)
            var rangeHeader: String? = null
            var upgradeHeader: String? = null
            var secWebSocketKey: String? = null

            var line = OmniWebSocket.readHttpLine(rawInput)
            while (!line.isNullOrEmpty()) {
                if (line.startsWith("Range:", ignoreCase = true)) {
                    rangeHeader = line.substringAfter(":").trim()
                } else if (line.startsWith("Upgrade:", ignoreCase = true)) {
                    upgradeHeader = line.substringAfter(":").trim()
                } else if (line.startsWith("Sec-WebSocket-Key:", ignoreCase = true)) {
                    secWebSocketKey = line.substringAfter(":").trim()
                }
                line = OmniWebSocket.readHttpLine(rawInput)
            }

            // CORS preflight
            if (method.equals("OPTIONS", ignoreCase = true)) {
                val cors = "HTTP/1.1 200 OK\r\nAccess-Control-Allow-Origin: *\r\nAccess-Control-Allow-Methods: GET, POST, OPTIONS\r\nAccess-Control-Allow-Headers: Range, Content-Type, Upgrade, Sec-WebSocket-Key, Sec-WebSocket-Version\r\nContent-Length: 0\r\nConnection: close\r\n\r\n"
                output.write(cors.toByteArray(Charsets.UTF_8))
                output.flush()
                socketSafeClose(socket)
                return
            }

            // WebSocket Upgrade Request (RFC 6455)
            if (upgradeHeader?.equals("websocket", ignoreCase = true) == true || path.startsWith("/ws")) {
                handleWebSocketSession(socket, rawInput, output, path, secWebSocketKey)
                return
            }

            // Real-time Server-Sent Events (SSE fallback)
            if (path.startsWith("/events")) {
                handleSseStream(socket, output, rawInput)
                return
            }

            // Peer Registration
            if (path.startsWith("/register")) {
                handleRegister(path, socket, output)
                socketSafeClose(socket)
                return
            }

            // Peer Unregistration
            if (path.startsWith("/unregister")) {
                handleUnregister(path, output)
                socketSafeClose(socket)
                return
            }

            // Status Polling / Inspection
            if (path.startsWith("/status")) {
                serveStatus(output, isHeadRequest)
                socketSafeClose(socket)
                return
            }

            // Audio Stream
            val song = currentSong ?: songProvider?.invoke()
            val uri = currentSongUri ?: song?.contentUri
            if (uri == null && song == null) {
                send404(output)
                socketSafeClose(socket)
                return
            }

            serveAudioStream(song, uri, output, rangeHeader, isHeadRequest)
            socketSafeClose(socket)
        } catch (ignored: Exception) {
            // Client disconnected or aborted
            try { socketSafeClose(socket) } catch (e: Exception) {}
        }
    }

    private fun handleWebSocketSession(
        socket: Socket,
        input: InputStream,
        output: OutputStream,
        path: String,
        secWebSocketKey: String?
    ) {
        if (secWebSocketKey.isNullOrEmpty()) {
            send404(output)
            socketSafeClose(socket)
            return
        }

        val acceptKey = OmniWebSocket.computeAcceptKey(secWebSocketKey)
        val headers = "HTTP/1.1 101 Switching Protocols\r\n" +
                "Upgrade: websocket\r\n" +
                "Connection: Upgrade\r\n" +
                "Sec-WebSocket-Accept: $acceptKey\r\n\r\n"
        output.write(headers.toByteArray(Charsets.UTF_8))
        output.flush()

        socket.soTimeout = 0 // Keep connection open indefinitely!

        val session = WebSocketSession(socket, output)
        webSocketSessions.add(session)

        val clientIp = socket.inetAddress?.hostAddress ?: "127.0.0.1"
        val query = if (path.contains("?")) path.substringAfter("?") else ""
        val params = query.split("&").associate {
            val p = it.split("=")
            if (p.size == 2) {
                p[0] to try { URLDecoder.decode(p[1], "UTF-8") } catch (e: Exception) { p[1] }
            } else "" to ""
        }
        val pId = params["id"]?.takeIf { it.isNotEmpty() } ?: clientIp
        val pName = params["name"]?.takeIf { it.isNotEmpty() } ?: "Listener ($clientIp)"

        session.peerId = pId
        session.peerName = pName

        val peer = OmniSyncPeer(pId, pName, clientIp)
        onPeerJoined?.invoke(peer)

        // Send initial state event immediately as a WebSocket text frame
        val song = currentSong ?: (try { songProvider?.invoke() } catch (e: Exception) { null })
        val isLive = isPlaying || (try { isPlayingProvider?.invoke() == true } catch (e: Exception) { false })
        val pos = (try { positionProvider?.invoke() } catch (e: Exception) { null }) ?: currentPositionMs
        val dur = song?.duration ?: currentDurationMs

        val initEvent = JSONObject().apply {
            put("action", "WELCOME")
            put("hostName", hostName)
            put("songId", song?.id ?: 0L)
            put("title", song?.title ?: "")
            put("artist", song?.artist ?: "")
            put("format", song?.format ?: "")
            put("quality", song?.audioQuality ?: "")
            put("isHiRes", song?.isHiRes ?: false)
            put("positionMs", pos)
            put("durationMs", dur)
            put("isPlaying", isLive)
            put("timestamp", System.currentTimeMillis())
        }.toString()
        session.sendText(initEvent)

        try {
            while (!socket.isClosed) {
                val frame = OmniWebSocket.readFrame(input) ?: break
                when (frame.opcode) {
                    OmniWebSocket.OPCODE_TEXT -> {
                        val msg = frame.asText()
                        val json = try { JSONObject(msg) } catch (e: Exception) { null }
                        if (json != null) {
                            when (json.optString("action")) {
                                "REGISTER" -> {
                                    val rId = json.optString("id", pId)
                                    val rName = json.optString("name", pName)
                                    session.peerId = rId
                                    session.peerName = rName
                                    onPeerJoined?.invoke(OmniSyncPeer(rId, rName, clientIp))
                                }
                                "PING" -> {
                                    val clientTime = json.optLong("clientTime", 0L)
                                    val pong = JSONObject().apply {
                                        put("action", "PONG")
                                        if (clientTime > 0L) {
                                            put("clientTime", clientTime)
                                        }
                                    }.toString()
                                    session.sendText(pong)
                                }
                                "PONG" -> {
                                    // Peer responded to heartbeat
                                }
                            }
                        }
                    }
                    OmniWebSocket.OPCODE_PING -> {
                        session.sendPong(frame.payload)
                    }
                    OmniWebSocket.OPCODE_PONG -> {}
                    OmniWebSocket.OPCODE_CLOSE -> {
                        session.sendClose()
                        break
                    }
                }
            }
        } catch (ignored: Exception) {
        } finally {
            webSocketSessions.remove(session)
            session.peerId?.let { onPeerLeft?.invoke(it) }
            try { socket.close() } catch (ignored: Exception) {}
        }
    }

    private fun handleSseStream(socket: Socket, output: OutputStream, input: InputStream) {
        socket.soTimeout = 0 // Keep-alive indefinitely for event stream
        val header = "HTTP/1.1 200 OK\r\nContent-Type: text/event-stream; charset=utf-8\r\nCache-Control: no-cache\r\nConnection: keep-alive\r\nAccess-Control-Allow-Origin: *\r\n\r\n"
        output.write(header.toByteArray(Charsets.UTF_8))
        output.flush()

        val writer = PrintWriter(output, true)
        sseWriters.add(writer)

        val song = currentSong ?: (try { songProvider?.invoke() } catch (e: Exception) { null })
        val isLive = isPlaying || (try { isPlayingProvider?.invoke() == true } catch (e: Exception) { false })
        val pos = (try { positionProvider?.invoke() } catch (e: Exception) { null }) ?: currentPositionMs
        val dur = song?.duration ?: currentDurationMs

        // Send initial state event immediately
        val initEvent = JSONObject().apply {
            put("action", "WELCOME")
            put("hostName", hostName)
            put("songId", song?.id ?: 0L)
            put("title", song?.title ?: "")
            put("artist", song?.artist ?: "")
            put("format", song?.format ?: "")
            put("quality", song?.audioQuality ?: "")
            put("isHiRes", song?.isHiRes ?: false)
            put("positionMs", pos)
            put("durationMs", dur)
            put("isPlaying", isLive)
        }.toString()
        writer.print("data: $initEvent\n\n")
        writer.flush()

        try {
            while (!socket.isClosed) {
                val nextLine = OmniWebSocket.readHttpLine(input)
                if (nextLine == null) break
            }
        } catch (ignored: Exception) {
        } finally {
            sseWriters.remove(writer)
            try { socket.close() } catch (ignored: Exception) {}
        }
    }

    private fun handleRegister(path: String, socket: Socket, output: OutputStream) {
        try {
            val query = if (path.contains("?")) path.substringAfter("?") else ""
            val params = query.split("&").associate {
                val p = it.split("=")
                if (p.size == 2) {
                    p[0] to try { URLDecoder.decode(p[1], "UTF-8") } catch (e: Exception) { p[1] }
                } else "" to ""
            }
            val clientIp = socket.inetAddress.hostAddress ?: "127.0.0.1"
            val pId = params["id"]?.takeIf { it.isNotEmpty() } ?: clientIp
            val pName = params["name"]?.takeIf { it.isNotEmpty() } ?: "Listener ($clientIp)"

            val peer = OmniSyncPeer(pId, pName, clientIp)
            onPeerJoined?.invoke(peer)

            val song = currentSong ?: (try { songProvider?.invoke() } catch (e: Exception) { null })
            val isLive = isPlaying || (try { isPlayingProvider?.invoke() == true } catch (e: Exception) { false })
            val pos = (try { positionProvider?.invoke() } catch (e: Exception) { null }) ?: currentPositionMs
            val dur = song?.duration ?: currentDurationMs

            val statusJson = JSONObject().apply {
                put("status", "ok")
                put("hostName", hostName)
                put("songId", song?.id ?: 0L)
                put("title", song?.title ?: "")
                put("artist", song?.artist ?: "")
                put("duration", dur)
                put("position", pos)
                put("isPlaying", isLive)
            }.toString()

            val bytes = statusJson.toByteArray(Charsets.UTF_8)
            val headers = "HTTP/1.1 200 OK\r\nContent-Type: application/json; charset=utf-8\r\nAccess-Control-Allow-Origin: *\r\nContent-Length: ${bytes.size}\r\nConnection: close\r\n\r\n"
            output.write(headers.toByteArray(Charsets.UTF_8))
            output.write(bytes)
            output.flush()
        } catch (e: Exception) {
            e.printStackTrace()
            try {
                val fallback = "{\"status\":\"ok\"}".toByteArray(Charsets.UTF_8)
                val headers = "HTTP/1.1 200 OK\r\nContent-Type: application/json; charset=utf-8\r\nAccess-Control-Allow-Origin: *\r\nContent-Length: ${fallback.size}\r\nConnection: close\r\n\r\n"
                output.write(headers.toByteArray(Charsets.UTF_8))
                output.write(fallback)
                output.flush()
            } catch (ignored: Exception) {}
        }
    }

    private fun handleUnregister(path: String, output: OutputStream) {
        try {
            val query = if (path.contains("?")) path.substringAfter("?") else ""
            val params = query.split("&").associate {
                val p = it.split("=")
                if (p.size == 2) {
                    p[0] to try { URLDecoder.decode(p[1], "UTF-8") } catch (e: Exception) { p[1] }
                } else "" to ""
            }
            params["id"]?.let { onPeerLeft?.invoke(it) }

            val res = "{\"status\":\"ok\"}".toByteArray(Charsets.UTF_8)
            val headers = "HTTP/1.1 200 OK\r\nContent-Type: application/json; charset=utf-8\r\nAccess-Control-Allow-Origin: *\r\nContent-Length: ${res.size}\r\nConnection: close\r\n\r\n"
            output.write(headers.toByteArray(Charsets.UTF_8))
            output.write(res)
            output.flush()
        } catch (ignored: Exception) {}
    }

    private fun serveStatus(output: OutputStream, isHeadRequest: Boolean) {
        try {
            val song = currentSong ?: (try { songProvider?.invoke() } catch (e: Exception) { null })
            val isLive = isPlaying || (try { isPlayingProvider?.invoke() == true } catch (e: Exception) { false })
            val pos = (try { positionProvider?.invoke() } catch (e: Exception) { null }) ?: currentPositionMs
            val dur = song?.duration ?: currentDurationMs

            val statusJson = JSONObject().apply {
                put("status", "ok")
                put("hostName", hostName)
                put("songId", song?.id ?: 0L)
                put("title", song?.title ?: "")
                put("artist", song?.artist ?: "")
                put("duration", dur)
                put("position", pos)
                put("isPlaying", isLive)
            }.toString()

            val bytes = statusJson.toByteArray(Charsets.UTF_8)
            val headers = "HTTP/1.1 200 OK\r\nContent-Type: application/json; charset=utf-8\r\nAccess-Control-Allow-Origin: *\r\nContent-Length: ${bytes.size}\r\nConnection: close\r\n\r\n"
            output.write(headers.toByteArray(Charsets.UTF_8))
            if (!isHeadRequest) {
                output.write(bytes)
            }
            output.flush()
        } catch (e: Exception) {
            e.printStackTrace()
            try {
                val err = "{\"status\":\"ok\",\"hostName\":\"$hostName\",\"position\":0,\"isPlaying\":false}".toByteArray(Charsets.UTF_8)
                val headers = "HTTP/1.1 200 OK\r\nContent-Type: application/json; charset=utf-8\r\nAccess-Control-Allow-Origin: *\r\nContent-Length: ${err.size}\r\nConnection: close\r\n\r\n"
                output.write(headers.toByteArray(Charsets.UTF_8))
                output.write(err)
                output.flush()
            } catch (ignored: Exception) {}
        }
    }

    private fun serveAudioStream(
        song: Song?,
        uri: Uri?,
        output: OutputStream,
        rangeHeader: String?,
        isHeadRequest: Boolean
    ) {
        val file = song?.filePath?.let { if (it.isNotEmpty()) File(it) else null }
        val canReadFile = file != null && file.exists() && file.canRead() && file.length() > 0L

        val totalBytes: Long = if (canReadFile) {
            file!!.length()
        } else if (song != null && song.fileSize > 0L) {
            song.fileSize
        } else if (uri != null) {
            getFileSize(uri)
        } else {
            -1L
        }

        var startByte = 0L
        var endByte = if (totalBytes > 0L) totalBytes - 1L else -1L
        var isRange = false

        if (rangeHeader != null && rangeHeader.startsWith("bytes=")) {
            isRange = true
            val rangeVal = rangeHeader.removePrefix("bytes=").trim()
            val rParts = rangeVal.split("-")
            if (rParts.isNotEmpty() && rParts[0].isNotEmpty()) {
                startByte = rParts[0].toLongOrNull() ?: 0L
            }
            if (rParts.size > 1 && rParts[1].isNotEmpty()) {
                endByte = rParts[1].toLongOrNull() ?: endByte
            }
        }

        if (endByte < startByte && totalBytes > 0L) {
            endByte = totalBytes - 1L
        }

        val contentLength = if (endByte >= startByte) (endByte - startByte + 1L) else -1L

        val ext = song?.format?.ifEmpty { song.filePath.substringAfterLast('.', "") }?.uppercase()
        val effectiveMime = when (ext) {
            "FLAC" -> "audio/flac"
            "WAV" -> "audio/wav"
            "OGG" -> "audio/ogg"
            "OPUS" -> "audio/opus"
            "M4A", "AAC" -> "audio/mp4"
            "MP3" -> "audio/mpeg"
            else -> {
                val mimeFromResolver = uri?.let {
                    try { context.contentResolver.getType(it) } catch (e: Exception) { null }
                }
                mimeFromResolver ?: currentMimeType
            }
        }

        val statusLine = if (isRange) "HTTP/1.1 206 Partial Content\r\n" else "HTTP/1.1 200 OK\r\n"
        val headers = StringBuilder().apply {
            append(statusLine)
            append("Content-Type: $effectiveMime\r\n")
            append("Accept-Ranges: bytes\r\n")
            append("Access-Control-Allow-Origin: *\r\n")
            if (contentLength > 0L) {
                append("Content-Length: $contentLength\r\n")
            }
            if (isRange && totalBytes > 0L) {
                append("Content-Range: bytes $startByte-$endByte/$totalBytes\r\n")
            }
            append("Connection: close\r\n")
            append("\r\n")
        }.toString()

        output.write(headers.toByteArray(Charsets.UTF_8))
        output.flush()

        if (isHeadRequest) return

        var streamHandled = false

        // 1. Primary: Direct RandomAccessFile if direct file is accessible (instant O(1) seek)
        if (canReadFile && file != null) {
            try {
                RandomAccessFile(file, "r").use { raf ->
                    if (startByte > 0L) {
                        raf.seek(startByte)
                    }
                    val buffer = ByteArray(65536)
                    var remaining = if (contentLength > 0L) contentLength else Long.MAX_VALUE
                    while (remaining > 0L) {
                        val toRead = min(buffer.size.toLong(), remaining).toInt()
                        val read = raf.read(buffer, 0, toRead)
                        if (read == -1) break
                        output.write(buffer, 0, read)
                        remaining -= read
                    }
                    output.flush()
                    streamHandled = true
                }
            } catch (ignored: Exception) {}
        }

        if (streamHandled) return

        // 2. Secondary: ParcelFileDescriptor via ContentResolver (Scoped Storage safe, seekable channel)
        if (uri != null && uri != Uri.EMPTY) {
            try {
                context.contentResolver.openFileDescriptor(uri, "r")?.use { pfd ->
                    FileInputStream(pfd.fileDescriptor).use { fis ->
                        if (startByte > 0L) {
                            try {
                                fis.channel.position(startByte)
                            } catch (e: Exception) {
                                skipBytes(fis, startByte)
                            }
                        }
                        writeStreamToOutput(fis, output, contentLength)
                        streamHandled = true
                    }
                }
            } catch (ignored: Exception) {}
        }

        if (streamHandled) return

        // 3. Fallback: openInputStream via ContentResolver
        if (uri != null && uri != Uri.EMPTY) {
            try {
                context.contentResolver.openInputStream(uri)?.use { inputStream ->
                    if (startByte > 0L) {
                        skipBytes(inputStream, startByte)
                    }
                    writeStreamToOutput(inputStream, output, contentLength)
                    streamHandled = true
                }
            } catch (ignored: Exception) {}
        }
    }

    private fun skipBytes(input: InputStream, bytesToSkip: Long) {
        var remaining = bytesToSkip
        val tempBuf = ByteArray(8192)
        while (remaining > 0L) {
            val skipped = input.skip(remaining)
            if (skipped <= 0L) {
                val read = input.read(tempBuf, 0, min(tempBuf.size.toLong(), remaining).toInt())
                if (read == -1) break
                remaining -= read
            } else {
                remaining -= skipped
            }
        }
    }

    private fun writeStreamToOutput(input: InputStream, output: OutputStream, contentLength: Long) {
        val buffer = ByteArray(65536)
        var bytesRemaining = if (contentLength > 0L) contentLength else Long.MAX_VALUE
        while (bytesRemaining > 0L) {
            val toRead = min(buffer.size.toLong(), bytesRemaining).toInt()
            val read = input.read(buffer, 0, toRead)
            if (read == -1) break
            output.write(buffer, 0, read)
            bytesRemaining -= read
        }
        output.flush()
    }

    private fun getFileSize(uri: Uri): Long {
        if (uri.scheme == "file") {
            uri.path?.let {
                val f = File(it)
                if (f.exists()) return f.length()
            }
        }

        try {
            context.contentResolver.openFileDescriptor(uri, "r")?.use {
                if (it.statSize > 0) return it.statSize
            }
        } catch (ignored: Exception) {}

        try {
            context.contentResolver.query(uri, arrayOf(OpenableColumns.SIZE), null, null, null)?.use { cursor ->
                val idx = cursor.getColumnIndex(OpenableColumns.SIZE)
                if (idx != -1 && cursor.moveToFirst()) {
                    val size = cursor.getLong(idx)
                    if (size > 0) return size
                }
            }
        } catch (ignored: Exception) {}

        return -1L
    }

    private fun send404(output: OutputStream) {
        val response = "HTTP/1.1 404 Not Found\r\nContent-Length: 0\r\nConnection: close\r\n\r\n"
        output.write(response.toByteArray(Charsets.UTF_8))
        output.flush()
    }

    fun stop() {
        heartbeatJob?.cancel()
        heartbeatJob = null

        val stopMsg = JSONObject().apply { put("action", "STOP") }.toString()
        for (session in webSocketSessions) {
            try {
                session.sendText(stopMsg)
                session.sendClose()
            } catch (ignored: Exception) {}
        }
        webSocketSessions.clear()

        for (writer in sseWriters) {
            try {
                writer.print("data: {\"action\":\"STOP\"}\n\n")
                writer.flush()
            } catch (ignored: Exception) {}
        }
        sseWriters.clear()

        for (s in activeSockets) {
            try {
                s.close()
            } catch (ignored: Exception) {}
        }
        activeSockets.clear()

        try {
            serverSocket?.close()
            serverSocket = null
            serverJob?.cancel()
            serverJob = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

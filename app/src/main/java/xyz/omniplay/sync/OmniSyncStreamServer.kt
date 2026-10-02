package xyz.omniplay.sync

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.json.JSONObject
import xyz.omniplay.model.Song
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.io.OutputStream
import java.io.RandomAccessFile
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket
import kotlin.math.min

/**
 * Embedded HTTP audio streaming and status server for OmniSync.
 * Streams the host's current local audio track to listener peers over local Wi-Fi / hotspot.
 */
class OmniSyncStreamServer(
    private val context: Context,
    val port: Int = OmniSyncManager.STREAM_PORT
) {
    private var serverSocket: ServerSocket? = null
    private var serverJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    @Volatile
    var currentSong: Song? = null

    @Volatile
    var currentSongUri: Uri? = null

    @Volatile
    var currentMimeType: String = "audio/mpeg"

    @Volatile
    var hostName: String = "OmniSync Host"

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
                        launch(Dispatchers.IO) {
                            try {
                                handleClient(client)
                            } catch (ignored: Exception) {
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
    }

    private fun handleClient(client: Socket) {
        try {
            client.use { socket ->
                socket.soTimeout = 15000
                val input = socket.getInputStream().bufferedReader()
                val output = socket.getOutputStream()

                val requestLine = input.readLine() ?: return
                val parts = requestLine.split(" ")
                if (parts.size < 2) return
                val method = parts[0]
                val path = parts[1]

                val isHeadRequest = method.equals("HEAD", ignoreCase = true)
                var line = input.readLine()
                var rangeHeader: String? = null
                while (!line.isNullOrEmpty()) {
                    if (line.startsWith("Range:", ignoreCase = true)) {
                        rangeHeader = line.substringAfter(":").trim()
                    }
                    line = input.readLine()
                }

                if (path.startsWith("/status")) {
                    serveStatus(output, isHeadRequest)
                    return
                }

                val song = currentSong
                val uri = currentSongUri ?: song?.contentUri
                if (uri == null && song == null) {
                    send404(output)
                    return
                }

                serveAudioStream(song, uri, output, rangeHeader, isHeadRequest)
            }
        } catch (ignored: Exception) {
            // Client disconnected or aborted
        }
    }

    private fun serveStatus(output: OutputStream, isHeadRequest: Boolean) {
        try {
            val statusJson = JSONObject().apply {
                put("status", "ok")
                put("hostName", hostName)
                put("title", currentSong?.title ?: "")
                put("artist", currentSong?.artist ?: "")
                put("duration", currentDurationMs)
                put("position", currentPositionMs)
                put("isPlaying", isPlaying)
            }.toString()

            val bytes = statusJson.toByteArray(Charsets.UTF_8)
            val headers = "HTTP/1.1 200 OK\r\nContent-Type: application/json; charset=utf-8\r\nContent-Length: ${bytes.size}\r\nConnection: close\r\n\r\n"
            output.write(headers.toByteArray(Charsets.UTF_8))
            if (!isHeadRequest) {
                output.write(bytes)
            }
            output.flush()
        } catch (ignored: Exception) {}
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

        // 1. Direct RandomAccessFile if direct file is accessible
        if (canReadFile) {
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
                }
                return
            } catch (ignored: Exception) {
                return
            }
        }

        // 2. ParcelFileDescriptor via ContentResolver
        if (uri != null) {
            var streamHandled = false
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

            if (streamHandled) return

            // 3. Fallback to openInputStream
            try {
                context.contentResolver.openInputStream(uri)?.use { inputStream ->
                    if (startByte > 0L) {
                        skipBytes(inputStream, startByte)
                    }
                    writeStreamToOutput(inputStream, output, contentLength)
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

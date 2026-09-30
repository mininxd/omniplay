package xyz.omniplay.mesh

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.io.InputStream
import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket

/**
 * Lightweight, zero-dependency embedded HTTP audio streamer (<10KB footprint).
 * Streams the host's current local audio track to satellite peers over local Wi-Fi or hotspot.
 */
class HostAudioStreamServer(
    private val context: Context,
    private val port: Int = 8998
) {
    private var serverSocket: ServerSocket? = null
    private var serverJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.IO)

    @Volatile
    var currentSongUri: Uri? = null

    @Volatile
    var currentMimeType: String = "audio/mpeg"

    fun start() {
        if (serverSocket != null) return
        serverJob = scope.launch {
            try {
                val server = ServerSocket(port)
                serverSocket = server
                while (isActive && !server.isClosed) {
                    try {
                        val client = server.accept()
                        launch { handleClient(client) }
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
                val input = socket.getInputStream().bufferedReader()
                val output = socket.getOutputStream()

                val requestLine = input.readLine() ?: return
                var line = input.readLine()
                var rangeHeader: String? = null
                while (!line.isNullOrEmpty()) {
                    if (line.startsWith("Range:", ignoreCase = true)) {
                        rangeHeader = line.substringAfter(":").trim()
                    }
                    line = input.readLine()
                }

                val uri = currentSongUri
                if (uri == null) {
                    send404(output)
                    return
                }

                serveAudioStream(uri, output, rangeHeader)
            }
        } catch (ignored: Exception) {
            // Client disconnected or aborted
        }
    }

    private fun serveAudioStream(uri: Uri, output: OutputStream, rangeHeader: String?) {
        var inputStream: InputStream? = null
        try {
            val totalBytes = getFileSize(uri)
            inputStream = context.contentResolver.openInputStream(uri)
            if (inputStream == null) {
                send404(output)
                return
            }

            var startByte = 0L
            var endByte = if (totalBytes > 0L) totalBytes - 1L else -1L

            if (rangeHeader != null && rangeHeader.startsWith("bytes=")) {
                val ranges = rangeHeader.removePrefix("bytes=").split("-")
                startByte = ranges[0].toLongOrNull() ?: 0L
                if (ranges.size > 1 && ranges[1].isNotEmpty()) {
                    endByte = ranges[1].toLongOrNull() ?: endByte
                }
            }

            if (startByte > 0) {
                inputStream.skip(startByte)
            }

            val contentLength = if (endByte >= startByte) (endByte - startByte + 1L) else -1L
            val isPartial = rangeHeader != null && startByte > 0

            val statusLine = if (isPartial) "HTTP/1.1 206 Partial Content\r\n" else "HTTP/1.1 200 OK\r\n"
            val headers = StringBuilder().apply {
                append(statusLine)
                append("Content-Type: $currentMimeType\r\n")
                append("Accept-Ranges: bytes\r\n")
                if (contentLength > 0L) {
                    append("Content-Length: $contentLength\r\n")
                }
                if (isPartial && totalBytes > 0L) {
                    append("Content-Range: bytes $startByte-$endByte/$totalBytes\r\n")
                }
                append("Connection: close\r\n")
                append("\r\n")
            }.toString()

            output.write(headers.toByteArray(Charsets.UTF_8))
            output.flush()

            val buffer = ByteArray(16384) // 16KB chunk
            var bytesRemaining = if (contentLength > 0L) contentLength else Long.MAX_VALUE
            while (bytesRemaining > 0L) {
                val readToTake = Math.min(buffer.size.toLong(), bytesRemaining).toInt()
                val read = inputStream.read(buffer, 0, readToTake)
                if (read == -1) break
                output.write(buffer, 0, read)
                bytesRemaining -= read
            }
            output.flush()
        } catch (ignored: Exception) {
        } finally {
            try {
                inputStream?.close()
            } catch (ignored: Exception) {}
        }
    }

    private fun getFileSize(uri: Uri): Long {
        return try {
            context.contentResolver.openFileDescriptor(uri, "r")?.use {
                it.statSize
            } ?: -1L
        } catch (e: Exception) {
            -1L
        }
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

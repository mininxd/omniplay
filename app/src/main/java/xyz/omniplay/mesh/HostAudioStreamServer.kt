package xyz.omniplay.mesh

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.io.FileInputStream
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
                        client.tcpNoDelay = true
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
                val isHeadRequest = requestLine.startsWith("HEAD", ignoreCase = true)
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

                serveAudioStream(uri, output, rangeHeader, isHeadRequest)
            }
        } catch (ignored: Exception) {
            // Client disconnected or aborted
        }
    }

    private fun serveAudioStream(uri: Uri, output: OutputStream, rangeHeader: String?, isHeadRequest: Boolean) {
        val totalBytes = getFileSize(uri)

        var startByte = 0L
        var endByte = if (totalBytes > 0L) totalBytes - 1L else -1L
        var isRange = false

        if (rangeHeader != null && rangeHeader.startsWith("bytes=")) {
            isRange = true
            val rangeVal = rangeHeader.removePrefix("bytes=").trim()
            val parts = rangeVal.split("-")
            if (parts.isNotEmpty() && parts[0].isNotEmpty()) {
                startByte = parts[0].toLongOrNull() ?: 0L
            }
            if (parts.size > 1 && parts[1].isNotEmpty()) {
                endByte = parts[1].toLongOrNull() ?: endByte
            }
        }

        if (endByte < startByte && totalBytes > 0L) {
            endByte = totalBytes - 1L
        }

        val contentLength = if (endByte >= startByte) (endByte - startByte + 1L) else -1L

        val statusLine = if (isRange) "HTTP/1.1 206 Partial Content\r\n" else "HTTP/1.1 200 OK\r\n"
        val headers = StringBuilder().apply {
            append(statusLine)
            append("Content-Type: $currentMimeType\r\n")
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

        // Prefer direct file descriptor seek
        val pfd = try {
            context.contentResolver.openFileDescriptor(uri, "r")
        } catch (e: Exception) {
            null
        }

        if (pfd != null) {
            pfd.use { fd ->
                FileInputStream(fd.fileDescriptor).use { fis ->
                    if (startByte > 0L) {
                        try {
                            fis.channel.position(startByte)
                        } catch (e: Exception) {
                            skipBytes(fis, startByte)
                        }
                    }
                    writeStreamToOutput(fis, output, contentLength)
                }
            }
        } else {
            var inputStream: InputStream? = null
            try {
                inputStream = context.contentResolver.openInputStream(uri)
                if (inputStream != null) {
                    if (startByte > 0L) {
                        skipBytes(inputStream, startByte)
                    }
                    writeStreamToOutput(inputStream, output, contentLength)
                }
            } finally {
                try { inputStream?.close() } catch (ignored: Exception) {}
            }
        }
    }

    private fun skipBytes(input: InputStream, bytesToSkip: Long) {
        var remaining = bytesToSkip
        val tempBuf = ByteArray(8192)
        while (remaining > 0L) {
            val skipped = input.skip(remaining)
            if (skipped <= 0L) {
                val read = input.read(tempBuf, 0, Math.min(tempBuf.size.toLong(), remaining).toInt())
                if (read == -1) break
                remaining -= read
            } else {
                remaining -= skipped
            }
        }
    }

    private fun writeStreamToOutput(input: InputStream, output: OutputStream, contentLength: Long) {
        val buffer = ByteArray(32768)
        var bytesRemaining = if (contentLength > 0L) contentLength else Long.MAX_VALUE
        while (bytesRemaining > 0L) {
            val toRead = Math.min(buffer.size.toLong(), bytesRemaining).toInt()
            val read = input.read(buffer, 0, toRead)
            if (read == -1) break
            output.write(buffer, 0, read)
            bytesRemaining -= read
        }
        output.flush()
    }

    private fun getFileSize(uri: Uri): Long {
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

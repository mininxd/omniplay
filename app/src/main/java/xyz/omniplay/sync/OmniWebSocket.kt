package xyz.omniplay.sync

import android.util.Base64
import java.io.EOFException
import java.io.InputStream
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.Socket
import java.security.MessageDigest
import java.security.SecureRandom
import kotlin.random.Random

/**
 * Lightweight, zero-dependency RFC 6455 WebSocket client, server sessions, and framing utilities
 * designed for real-time live synchronization in OmniSync.
 */
object OmniWebSocket {
    const val MAGIC_GUID = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11"

    const val OPCODE_CONTINUATION = 0x0
    const val OPCODE_TEXT = 0x1
    const val OPCODE_BINARY = 0x2
    const val OPCODE_CLOSE = 0x8
    const val OPCODE_PING = 0x9
    const val OPCODE_PONG = 0xA

    fun generateKey(): String {
        val nonce = ByteArray(16)
        SecureRandom().nextBytes(nonce)
        return Base64.encodeToString(nonce, Base64.NO_WRAP)
    }

    fun computeAcceptKey(clientKey: String): String {
        val input = clientKey.trim() + MAGIC_GUID
        val sha1 = MessageDigest.getInstance("SHA-1").digest(input.toByteArray(Charsets.UTF_8))
        return Base64.encodeToString(sha1, Base64.NO_WRAP)
    }

    fun encodeFrame(opcode: Int, payload: ByteArray, mask: Boolean): ByteArray {
        val len = payload.size
        val maskKey = if (mask) Random.nextBytes(4) else null

        val headerSize = when {
            len < 126 -> 2 + if (mask) 4 else 0
            len <= 65535 -> 4 + if (mask) 4 else 0
            else -> 10 + if (mask) 4 else 0
        }

        val frame = ByteArray(headerSize + len)
        frame[0] = (0x80 or (opcode and 0x0F)).toByte() // FIN = 1

        var offset = 1
        val maskBit = if (mask) 0x80 else 0x00

        when {
            len < 126 -> {
                frame[offset++] = (maskBit or len).toByte()
            }
            len <= 65535 -> {
                frame[offset++] = (maskBit or 126).toByte()
                frame[offset++] = ((len shr 8) and 0xFF).toByte()
                frame[offset++] = (len and 0xFF).toByte()
            }
            else -> {
                frame[offset++] = (maskBit or 127).toByte()
                val longLen = len.toLong()
                for (i in 7 downTo 0) {
                    frame[offset++] = ((longLen shr (i * 8)) and 0xFF).toByte()
                }
            }
        }

        if (mask && maskKey != null) {
            System.arraycopy(maskKey, 0, frame, offset, 4)
            offset += 4
            for (i in 0 until len) {
                frame[offset + i] = (payload[i].toInt() xor maskKey[i % 4].toInt()).toByte()
            }
        } else {
            System.arraycopy(payload, 0, frame, offset, len)
        }

        return frame
    }

    fun readFrame(input: InputStream): WebSocketFrame? {
        val b0 = input.read()
        if (b0 == -1) return null
        val fin = (b0 and 0x80) != 0
        val opcode = b0 and 0x0F

        val b1 = input.read()
        if (b1 == -1) return null
        val hasMask = (b1 and 0x80) != 0
        var payloadLen = (b1 and 0x7F).toLong()

        if (payloadLen == 126L) {
            val b2 = input.read()
            val b3 = input.read()
            if (b2 == -1 || b3 == -1) return null
            payloadLen = (((b2 and 0xFF) shl 8) or (b3 and 0xFF)).toLong()
        } else if (payloadLen == 127L) {
            var l = 0L
            for (i in 0..7) {
                val b = input.read()
                if (b == -1) return null
                l = (l shl 8) or (b.toLong() and 0xFFL)
            }
            payloadLen = l
        }

        if (payloadLen > 10 * 1024 * 1024L) {
            throw IllegalStateException("WebSocket frame exceeds 10MB limit: $payloadLen")
        }

        val maskKey = if (hasMask) {
            val m = ByteArray(4)
            readFully(input, m)
            m
        } else null

        val payload = ByteArray(payloadLen.toInt())
        readFully(input, payload)

        if (hasMask && maskKey != null) {
            for (i in payload.indices) {
                payload[i] = (payload[i].toInt() xor maskKey[i % 4].toInt()).toByte()
            }
        }

        return WebSocketFrame(fin, opcode, payload)
    }

    fun readFully(input: InputStream, buffer: ByteArray) {
        var total = 0
        while (total < buffer.size) {
            val count = input.read(buffer, total, buffer.size - total)
            if (count == -1) throw EOFException("Unexpected EOF while reading WebSocket frame")
            total += count
        }
    }

    fun readHttpLine(input: InputStream): String? {
        val sb = StringBuilder()
        while (true) {
            val b = input.read()
            if (b == -1) {
                if (sb.isEmpty()) return null
                break
            }
            if (b == '\n'.code) {
                if (sb.isNotEmpty() && sb[sb.length - 1] == '\r') {
                    sb.setLength(sb.length - 1)
                }
                break
            }
            sb.append(b.toChar())
            if (sb.length > 8192) break
        }
        return sb.toString()
    }
}

data class WebSocketFrame(
    val fin: Boolean,
    val opcode: Int,
    val payload: ByteArray
) {
    fun asText(): String = String(payload, Charsets.UTF_8)
}

/**
 * Server-side WebSocket connection session representing a connected peer.
 */
class WebSocketSession(
    val socket: Socket,
    val output: OutputStream,
    var peerId: String? = null,
    var peerName: String? = null
) {
    private val sendLock = Any()

    fun sendText(text: String) {
        val bytes = text.toByteArray(Charsets.UTF_8)
        val frame = OmniWebSocket.encodeFrame(OmniWebSocket.OPCODE_TEXT, bytes, mask = false)
        synchronized(sendLock) {
            output.write(frame)
            output.flush()
        }
    }

    fun sendPong(payload: ByteArray) {
        val frame = OmniWebSocket.encodeFrame(OmniWebSocket.OPCODE_PONG, payload, mask = false)
        synchronized(sendLock) {
            try {
                output.write(frame)
                output.flush()
            } catch (ignored: Exception) {}
        }
    }

    fun sendClose() {
        val frame = OmniWebSocket.encodeFrame(OmniWebSocket.OPCODE_CLOSE, ByteArray(0), mask = false)
        synchronized(sendLock) {
            try {
                output.write(frame)
                output.flush()
            } catch (ignored: Exception) {}
        }
    }
}

/**
 * Client-side RFC 6455 WebSocket client for listener devices connecting to the host room.
 */
class OmniWebSocketClient(
    val host: String,
    val port: Int,
    val path: String = "/ws",
    val timeoutMs: Int = 5000
) {
    @Volatile
    private var socket: Socket? = null
    @Volatile
    var isConnected: Boolean = false
        private set

    private val sendLock = Any()

    var onOpen: (() -> Unit)? = null
    var onMessage: ((String) -> Unit)? = null
    var onClose: ((code: Int, reason: String) -> Unit)? = null
    var onError: ((Exception) -> Unit)? = null

    fun connect() {
        val s = Socket()
        s.tcpNoDelay = true
        s.keepAlive = true
        s.connect(InetSocketAddress(host, port), timeoutMs)
        socket = s

        val output = s.getOutputStream()
        val input = s.getInputStream()

        // 1. Send WebSocket Upgrade Request
        val key = OmniWebSocket.generateKey()
        val formattedHost = if (host.contains(":") && !host.startsWith("[")) "[$host]" else host
        val req = buildString {
            append("GET $path HTTP/1.1\r\n")
            append("Host: $formattedHost:$port\r\n")
            append("Upgrade: websocket\r\n")
            append("Connection: Upgrade\r\n")
            append("Sec-WebSocket-Key: $key\r\n")
            append("Sec-WebSocket-Version: 13\r\n")
            append("\r\n")
        }
        output.write(req.toByteArray(Charsets.UTF_8))
        output.flush()

        // 2. Read HTTP Response Header byte-accurately (avoiding BufferedReader overrun)
        val statusLine = OmniWebSocket.readHttpLine(input) ?: throw EOFException("Host closed connection before handshake response")
        if (!statusLine.contains("101")) {
            throw IllegalStateException("WebSocket handshake failed: $statusLine")
        }

        var line = OmniWebSocket.readHttpLine(input)
        while (!line.isNullOrEmpty()) {
            line = OmniWebSocket.readHttpLine(input)
        }

        // 3. Handshake successful!
        isConnected = true
        onOpen?.invoke()

        // 4. Frame read loop
        try {
            while (isConnected && !s.isClosed) {
                val frame = OmniWebSocket.readFrame(input) ?: break
                when (frame.opcode) {
                    OmniWebSocket.OPCODE_TEXT -> {
                        val text = frame.asText()
                        onMessage?.invoke(text)
                    }
                    OmniWebSocket.OPCODE_PING -> {
                        // Reply with PONG frame
                        sendFrame(OmniWebSocket.OPCODE_PONG, frame.payload)
                    }
                    OmniWebSocket.OPCODE_PONG -> {
                        // Keepalive pong received
                    }
                    OmniWebSocket.OPCODE_CLOSE -> {
                        try {
                            sendFrame(OmniWebSocket.OPCODE_CLOSE, ByteArray(0))
                        } catch (ignored: Exception) {}
                        break
                    }
                }
            }
        } catch (e: Exception) {
            if (isConnected) {
                onError?.invoke(e)
            }
        } finally {
            closeInternal(1000, "Normal closure")
        }
    }

    fun send(text: String) {
        if (!isConnected) return
        val bytes = text.toByteArray(Charsets.UTF_8)
        sendFrame(OmniWebSocket.OPCODE_TEXT, bytes)
    }

    private fun sendFrame(opcode: Int, payload: ByteArray) {
        val s = socket ?: return
        val frame = OmniWebSocket.encodeFrame(opcode, payload, mask = true)
        synchronized(sendLock) {
            try {
                val out = s.getOutputStream()
                out.write(frame)
                out.flush()
            } catch (e: Exception) {
                closeInternal(1006, e.message ?: "Send error")
                throw e
            }
        }
    }

    private fun closeInternal(code: Int, reason: String) {
        if (!isConnected && socket == null) return
        isConnected = false
        try {
            socket?.close()
        } catch (ignored: Exception) {}
        socket = null
        onClose?.invoke(code, reason)
    }

    fun close() {
        if (!isConnected) return
        try {
            sendFrame(OmniWebSocket.OPCODE_CLOSE, ByteArray(0))
        } catch (ignored: Exception) {}
        closeInternal(1000, "Client closed")
    }

    fun isOpen(): Boolean = isConnected && socket?.isClosed == false
}

package xyz.omniplay.lyrics

import android.content.Context
import android.util.Log
import xyz.omniplay.model.Song
import java.io.BufferedInputStream
import java.io.File
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.charset.Charset

object EmbeddedLyricsExtractor {
    private const val TAG = "EmbeddedLyrics"
    private const val MAX_TAG_READ_BYTES = 4 * 1024 * 1024 // 4 MB max to read for metadata

    /**
     * Extracts embedded lyrics directly from audio container tags (FLAC, MP3, M4A, OGG).
     * Returns a [Lyrics] object with [Lyrics.isOffline] = true and source set to the container type,
     * or null if no embedded lyrics are found.
     */
    fun extract(context: Context, song: Song): Lyrics? {
        var stream: InputStream? = null
        try {
            if (song.filePath.isNotBlank()) {
                val f = File(song.filePath)
                if (f.exists() && f.canRead()) {
                    stream = f.inputStream()
                }
            }
            if (stream == null) {
                stream = context.contentResolver.openInputStream(song.contentUri)
            }
            if (stream == null) return null

            BufferedInputStream(stream, 64 * 1024).use { bis ->
                bis.mark(32)
                val magic = ByteArray(12)
                val readOk = readFully(bis, magic, 0, magic.size)
                bis.reset()
                if (!readOk) return null

                val result: Pair<String, String>? = when {
                    // FLAC: "fLaC" or ID3v2 preceding "fLaC"
                    isFlac(magic) -> extractFlac(bis)?.let { Pair(it, "FLAC") }
                    // MP3 / ID3v2: "ID3"
                    isId3(magic) -> extractId3(bis)?.let { Pair(it, "ID3") }
                    // MP4 / M4A: "ftyp", "moov", etc.
                    isMp4(magic) -> extractMp4(bis)?.let { Pair(it, "M4A") }
                    // OGG: "OggS"
                    isOgg(magic) -> extractOgg(bis)?.let { Pair(it, "OGG") }
                    else -> null
                }

                if (result != null && result.first.isNotBlank()) {
                    val rawText = result.first.trim()
                    val container = result.second
                    val synced = LrcParser.parse(rawText)
                    return Lyrics(
                        songId = song.id,
                        trackName = song.title,
                        artistName = song.artist,
                        syncedLyrics = if (synced.isNotEmpty()) synced else null,
                        plainLyrics = if (synced.isEmpty()) rawText else null,
                        isInstrumental = false,
                        syncedRaw = if (synced.isNotEmpty()) rawText else null,
                        source = "EMBEDDED ($container)",
                        isOffline = true
                    )
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "Error extracting embedded lyrics for ${song.title}: ${e.message}")
        } finally {
            try { stream?.close() } catch (ignored: Exception) {}
        }
        return null
    }

    private fun isFlac(b: ByteArray): Boolean {
        if (b.size < 4) return false
        if (b[0] == 0x66.toByte() && b[1] == 0x4C.toByte() && b[2] == 0x61.toByte() && b[3] == 0x43.toByte()) {
            return true
        }
        return isId3(b)
    }

    private fun isId3(b: ByteArray): Boolean {
        return b.size >= 3 && b[0] == 0x49.toByte() && b[1] == 0x44.toByte() && b[2] == 0x33.toByte()
    }

    private fun isMp4(b: ByteArray): Boolean {
        if (b.size < 8) return false
        val atom = String(b, 4, 4, Charsets.US_ASCII)
        return atom in listOf("ftyp", "moov", "free", "mdat", "wide")
    }

    private fun isOgg(b: ByteArray): Boolean {
        return b.size >= 4 && b[0] == 0x4F.toByte() && b[1] == 0x67.toByte() && b[2] == 0x67.toByte() && b[3] == 0x53.toByte()
    }

    // =========================================================================
    // FLAC VORBIS COMMENT EXTRACTOR
    // =========================================================================

    private fun extractFlac(stream: InputStream): String? {
        val magic = ByteArray(4)
        if (!readFully(stream, magic)) return null

        // Skip leading ID3v2 if present
        if (magic[0] == 0x49.toByte() && magic[1] == 0x44.toByte() && magic[2] == 0x33.toByte()) {
            val id3Hdr = ByteArray(6)
            if (!readFully(stream, id3Hdr)) return null
            val tagSize = ((id3Hdr[2].toInt() and 0x7F) shl 21) or
                    ((id3Hdr[3].toInt() and 0x7F) shl 14) or
                    ((id3Hdr[4].toInt() and 0x7F) shl 7) or
                    (id3Hdr[5].toInt() and 0x7F)
            val hasFooter = (id3Hdr[1].toInt() and 0x10) != 0
            val toSkip = tagSize.toLong() + (if (hasFooter) 10L else 0L)
            skipFully(stream, toSkip)
            if (!readFully(stream, magic)) return null
        }

        // Verify "fLaC"
        if (magic[0] != 0x66.toByte() || magic[1] != 0x4C.toByte() ||
            magic[2] != 0x61.toByte() || magic[3] != 0x43.toByte()
        ) {
            return null
        }

        val blockHdr = ByteArray(4)
        var bytesReadTotal = 0

        while (bytesReadTotal < MAX_TAG_READ_BYTES) {
            if (!readFully(stream, blockHdr)) break
            bytesReadTotal += 4
            val isLast = (blockHdr[0].toInt() and 0x80) != 0
            val blockType = blockHdr[0].toInt() and 0x7F
            val length = ((blockHdr[1].toInt() and 0xFF) shl 16) or
                    ((blockHdr[2].toInt() and 0xFF) shl 8) or
                    (blockHdr[3].toInt() and 0xFF)

            if (blockType == 4) {
                // VORBIS_COMMENT block
                if (length in 4..(2 * 1024 * 1024)) {
                    val data = ByteArray(length)
                    if (!readFully(stream, data)) break
                    return parseVorbisCommentPayload(data)
                } else {
                    skipFully(stream, length.toLong())
                }
            } else {
                skipFully(stream, length.toLong())
            }
            bytesReadTotal += length
            if (isLast) break
        }
        return null
    }

    private fun parseVorbisCommentPayload(data: ByteArray): String? {
        val buf = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN)
        if (buf.remaining() < 4) return null
        val vendorLen = buf.int
        if (vendorLen < 0 || vendorLen > buf.remaining() - 4) return null
        buf.position(buf.position() + vendorLen)

        if (buf.remaining() < 4) return null
        val commentCount = buf.int
        val lyricKeys = setOf("LYRICS", "UNSYNCEDLYRICS", "UNSYNCED LYRICS", "SYNCEDLYRICS")

        for (i in 0 until commentCount) {
            if (buf.remaining() < 4) break
            val cLen = buf.int
            if (cLen <= 0 || cLen > buf.remaining()) break
            val cBytes = ByteArray(cLen)
            buf.get(cBytes)
            val comment = String(cBytes, Charsets.UTF_8)
            val eq = comment.indexOf('=')
            if (eq > 0) {
                val key = comment.substring(0, eq).trim().uppercase(Locale.ROOT)
                if (key in lyricKeys) {
                    val value = comment.substring(eq + 1).trim()
                    if (value.isNotEmpty()) return value
                }
            }
        }
        return null
    }

    // =========================================================================
    // MP3 ID3v2 EXTRACTOR (USLT / SYLT)
    // =========================================================================

    private fun extractId3(stream: InputStream): String? {
        val hdr = ByteArray(10)
        if (!readFully(stream, hdr)) return null
        if (hdr[0] != 0x49.toByte() || hdr[1] != 0x44.toByte() || hdr[2] != 0x33.toByte()) {
            return null
        }

        val majorVersion = hdr[3].toInt() and 0xFF
        val tagSize = ((hdr[6].toInt() and 0x7F) shl 21) or
                ((hdr[7].toInt() and 0x7F) shl 14) or
                ((hdr[8].toInt() and 0x7F) shl 7) or
                (hdr[9].toInt() and 0x7F)

        val readSize = minOf(tagSize, MAX_TAG_READ_BYTES)
        val tagData = ByteArray(readSize)
        if (!readFully(stream, tagData)) return null

        val buf = ByteBuffer.wrap(tagData)

        // Iterate through ID3 frames
        while (buf.remaining() >= 10) {
            val frameId: String
            val frameSize: Int

            if (majorVersion == 2) {
                // ID3v2.2: 3-byte ID + 3-byte size
                val idBytes = ByteArray(3)
                buf.get(idBytes)
                frameId = String(idBytes, Charsets.US_ASCII)
                frameSize = ((buf.get().toInt() and 0xFF) shl 16) or
                        ((buf.get().toInt() and 0xFF) shl 8) or
                        (buf.get().toInt() and 0xFF)
            } else {
                // ID3v2.3 / ID3v2.4: 4-byte ID + 4-byte size + 2-byte flags
                val idBytes = ByteArray(4)
                buf.get(idBytes)
                if (idBytes[0] == 0.toByte()) break // Padding reached
                frameId = String(idBytes, Charsets.US_ASCII)

                val b0 = buf.get().toInt() and 0xFF
                val b1 = buf.get().toInt() and 0xFF
                val b2 = buf.get().toInt() and 0xFF
                val b3 = buf.get().toInt() and 0xFF
                frameSize = if (majorVersion == 4) {
                    // Syncsafe in v2.4
                    ((b0 and 0x7F) shl 21) or ((b1 and 0x7F) shl 14) or ((b2 and 0x7F) shl 7) or (b3 and 0x7F)
                } else {
                    (b0 shl 24) or (b1 shl 16) or (b2 shl 8) or b3
                }
                buf.short // Skip 2 flags bytes
            }

            if (frameSize <= 0 || frameSize > buf.remaining()) break

            if (frameId in listOf("USLT", "ULT", "SYLT")) {
                val frameBytes = ByteArray(frameSize)
                buf.get(frameBytes)
                val text = parseUsltFrame(frameBytes)
                if (!text.isNullOrBlank()) return text
            } else {
                buf.position(buf.position() + frameSize)
            }
        }
        return null
    }

    private fun parseUsltFrame(bytes: ByteArray): String? {
        if (bytes.size < 5) return null
        val encoding = bytes[0].toInt() and 0xFF
        val charset = when (encoding) {
            0 -> Charsets.ISO_8859_1
            1 -> Charsets.UTF_16
            2 -> Charsets.UTF_16BE
            3 -> Charsets.UTF_8
            else -> Charsets.UTF_8
        }

        // Bytes 1..3: language code (3 chars)
        // From byte 4: description (null-terminated), followed by lyrics text
        var offset = 4
        if (encoding == 1 || encoding == 2) {
            // 2-byte null terminator (0x00 0x00)
            while (offset + 1 < bytes.size) {
                if (bytes[offset] == 0.toByte() && bytes[offset + 1] == 0.toByte()) {
                    offset += 2
                    break
                }
                offset += 2
            }
        } else {
            // 1-byte null terminator (0x00)
            while (offset < bytes.size) {
                if (bytes[offset] == 0.toByte()) {
                    offset += 1
                    break
                }
                offset += 1
            }
        }

        if (offset < bytes.size) {
            val lyricsBytes = bytes.copyOfRange(offset, bytes.size)
            return String(lyricsBytes, charset).trim()
        }
        return null
    }

    // =========================================================================
    // MP4 / M4A EXTRACTOR (©lyr atom in moov.udta.meta.ilst)
    // =========================================================================

    private fun extractMp4(stream: InputStream): String? {
        // Read up to 2MB to find metadata atoms
        val limit = 2 * 1024 * 1024
        val data = ByteArray(limit)
        var total = 0
        while (total < limit) {
            val r = stream.read(data, total, limit - total)
            if (r == -1) break
            total += r
        }
        if (total < 16) return null

        // Search for ©lyr atom pattern in bytes (0xA9, 0x6C, 0x79, 0x72) or "©lyr"
        val lyrSignature = byteArrayOf(0xA9.toByte(), 0x6C.toByte(), 0x79.toByte(), 0x72.toByte())
        val index = indexOfBytes(data, total, lyrSignature)
        if (index != -1 && index + 16 < total) {
            // ©lyr atom contains a 'data' child atom: [4 bytes size, 'data', 4 bytes flags/type, 4 bytes locale, text]
            val lyrSize = ((data[index - 4].toInt() and 0xFF) shl 24) or
                    ((data[index - 3].toInt() and 0xFF) shl 16) or
                    ((data[index - 2].toInt() and 0xFF) shl 8) or
                    (data[index - 1].toInt() and 0xFF)
            if (lyrSize in 16..100000 && index - 4 + lyrSize <= total) {
                val dataAtomIndex = indexOfBytes(data, index - 4 + lyrSize, "data".toByteArray(Charsets.US_ASCII))
                if (dataAtomIndex != -1 && dataAtomIndex + 12 < total) {
                    val textStart = dataAtomIndex + 12
                    val textEnd = minOf(index - 4 + lyrSize, total)
                    if (textEnd > textStart) {
                        return String(data, textStart, textEnd - textStart, Charsets.UTF_8).trim()
                    }
                }
            }
        }
        return null
    }

    // =========================================================================
    // OGG VORBIS EXTRACTOR
    // =========================================================================

    private fun extractOgg(stream: InputStream): String? {
        // Read first 64KB containing Vorbis comment header
        val data = ByteArray(64 * 1024)
        var total = 0
        while (total < data.size) {
            val r = stream.read(data, total, data.size - total)
            if (r == -1) break
            total += r
        }
        if (total < 32) return null

        // Search for vorbis comment header signature "\x03vorbis"
        val sig = byteArrayOf(0x03, 0x76, 0x6F, 0x72, 0x62, 0x69, 0x73)
        val idx = indexOfBytes(data, total, sig)
        if (idx != -1 && idx + 7 < total) {
            val payload = data.copyOfRange(idx + 7, total)
            return parseVorbisCommentPayload(payload)
        }
        return null
    }

    private fun indexOfBytes(data: ByteArray, maxLen: Int, target: ByteArray): Int {
        val end = maxLen - target.size
        for (i in 0..end) {
            var matched = true
            for (j in target.indices) {
                if (data[i + j] != target[j]) {
                    matched = false
                    break
                }
            }
            if (matched) return i
        }
        return -1
    }

    private fun readFully(stream: InputStream, buffer: ByteArray, offset: Int = 0, length: Int = buffer.size - offset): Boolean {
        var bytesRead = 0
        while (bytesRead < length) {
            val r = stream.read(buffer, offset + bytesRead, length - bytesRead)
            if (r == -1) return false
            bytesRead += r
        }
        return true
    }

    private fun skipFully(stream: InputStream, toSkip: Long) {
        var remaining = toSkip
        val temp = ByteArray(4096)
        while (remaining > 0) {
            val r = stream.read(temp, 0, minOf(remaining, temp.size.toLong()).toInt())
            if (r <= 0) break
            remaining -= r
        }
    }
}

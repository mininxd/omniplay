package xyz.omniplay.util

import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.Locale

data class FlacHeader(
    val title: String? = null,
    val artist: String? = null,
    val album: String? = null,
    val durationMs: Long = 0L,
    val sampleRate: Int = 0,
    val channels: Int = 0,
    val bitDepth: Int = 0,
    val totalSamples: Long = 0L,
    val pictureData: ByteArray? = null,
    val pictureMime: String? = null
)

object FlacHeaderParser {

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

    /**
     * Inspects input stream to parse FLAC STREAMINFO, VORBIS_COMMENT, and PICTURE blocks.
     */
    fun parse(stream: InputStream): FlacHeader? {
        val magic = ByteArray(4)
        if (!readFully(stream, magic)) return null

        // Skip ID3v2 tag if present at start of file
        if (magic[0] == 0x49.toByte() && magic[1] == 0x44.toByte() && magic[2] == 0x33.toByte()) {
            val id3Header = ByteArray(6)
            if (!readFully(stream, id3Header)) return null
            val tagSize = ((id3Header[2].toInt() and 0x7F) shl 21) or
                    ((id3Header[3].toInt() and 0x7F) shl 14) or
                    ((id3Header[4].toInt() and 0x7F) shl 7) or
                    (id3Header[5].toInt() and 0x7F)
            val hasFooter = (id3Header[1].toInt() and 0x10) != 0
            val toSkip = tagSize.toLong() + (if (hasFooter) 10L else 0L)
            skipFully(stream, toSkip)
            if (!readFully(stream, magic)) return null
        }

        // Check FLAC magic "fLaC" (0x66, 0x4C, 0x61, 0x43)
        if (magic[0] != 0x66.toByte() || magic[1] != 0x4C.toByte() ||
            magic[2] != 0x61.toByte() || magic[3] != 0x43.toByte()
        ) {
            return null
        }

        var sampleRate = 0
        var channels = 0
        var bitDepth = 0
        var totalSamples = 0L
        var durationMs = 0L

        var title: String? = null
        var artist: String? = null
        var album: String? = null

        var pictureData: ByteArray? = null
        var pictureMime: String? = null

        val blockHdr = ByteArray(4)

        while (true) {
            if (!readFully(stream, blockHdr)) break
            val isLast = (blockHdr[0].toInt() and 0x80) != 0
            val blockType = blockHdr[0].toInt() and 0x7F
            val length = ((blockHdr[1].toInt() and 0xFF) shl 16) or
                    ((blockHdr[2].toInt() and 0xFF) shl 8) or
                    (blockHdr[3].toInt() and 0xFF)

            when (blockType) {
                0 -> {
                    // STREAMINFO (34 bytes)
                    if (length >= 34) {
                        val streamInfo = ByteArray(length)
                        if (!readFully(stream, streamInfo)) break
                        val b10 = streamInfo[10].toInt() and 0xFF
                        val b11 = streamInfo[11].toInt() and 0xFF
                        val b12 = streamInfo[12].toInt() and 0xFF
                        val b13 = streamInfo[13].toInt() and 0xFF
                        val b14 = streamInfo[14].toInt() and 0xFF
                        val b15 = streamInfo[15].toInt() and 0xFF
                        val b16 = streamInfo[16].toInt() and 0xFF
                        val b17 = streamInfo[17].toInt() and 0xFF

                        sampleRate = (b10 shl 12) or (b11 shl 4) or (b12 ushr 4)
                        channels = ((b12 and 0x0E) ushr 1) + 1
                        bitDepth = (((b12 and 0x01) shl 4) or (b13 ushr 4)) + 1
                        totalSamples = ((b13.toLong() and 0x0F) shl 32) or
                                ((b14.toLong() and 0xFF) shl 24) or
                                ((b15.toLong() and 0xFF) shl 16) or
                                ((b16.toLong() and 0xFF) shl 8) or
                                (b17.toLong() and 0xFF)

                        if (sampleRate > 0) {
                            durationMs = (totalSamples * 1000L) / sampleRate
                        }
                    } else {
                        skipFully(stream, length.toLong())
                    }
                }
                4 -> {
                    // VORBIS_COMMENT
                    if (length in 4..(512 * 1024)) {
                        val data = ByteArray(length)
                        if (!readFully(stream, data)) break
                        val buf = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN)
                        val vendorLen = buf.int
                        if (vendorLen in 0 until (length - 4)) {
                            buf.position(buf.position() + vendorLen)
                            if (buf.remaining() >= 4) {
                                val commentCount = buf.int
                                for (i in 0 until commentCount) {
                                    if (buf.remaining() < 4) break
                                    val cLen = buf.int
                                    if (cLen < 0 || cLen > buf.remaining()) break
                                    val cBytes = ByteArray(cLen)
                                    buf.get(cBytes)
                                    val comment = String(cBytes, Charsets.UTF_8)
                                    val eqIdx = comment.indexOf('=')
                                    if (eqIdx != -1) {
                                        val key = comment.substring(0, eqIdx).trim().uppercase(Locale.ROOT)
                                        val value = comment.substring(eqIdx + 1).trim()
                                        when (key) {
                                            "TITLE" -> if (title.isNullOrBlank()) title = value
                                            "ARTIST" -> if (artist.isNullOrBlank()) artist = value
                                            "ALBUM" -> if (album.isNullOrBlank()) album = value
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        skipFully(stream, length.toLong())
                    }
                }
                6 -> {
                    // PICTURE
                    if (length in 32..(15 * 1024 * 1024)) { // Up to 15MB embedded art
                        val data = ByteArray(length)
                        if (!readFully(stream, data)) break
                        val buf = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN)
                        val picType = buf.int
                        val mimeLen = buf.int
                        if (mimeLen in 0..buf.remaining()) {
                            val mimeBytes = ByteArray(mimeLen)
                            buf.get(mimeBytes)
                            val mime = String(mimeBytes, Charsets.US_ASCII)
                            if (buf.remaining() >= 4) {
                                val descLen = buf.int
                                if (descLen in 0..buf.remaining()) {
                                    buf.position(buf.position() + descLen)
                                    if (buf.remaining() >= 20) {
                                        buf.position(buf.position() + 16) // skip width, height, color depth, colors used
                                        val picDataLen = buf.int
                                        if (picDataLen in 1..buf.remaining()) {
                                            val picBytes = ByteArray(picDataLen)
                                            buf.get(picBytes)
                                            if (pictureData == null || picType == 3) { // 3 = Front cover
                                                pictureData = picBytes
                                                pictureMime = mime
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        skipFully(stream, length.toLong())
                    }
                }
                else -> {
                    skipFully(stream, length.toLong())
                }
            }

            if (isLast) break
        }

        return FlacHeader(
            title = title,
            artist = artist,
            album = album,
            durationMs = durationMs,
            sampleRate = sampleRate,
            channels = channels,
            bitDepth = bitDepth,
            totalSamples = totalSamples,
            pictureData = pictureData,
            pictureMime = pictureMime
        )
    }

    /**
     * Fast extraction of embedded picture data from a FLAC stream.
     */
    fun extractPicture(stream: InputStream): ByteArray? {
        val header = parse(stream)
        return header?.pictureData
    }
}

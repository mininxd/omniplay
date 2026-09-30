package xyz.omniplay.util

import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import androidx.media3.common.C
import androidx.media3.common.Format
import java.io.BufferedInputStream
import java.io.InputStream
import java.io.Serializable
import java.util.Locale

data class AudioTrackInfo(
    val format: String = "AUDIO",
    val bitDepth: Int = 0,
    val sampleRate: Int = 0,
    val bitrate: Int = 0,
    val channels: Int = 2,
    val isHiRes: Boolean = false,
    val isBitPerfect: Boolean = false
) : Serializable {

    fun checkHiRes(): Boolean {
        return isHiRes ||
                bitDepth > 16 ||
                sampleRate > 48000 ||
                bitDepth == 1 ||
                format.startsWith("DSD", ignoreCase = true) ||
                format.equals("DSF", ignoreCase = true) ||
                format.equals("DFF", ignoreCase = true)
    }

    fun formatQualityString(): String {
        if (bitDepth == 1 || format.startsWith("DSD")) {
            val mhz = when {
                sampleRate >= 11289600 -> "11.28 MHz"
                sampleRate >= 5644800 -> "5.64 MHz"
                sampleRate >= 2822400 -> "2.82 MHz"
                sampleRate > 1000000 -> String.format(Locale.US, "%.2f MHz", sampleRate / 1000000.0)
                else -> "2.82 MHz"
            }
            return "1-bit • $mhz"
        }

        val srText = when {
            sampleRate <= 0 -> ""
            sampleRate % 1000 == 0 -> "${sampleRate / 1000}khz"
            sampleRate % 100 == 0 -> String.format(Locale.US, "%.1fkhz", sampleRate / 1000.0)
            else -> String.format(Locale.US, "%.1fkhz", sampleRate / 1000.0)
        }

        val isLossless = format in listOf("FLAC", "WAV", "ALAC", "AIFF", "APE", "WV") ||
                isHiRes || checkHiRes()

        val effectiveBitDepth = when {
            bitDepth > 0 -> bitDepth
            isHiRes || checkHiRes() || sampleRate > 48000 -> 24
            isLossless -> 16
            else -> 0
        }

        return when {
            effectiveBitDepth > 0 && srText.isNotEmpty() -> "${effectiveBitDepth}bit/$srText"
            effectiveBitDepth > 0 -> "${effectiveBitDepth}bit"
            isLossless && srText.isNotEmpty() -> "16bit/$srText"
            srText.isNotEmpty() && bitrate > 0 && !isLossless -> "${bitrate / 1000}kbps/$srText"
            srText.isNotEmpty() -> srText
            bitrate > 0 -> "${bitrate / 1000}kbps"
            else -> ""
        }
    }
}

object AudioInfoExtractor {

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
     * Extracts audio resolution, bit depth, sample rate, and format from URI.
     * Uses direct stream header inspection for FLAC (STREAMINFO), WAV (fmt),
     * and DSD (DSF/DFF) for instant, 100% accurate bit depth and sample rate detection.
     */
    fun extractFromUri(context: Context, uri: Uri, fallbackFormat: String): AudioTrackInfo {
        val upperFormat = fallbackFormat.uppercase(Locale.ROOT)

        // 1. DSD (DSF / DFF): Read DSD stream header for exact sample rate (2.8MHz/5.6MHz) and 1-bit PDM
        if (upperFormat in listOf("DSF", "DFF", "DSD", "DSD64", "DSD128", "DSD256", "DSD512") ||
            uri.toString().endsWith(".dsf", ignoreCase = true) ||
            uri.toString().endsWith(".dff", ignoreCase = true)) {
            try {
                context.contentResolver.openInputStream(uri)?.let { raw ->
                    BufferedInputStream(raw).use { stream ->
                        xyz.omniplay.dsd.DsdHeaderParser.parse(stream)?.let { dsdHeader ->
                            return AudioTrackInfo(
                                format = dsdHeader.formatName,
                                bitDepth = 1,
                                sampleRate = dsdHeader.sampleRate,
                                bitrate = dsdHeader.bitrateKbps * 1000,
                                channels = dsdHeader.channelCount,
                                isHiRes = true
                            )
                        }
                    }
                }
            } catch (ignored: Throwable) {}
        }

        // 2. FLAC: Read native STREAMINFO block for exact bit depth (16/24/32-bit) & sample rate
        if (upperFormat == "FLAC" || uri.toString().endsWith(".flac", ignoreCase = true)) {
            try {
                context.contentResolver.openInputStream(uri)?.let { raw ->
                    BufferedInputStream(raw).use { stream ->
                        FlacHeaderParser.parse(stream)?.let { flacHeader ->
                            if (flacHeader.sampleRate > 0) {
                                return AudioTrackInfo(
                                    format = "FLAC",
                                    bitDepth = flacHeader.bitDepth,
                                    sampleRate = flacHeader.sampleRate,
                                    channels = flacHeader.channels,
                                    isHiRes = flacHeader.sampleRate > 48000 || flacHeader.bitDepth > 16
                                )
                            }
                        }
                    }
                }
            } catch (ignored: Throwable) {}
            try {
                context.contentResolver.openInputStream(uri)?.let { raw ->
                    BufferedInputStream(raw).use { stream ->
                        extractFlacHeader(stream)?.let { return it }
                    }
                }
            } catch (ignored: Throwable) {}
        }

        // 3. WAV: Read RIFF fmt chunk for bit depth (16/24/32-bit) & sample rate
        if (upperFormat == "WAV" || uri.toString().endsWith(".wav", ignoreCase = true)) {
            try {
                context.contentResolver.openInputStream(uri)?.let { raw ->
                    BufferedInputStream(raw).use { stream ->
                        extractWavHeader(stream)?.let { return it }
                    }
                }
            } catch (ignored: Throwable) {}
        }

        // 4. Fallback stream inspection if format was generic "AUDIO"
        if (upperFormat == "AUDIO" || upperFormat.isEmpty()) {
            try {
                context.contentResolver.openInputStream(uri)?.let { raw ->
                    BufferedInputStream(raw).use { stream ->
                        extractFlacHeader(stream)?.let { return it }
                    }
                }
            } catch (ignored: Throwable) {}
            try {
                context.contentResolver.openInputStream(uri)?.let { raw ->
                    BufferedInputStream(raw).use { stream ->
                        extractWavHeader(stream)?.let { return it }
                    }
                }
            } catch (ignored: Throwable) {}
        }

        // 5. Fallback: MediaMetadataRetriever
        return extractFromRetriever(context, uri, fallbackFormat)
    }

    private fun extractFlacHeader(stream: InputStream): AudioTrackInfo? {
        val magic = ByteArray(4)
        if (!readFully(stream, magic)) return null

        if (magic[0] == 0x49.toByte() && magic[1] == 0x44.toByte() && magic[2] == 0x33.toByte()) {
            // ID3v2 tag present at start of FLAC file
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

        // Check magic "fLaC" -> 0x66, 0x4C, 0x61, 0x43
        var foundFlac = magic[0] == 0x66.toByte() && magic[1] == 0x4C.toByte() &&
                magic[2] == 0x61.toByte() && magic[3] == 0x43.toByte()

        if (!foundFlac) {
            val scanBuf = ByteArray(8192)
            val bytesRead = stream.read(scanBuf)
            if (bytesRead >= 4) {
                for (i in 0 until bytesRead - 4) {
                    if (scanBuf[i] == 0x66.toByte() && scanBuf[i + 1] == 0x4C.toByte() &&
                        scanBuf[i + 2] == 0x61.toByte() && scanBuf[i + 3] == 0x43.toByte()
                    ) {
                        foundFlac = true
                        val remainingInBuf = bytesRead - (i + 4)
                        val streamInfo = ByteArray(38)
                        val fromBuf = minOf(remainingInBuf, 38)
                        System.arraycopy(scanBuf, i + 4, streamInfo, 0, fromBuf)
                        if (fromBuf < 38) {
                            if (!readFully(stream, streamInfo, fromBuf, 38 - fromBuf)) return null
                        }
                        return parseStreamInfo(streamInfo)
                    }
                }
            }
            if (!foundFlac) return null
        }

        // Read 38 bytes (4-byte metadata block header + 34-byte STREAMINFO block)
        val streamInfo = ByteArray(38)
        if (!readFully(stream, streamInfo)) return null
        return parseStreamInfo(streamInfo)
    }

    private fun parseStreamInfo(streamInfo: ByteArray): AudioTrackInfo? {
        // StreamInfo block type is 0 (bits 0..6 of byte 0)
        if ((streamInfo[0].toInt() and 0x7F) != 0) return null

        val b14 = streamInfo[14].toInt() and 0xFF
        val b15 = streamInfo[15].toInt() and 0xFF
        val b16 = streamInfo[16].toInt() and 0xFF
        val b17 = streamInfo[17].toInt() and 0xFF

        val sampleRate = (b14 shl 12) or (b15 shl 4) or (b16 ushr 4)
        val channels = ((b16 and 0x0E) ushr 1) + 1
        val bitDepth = (((b16 and 0x01) shl 4) or (b17 ushr 4)) + 1
        val isHiRes = sampleRate > 48000 || bitDepth > 16

        return AudioTrackInfo(
            format = "FLAC",
            bitDepth = bitDepth,
            sampleRate = sampleRate,
            channels = channels,
            isHiRes = isHiRes
        )
    }

    private fun extractWavHeader(stream: InputStream): AudioTrackInfo? {
        val riffHeader = ByteArray(12)
        if (!readFully(stream, riffHeader)) return null

        val riff = String(riffHeader, 0, 4, Charsets.US_ASCII)
        val wave = String(riffHeader, 8, 4, Charsets.US_ASCII)
        if (riff != "RIFF" || wave != "WAVE") return null

        var bytesScanned = 12
        val chunkHeader = ByteArray(8)
        while (bytesScanned < 65536) {
            if (!readFully(stream, chunkHeader)) break
            bytesScanned += 8

            val chunkId = String(chunkHeader, 0, 4, Charsets.US_ASCII)
            val chunkSize = (chunkHeader[4].toInt() and 0xFF) or
                    ((chunkHeader[5].toInt() and 0xFF) shl 8) or
                    ((chunkHeader[6].toInt() and 0xFF) shl 16) or
                    ((chunkHeader[7].toInt() and 0xFF) shl 24)

            if (chunkId == "fmt ") {
                val toRead = chunkSize.coerceAtLeast(16).coerceAtMost(40)
                val fmtData = ByteArray(toRead)
                if (!readFully(stream, fmtData)) break
                val channels = (fmtData[2].toInt() and 0xFF) or ((fmtData[3].toInt() and 0xFF) shl 8)
                val sampleRate = (fmtData[4].toInt() and 0xFF) or
                        ((fmtData[5].toInt() and 0xFF) shl 8) or
                        ((fmtData[6].toInt() and 0xFF) shl 16) or
                        ((fmtData[7].toInt() and 0xFF) shl 24)
                val bitsPerSample = (fmtData[14].toInt() and 0xFF) or
                        ((fmtData[15].toInt() and 0xFF) shl 8)
                val isHiRes = sampleRate > 48000 || bitsPerSample > 16
                return AudioTrackInfo(
                    format = "WAV",
                    bitDepth = bitsPerSample,
                    sampleRate = sampleRate,
                    channels = channels.coerceAtLeast(2),
                    isHiRes = isHiRes
                )
            } else {
                if (chunkSize < 0) break
                val paddedSize = if (chunkSize % 2 != 0) chunkSize + 1 else chunkSize
                skipFully(stream, paddedSize.toLong())
                bytesScanned += paddedSize
            }
        }
        return null
    }

    private fun extractFromRetriever(context: Context, uri: Uri, fallbackFormat: String): AudioTrackInfo {
        val retriever = MediaMetadataRetriever()
        return try {
            retriever.setDataSource(context, uri)
            val bitrateStr = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE)
            val bitrate = bitrateStr?.toIntOrNull() ?: 0

            val sampleRate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_SAMPLERATE)?.toIntOrNull() ?: 0
            } else {
                0
            }

            val bitDepth = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITS_PER_SAMPLE)?.toIntOrNull() ?: 0
            } else {
                0
            }

            val isHiRes = sampleRate > 48000 || bitDepth > 16 || fallbackFormat.startsWith("DSD", true) || fallbackFormat.equals("DSF", true) || fallbackFormat.equals("DFF", true)
            AudioTrackInfo(
                format = fallbackFormat.uppercase(Locale.ROOT),
                bitDepth = bitDepth,
                sampleRate = sampleRate,
                bitrate = bitrate,
                isHiRes = isHiRes
            )
        } catch (e: Throwable) {
            AudioTrackInfo(format = fallbackFormat.uppercase(Locale.ROOT))
        } finally {
            try {
                retriever.release()
            } catch (ignored: Throwable) {}
        }
    }

    /**
     * Converts ExoPlayer's resolved Format to an AudioTrackInfo.
     */
    fun fromExoFormat(format: Format, fallbackFormat: String): AudioTrackInfo {
        if (format.id == "dsd" || fallbackFormat.startsWith("DSD") || fallbackFormat == "DSF" || fallbackFormat == "DFF") {
            val dsdRate = if (format.sampleRate > 0) format.sampleRate * 32 else 2822400
            val dsdName = if (fallbackFormat.startsWith("DSD")) fallbackFormat else xyz.omniplay.dsd.DsdHeader.getFormatName(dsdRate)
            return AudioTrackInfo(
                format = dsdName,
                bitDepth = 1,
                sampleRate = dsdRate,
                channels = format.channelCount.coerceAtLeast(2),
                isHiRes = true
            )
        }

        val sampleRate = if (format.sampleRate > 0) format.sampleRate else 0
        val bitDepth = when (format.pcmEncoding) {
            C.ENCODING_PCM_16BIT -> 16
            C.ENCODING_PCM_24BIT -> 24
            C.ENCODING_PCM_32BIT, C.ENCODING_PCM_FLOAT -> 32
            else -> 0
        }
        val bitrate = if (format.bitrate > 0) format.bitrate else 0
        val isHiRes = sampleRate > 48000 || bitDepth > 16 || fallbackFormat.startsWith("DSD", true) || fallbackFormat.equals("DSF", true) || fallbackFormat.equals("DFF", true)

        val resolvedFormat = when {
            format.sampleMimeType?.contains("flac", ignoreCase = true) == true -> "FLAC"
            format.sampleMimeType?.contains("wav", ignoreCase = true) == true -> "WAV"
            format.sampleMimeType?.contains("alac", ignoreCase = true) == true -> "ALAC"
            format.sampleMimeType?.contains("mpeg", ignoreCase = true) == true ||
            format.sampleMimeType?.contains("mp3", ignoreCase = true) == true -> "MP3"
            format.sampleMimeType?.contains("aac", ignoreCase = true) == true ||
            format.sampleMimeType?.contains("mp4a", ignoreCase = true) == true -> "AAC"
            format.sampleMimeType?.contains("opus", ignoreCase = true) == true -> "OPUS"
            format.sampleMimeType?.contains("vorbis", ignoreCase = true) == true ||
            format.sampleMimeType?.contains("ogg", ignoreCase = true) == true -> "OGG"
            fallbackFormat.isNotEmpty() -> fallbackFormat.uppercase(Locale.ROOT)
            else -> "AUDIO"
        }

        return AudioTrackInfo(
            format = resolvedFormat,
            bitDepth = bitDepth,
            sampleRate = sampleRate,
            bitrate = bitrate,
            channels = format.channelCount.coerceAtLeast(2),
            isHiRes = isHiRes
        )
    }

    /**
     * Merges two AudioTrackInfo instances to preserve the highest fidelity details.
     */
    fun merge(primary: AudioTrackInfo?, secondary: AudioTrackInfo?): AudioTrackInfo {
        if (primary == null) return secondary ?: AudioTrackInfo("AUDIO")
        if (secondary == null) return primary

        val format = if (primary.format.isNotEmpty() && primary.format != "AUDIO") {
            primary.format
        } else {
            secondary.format
        }

        val bitDepth = maxOf(primary.bitDepth, secondary.bitDepth)
        val sampleRate = maxOf(primary.sampleRate, secondary.sampleRate)
        val bitrate = if (primary.bitrate > 0) primary.bitrate else secondary.bitrate
        val channels = maxOf(primary.channels, secondary.channels)
        val isHiRes = primary.checkHiRes() || secondary.checkHiRes() || (sampleRate > 48000 || bitDepth > 16)
        val isBitPerfect = primary.isBitPerfect || secondary.isBitPerfect

        return AudioTrackInfo(
            format = format,
            bitDepth = bitDepth,
            sampleRate = sampleRate,
            bitrate = bitrate,
            channels = channels,
            isHiRes = isHiRes,
            isBitPerfect = isBitPerfect
        )
    }
}

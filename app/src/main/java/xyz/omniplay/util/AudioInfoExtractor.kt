package xyz.omniplay.util

import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import androidx.media3.common.C
import androidx.media3.common.Format
import java.io.InputStream
import java.io.Serializable
import java.util.Locale

data class AudioTrackInfo(
    val format: String = "AUDIO",
    val bitDepth: Int = 0,
    val sampleRate: Int = 0,
    val bitrate: Int = 0,
    val isHiRes: Boolean = false
) : Serializable {

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

        return when {
            bitDepth > 0 && srText.isNotEmpty() -> "${bitDepth}bit/$srText"
            bitDepth > 0 -> "${bitDepth}bit"
            bitrate > 0 && srText.isNotEmpty() -> "${bitrate / 1000}kbps/$srText"
            bitrate > 0 -> "${bitrate / 1000}kbps"
            srText.isNotEmpty() -> srText
            else -> ""
        }
    }
}

object AudioInfoExtractor {

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
                context.contentResolver.openInputStream(uri)?.use { stream ->
                    xyz.omniplay.dsd.DsdHeaderParser.parse(stream)?.let { dsdHeader ->
                        return AudioTrackInfo(
                            format = dsdHeader.formatName,
                            bitDepth = 1,
                            sampleRate = dsdHeader.sampleRate,
                            bitrate = dsdHeader.bitrateKbps * 1000,
                            isHiRes = true
                        )
                    }
                }
            } catch (ignored: Throwable) {}
        }

        // 2. FLAC: Read native 42-byte STREAMINFO block for exact bit depth (16/24/32-bit) & sample rate
        if (upperFormat == "FLAC" || uri.toString().endsWith(".flac", ignoreCase = true)) {
            try {
                context.contentResolver.openInputStream(uri)?.use { stream ->
                    extractFlacHeader(stream)?.let { return it }
                }
            } catch (ignored: Throwable) {}
        }

        // 3. WAV: Read RIFF fmt chunk for bit depth (16/24/32-bit) & sample rate
        if (upperFormat == "WAV" || uri.toString().endsWith(".wav", ignoreCase = true)) {
            try {
                context.contentResolver.openInputStream(uri)?.use { stream ->
                    extractWavHeader(stream)?.let { return it }
                }
            } catch (ignored: Throwable) {}
        }

        // 4. Fallback: MediaMetadataRetriever
        return extractFromRetriever(context, uri, fallbackFormat)
    }

    private fun extractFlacHeader(stream: InputStream): AudioTrackInfo? {
        val buffer = ByteArray(42)
        var bytesRead = 0
        while (bytesRead < 42) {
            val r = stream.read(buffer, bytesRead, 42 - bytesRead)
            if (r == -1) break
            bytesRead += r
        }
        if (bytesRead < 42) return null

        // Check magic "fLaC" -> 0x66, 0x4C, 0x61, 0x43
        if (buffer[0] != 0x66.toByte() || buffer[1] != 0x4C.toByte() ||
            buffer[2] != 0x61.toByte() || buffer[3] != 0x43.toByte()
        ) {
            return null
        }

        // StreamInfo block type is 0 (bits 0..6 of byte 4)
        if ((buffer[4].toInt() and 0x7F) != 0) return null

        val b18 = buffer[18].toInt() and 0xFF
        val b19 = buffer[19].toInt() and 0xFF
        val b20 = buffer[20].toInt() and 0xFF
        val b21 = buffer[21].toInt() and 0xFF

        val sampleRate = (b18 shl 12) or (b19 shl 4) or (b20 ushr 4)
        val bitDepth = (((b20 and 0x01) shl 4) or (b21 ushr 4)) + 1
        val isHiRes = sampleRate >= 88200 || bitDepth >= 24

        return AudioTrackInfo(
            format = "FLAC",
            bitDepth = bitDepth,
            sampleRate = sampleRate,
            isHiRes = isHiRes
        )
    }

    private fun extractWavHeader(stream: InputStream): AudioTrackInfo? {
        val header = ByteArray(12)
        var r = 0
        while (r < 12) {
            val count = stream.read(header, r, 12 - r)
            if (count == -1) break
            r += count
        }
        if (r < 12) return null

        val riff = String(header, 0, 4, Charsets.US_ASCII)
        val wave = String(header, 8, 4, Charsets.US_ASCII)
        if (riff != "RIFF" || wave != "WAVE") return null

        var bytesScanned = 12
        val chunkHeader = ByteArray(8)
        while (bytesScanned < 2048) {
            var chRead = 0
            while (chRead < 8) {
                val c = stream.read(chunkHeader, chRead, 8 - chRead)
                if (c == -1) break
                chRead += c
            }
            if (chRead < 8) break
            bytesScanned += 8

            val chunkId = String(chunkHeader, 0, 4, Charsets.US_ASCII)
            val chunkSize = (chunkHeader[4].toInt() and 0xFF) or
                    ((chunkHeader[5].toInt() and 0xFF) shl 8) or
                    ((chunkHeader[6].toInt() and 0xFF) shl 16) or
                    ((chunkHeader[7].toInt() and 0xFF) shl 24)

            if (chunkId == "fmt ") {
                if (chunkSize < 16) break
                val fmtData = ByteArray(chunkSize.coerceAtMost(40))
                var fmtRead = 0
                while (fmtRead < fmtData.size) {
                    val c = stream.read(fmtData, fmtRead, fmtData.size - fmtRead)
                    if (c == -1) break
                    fmtRead += c
                }
                if (fmtRead >= 16) {
                    val sampleRate = (fmtData[4].toInt() and 0xFF) or
                            ((fmtData[5].toInt() and 0xFF) shl 8) or
                            ((fmtData[6].toInt() and 0xFF) shl 16) or
                            ((fmtData[7].toInt() and 0xFF) shl 24)
                    val bitsPerSample = (fmtData[14].toInt() and 0xFF) or
                            ((fmtData[15].toInt() and 0xFF) shl 8)
                    val isHiRes = sampleRate >= 88200 || bitsPerSample >= 24
                    return AudioTrackInfo(
                        format = "WAV",
                        bitDepth = bitsPerSample,
                        sampleRate = sampleRate,
                        isHiRes = isHiRes
                    )
                }
                break
            } else {
                var toSkip = chunkSize.toLong()
                while (toSkip > 0) {
                    val skipped = stream.skip(toSkip)
                    if (skipped <= 0) break
                    toSkip -= skipped
                }
                bytesScanned += chunkSize
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

            val isHiRes = sampleRate >= 88200 || bitDepth >= 24
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
        val isHiRes = sampleRate >= 88200 || bitDepth >= 24

        val resolvedFormat = when {
            format.sampleMimeType?.contains("flac", ignoreCase = true) == true -> "FLAC"
            format.sampleMimeType?.contains("wav", ignoreCase = true) == true -> "WAV"
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

        val bitDepth = if (primary.bitDepth > 0) primary.bitDepth else secondary.bitDepth
        val sampleRate = if (primary.sampleRate > 0) primary.sampleRate else secondary.sampleRate
        val bitrate = if (primary.bitrate > 0) primary.bitrate else secondary.bitrate
        val isHiRes = primary.isHiRes || secondary.isHiRes || (sampleRate >= 88200 || bitDepth >= 24)

        return AudioTrackInfo(
            format = format,
            bitDepth = bitDepth,
            sampleRate = sampleRate,
            bitrate = bitrate,
            isHiRes = isHiRes
        )
    }
}

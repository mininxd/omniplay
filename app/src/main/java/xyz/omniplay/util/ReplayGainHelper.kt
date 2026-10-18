package xyz.omniplay.util

import android.content.Context
import android.net.Uri
import xyz.omniplay.model.Song
import java.io.File
import kotlin.math.pow

object ReplayGainHelper {

    /**
     * Attempts to extract ReplayGain track gain in decibels (e.g. -6.5 dB).
     * Returns null if no ReplayGain metadata is found.
     */
    fun extractReplayGainDb(context: Context, song: Song): Float? {
        val path = song.filePath
        // 1. FLAC Vorbis Comment fast-path
        if (path.isNotEmpty() && path.endsWith(".flac", ignoreCase = true)) {
            try {
                val file = File(path)
                if (file.exists()) {
                    file.inputStream().use { stream ->
                        val header = FlacHeaderParser.parse(stream)
                        header?.replayGainTrackGain?.let { return it }
                        header?.replayGainAlbumGain?.let { return it }
                    }
                }
            } catch (_: Throwable) {}
        }

        // 2. Fallback check for treeUri / contentUri streaming
        if (song.contentUri != Uri.EMPTY) {
            try {
                context.contentResolver.openInputStream(song.contentUri)?.use { stream ->
                    val header = FlacHeaderParser.parse(stream)
                    header?.replayGainTrackGain?.let { return it }
                    header?.replayGainAlbumGain?.let { return it }
                }
            } catch (_: Throwable) {}
        }

        return null
    }

    /**
     * Converts a ReplayGain decibel value to a linear amplitude multiplier.
     * Clamped between 0.1 (-20dB) and 1.5 (+3.5dB) to avoid dangerous clipping.
     */
    fun dbToLinear(gainDb: Float): Float {
        val linear = 10.0.pow(gainDb.toDouble() / 20.0).toFloat()
        return linear.coerceIn(0.1f, 1.5f)
    }
}

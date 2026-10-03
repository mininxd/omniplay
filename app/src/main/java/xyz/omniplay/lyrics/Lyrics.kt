package xyz.omniplay.lyrics

import java.io.Serializable

data class LyricLine(
    val timeMs: Long,
    val text: String
) : Serializable

data class Lyrics(
    val songId: Long,
    val trackName: String,
    val artistName: String,
    val syncedLyrics: List<LyricLine>?,
    val plainLyrics: String?,
    val isInstrumental: Boolean = false,
    val syncedRaw: String? = null,
    val source: String = "LRCLIB"
) : Serializable {

    val hasSynced: Boolean
        get() = !syncedLyrics.isNullOrEmpty()

    val hasPlain: Boolean
        get() = !plainLyrics.isNullOrBlank()

    val hasAnyLyrics: Boolean
        get() = isInstrumental || hasSynced || hasPlain
}

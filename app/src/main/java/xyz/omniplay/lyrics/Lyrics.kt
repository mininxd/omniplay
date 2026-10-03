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
    val source: String = "LRCLIB",
    val isOffline: Boolean = false
) : Serializable {

    val hasSynced: Boolean
        get() = !syncedLyrics.isNullOrEmpty()

    val hasPlain: Boolean
        get() = !plainLyrics.isNullOrBlank()

    val hasAnyLyrics: Boolean
        get() = isInstrumental || hasSynced || hasPlain
}

sealed class LyricsResult {
    data class Success(val lyrics: Lyrics, val isOffline: Boolean) : LyricsResult()
    data class NotFound(val message: String = "No lyrics found for this song") : LyricsResult()
    data class Error(val message: String, val isRateLimited: Boolean = false) : LyricsResult()
    object Loading : LyricsResult()
}

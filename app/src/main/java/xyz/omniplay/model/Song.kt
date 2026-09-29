package xyz.omniplay.model

import android.net.Uri
import java.io.Serializable
import java.util.Locale

data class Song(
    val id: Long,
    val title: String,
    val artist: String,
    val album: String,
    val duration: Long,
    val contentUri: Uri,
    val albumArtUri: Uri? = null,
    val format: String = "MP3",
    val filePath: String = "",
    val fileSize: Long = 0L,
    val audioQuality: String = "",
    val isHiRes: Boolean = false
) : Serializable {

    companion object {
        fun formatTime(milliseconds: Long): String {
            if (milliseconds <= 0) return "0:00"
            val totalSeconds = milliseconds / 1000
            val minutes = totalSeconds / 60
            val seconds = totalSeconds % 60
            return String.format(Locale.US, "%d:%02d", minutes, seconds)
        }
    }
}

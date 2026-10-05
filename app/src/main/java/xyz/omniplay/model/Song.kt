package xyz.omniplay.model

import android.net.Uri
import java.io.File
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
    val isHiRes: Boolean = false,
    val dateModified: Long = 0L,
    val folderName: String = ""
) : Serializable {

    fun getResolvedFolderName(): String {
        if (folderName.isNotBlank()) return folderName
        if (filePath.isNotBlank()) {
            val file = File(filePath)
            val parentName = file.parentFile?.name
            if (!parentName.isNullOrBlank() && parentName != "0" && parentName != "emulated") {
                return parentName
            }
        }
        val uriStr = contentUri.toString()
        val decoded = try { Uri.decode(uriStr) } catch (e: Exception) { uriStr }
        val segs = decoded.split('/', ':').filter { it.isNotBlank() }
        if (segs.size >= 2) {
            val candidate = segs[segs.size - 2]
            if (candidate != "document" && candidate != "tree" && candidate != "primary" && candidate != "raw") {
                return candidate
            }
        }
        return "Music"
    }

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

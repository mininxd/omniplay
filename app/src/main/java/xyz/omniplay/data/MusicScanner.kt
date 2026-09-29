package xyz.omniplay.data

import android.content.ContentUris
import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.documentfile.provider.DocumentFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import xyz.omniplay.model.Song
import java.io.File
import java.util.Locale

class MusicScanner(private val context: Context) {

    private val supportedExtensions = setOf(
        "mp3", "wav", "flac", "aac", "m4a", "ogg", "opus", "amr", "mid", "midi", "wma"
    )

    /**
     * Scans a user-selected folder tree recursively using DocumentFile.
     */
    suspend fun scanFolder(treeUri: Uri): List<Song> = withContext(Dispatchers.IO) {
        val songsList = mutableListOf<Song>()
        val rootDoc = DocumentFile.fromTreeUri(context, treeUri) ?: return@withContext songsList

        scanDocumentFileRecursive(rootDoc, songsList)
        songsList.sortedBy { it.title.lowercase(Locale.ROOT) }
    }

    private fun scanDocumentFileRecursive(directory: DocumentFile, songsList: MutableList<Song>) {
        val files = directory.listFiles()
        for (file in files) {
            if (file.isDirectory) {
                scanDocumentFileRecursive(file, songsList)
            } else if (file.isFile) {
                val name = file.name ?: ""
                val ext = name.substringAfterLast('.', "").lowercase(Locale.ROOT)
                if (ext in supportedExtensions) {
                    val song = extractSongFromDocument(file, ext)
                    if (song != null) {
                        songsList.add(song)
                    }
                }
            }
        }
    }

    private fun extractSongFromDocument(docFile: DocumentFile, ext: String): Song? {
        val uri = docFile.uri
        val retriever = MediaMetadataRetriever()
        return try {
            retriever.setDataSource(context, uri)

            val rawTitle = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
            val rawArtist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
            val rawAlbum = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
            val durationStr = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
            val duration = durationStr?.toLongOrNull() ?: 0L

            val title = if (!rawTitle.isNullOrBlank() && rawTitle != "<unknown>") {
                rawTitle
            } else {
                docFile.name?.substringBeforeLast('.') ?: "Track ${uri.hashCode()}"
            }

            val artist = if (!rawArtist.isNullOrBlank() && rawArtist != "<unknown>") {
                rawArtist
            } else {
                "Unknown Artist"
            }

            val album = if (!rawAlbum.isNullOrBlank() && rawAlbum != "<unknown>") {
                rawAlbum
            } else {
                "Unknown Album"
            }

            Song(
                id = uri.hashCode().toLong(),
                title = title,
                artist = artist,
                album = album,
                duration = duration,
                contentUri = uri,
                albumArtUri = null, // Embedded art is loaded on-demand via AlbumArtLoader
                format = ext.uppercase(Locale.ROOT),
                filePath = docFile.name ?: "",
                fileSize = docFile.length()
            )
        } catch (e: Exception) {
            null
        } finally {
            try {
                retriever.release()
            } catch (ignored: Exception) {}
        }
    }

    /**
     * Scans MediaStore for audio tracks.
     */
    suspend fun scanMediaStore(): List<Song> = withContext(Dispatchers.IO) {
        val songsList = mutableListOf<Song>()
        val collection: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        }

        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.MIME_TYPE
        )

        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0 AND ${MediaStore.Audio.Media.DURATION} >= 5000"
        val sortOrder = "${MediaStore.Audio.Media.TITLE} COLLATE NOCASE ASC"

        try {
            context.contentResolver.query(
                collection,
                projection,
                selection,
                null,
                sortOrder
            )?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                val titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
                val artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
                val albumColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
                val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
                val dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
                val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)
                val albumIdColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)
                val mimeColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.MIME_TYPE)

                val albumArtBaseUri = Uri.parse("content://media/external/audio/albumart")

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val rawTitle = cursor.getString(titleColumn)
                    val rawArtist = cursor.getString(artistColumn)
                    val rawAlbum = cursor.getString(albumColumn)
                    val duration = cursor.getLong(durationColumn)
                    val filePath = cursor.getString(dataColumn) ?: ""
                    val fileSize = cursor.getLong(sizeColumn)
                    val albumId = cursor.getLong(albumIdColumn)
                    val mimeType = cursor.getString(mimeColumn) ?: ""

                    val contentUri = ContentUris.withAppendedId(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        id
                    )

                    val albumArtUri = if (albumId > 0) {
                        ContentUris.withAppendedId(albumArtBaseUri, albumId)
                    } else {
                        null
                    }

                    val title = if (rawTitle.isNullOrBlank() || rawTitle == "<unknown>") {
                        File(filePath).nameWithoutExtension.ifEmpty { "Audio Track $id" }
                    } else {
                        rawTitle
                    }

                    val artist = if (rawArtist.isNullOrBlank() || rawArtist == "<unknown>") {
                        "Unknown Artist"
                    } else {
                        rawArtist
                    }

                    val album = if (rawAlbum.isNullOrBlank() || rawAlbum == "<unknown>") {
                        "Unknown Album"
                    } else {
                        rawAlbum
                    }

                    val format = resolveAudioFormat(filePath, mimeType)

                    songsList.add(
                        Song(
                            id = id,
                            title = title,
                            artist = artist,
                            album = album,
                            duration = duration,
                            contentUri = contentUri,
                            albumArtUri = albumArtUri,
                            format = format,
                            filePath = filePath,
                            fileSize = fileSize
                        )
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        songsList
    }

    private fun resolveAudioFormat(filePath: String, mimeType: String): String {
        val extension = File(filePath).extension.uppercase(Locale.ROOT)
        if (extension.isNotEmpty() && extension.length in 2..5) {
            return extension
        }
        return when {
            mimeType.contains("flac", ignoreCase = true) -> "FLAC"
            mimeType.contains("wav", ignoreCase = true) -> "WAV"
            mimeType.contains("mp4", ignoreCase = true) || mimeType.contains("m4a", ignoreCase = true) -> "M4A"
            mimeType.contains("aac", ignoreCase = true) -> "AAC"
            mimeType.contains("ogg", ignoreCase = true) -> "OGG"
            mimeType.contains("opus", ignoreCase = true) -> "OPUS"
            mimeType.contains("mpeg", ignoreCase = true) || mimeType.contains("mp3", ignoreCase = true) -> "MP3"
            mimeType.contains("amr", ignoreCase = true) -> "AMR"
            mimeType.contains("midi", ignoreCase = true) || mimeType.contains("mid", ignoreCase = true) -> "MIDI"
            else -> "AUDIO"
        }
    }
}

package xyz.omniplay.data

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract
import android.provider.MediaStore
import androidx.documentfile.provider.DocumentFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import xyz.omniplay.model.Song
import xyz.omniplay.util.AudioInfoExtractor
import java.io.File
import java.util.Locale

class MusicScanner(private val context: Context) {

    private val supportedExtensions = setOf(
        "mp3", "wav", "flac", "aac", "m4a", "ogg", "opus", "amr", "mid", "midi", "wma",
        "m4b", "aiff", "aif", "ape", "webm", "oga", "mp2", "dsf", "dff"
    )

    /**
     * Resolves the relative path or folder name from a Storage Access Framework treeUri.
     */
    fun getRelativePathFromTreeUri(treeUri: Uri): String? {
        val docId = try {
            if (DocumentsContract.isDocumentUri(context, treeUri)) {
                DocumentsContract.getDocumentId(treeUri)
            } else {
                DocumentsContract.getTreeDocumentId(treeUri)
            }
        } catch (e: Exception) {
            treeUri.lastPathSegment
        } ?: return null

        return when {
            docId.startsWith("raw:") -> {
                val fullPath = docId.removePrefix("raw:")
                val standardPrefix = "/storage/emulated/0/"
                if (fullPath.startsWith(standardPrefix)) {
                    fullPath.removePrefix(standardPrefix).trim('/')
                } else {
                    fullPath.trim('/')
                }
            }
            docId.contains(':') -> {
                val afterColon = docId.substringAfter(':').trim('/')
                if (afterColon.isNotEmpty()) afterColon else null
            }
            docId.equals("downloads", ignoreCase = true) -> {
                "Download"
            }
            else -> {
                docId.trim('/')
            }
        }
    }

    /**
     * Scans a user-selected folder tree.
     * First queries MediaStore filtered by the folder path for fast retrieval and rich metadata.
     * If MediaStore returns no songs (e.g. unindexed folder, .nomedia), falls back to SAF traversal.
     */
    suspend fun scanFolder(treeUri: Uri): List<Song> = withContext(Dispatchers.IO) {
        val songsList = mutableListOf<Song>()

        // 1. Try querying MediaStore for this folder path
        val folderRelativePath = getRelativePathFromTreeUri(treeUri)
        if (!folderRelativePath.isNullOrEmpty()) {
            val mediaStoreSongs = scanMediaStoreForFolder(folderRelativePath)
            if (mediaStoreSongs.isNotEmpty()) {
                return@withContext mediaStoreSongs.sortedBy { it.title.lowercase(Locale.ROOT) }
            }
        }

        // 2. Direct SAF traversal using DocumentsContract
        try {
            val rootDocId = if (DocumentsContract.isDocumentUri(context, treeUri)) {
                DocumentsContract.getDocumentId(treeUri)
            } else {
                DocumentsContract.getTreeDocumentId(treeUri)
            }
            scanFolderDocumentsContract(treeUri, rootDocId, songsList)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // 3. Fallback to DocumentFile traversal if DocumentsContract returned empty
        if (songsList.isEmpty()) {
            try {
                val rootDoc = DocumentFile.fromTreeUri(context, treeUri)
                if (rootDoc != null) {
                    scanDocumentFileRecursive(rootDoc, songsList)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // 4. If still empty and folder was root storage, fallback to MediaStore scan
        if (songsList.isEmpty() && folderRelativePath.isNullOrEmpty()) {
            val allSongs = scanMediaStore()
            if (allSongs.isNotEmpty()) {
                return@withContext allSongs
            }
        }

        songsList.sortedBy { it.title.lowercase(Locale.ROOT) }
    }

    /**
     * Queries MediaStore for audio files within a specific folder path.
     */
    suspend fun scanMediaStoreForFolder(folderPath: String): List<Song> = withContext(Dispatchers.IO) {
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

        val cleanPath = folderPath.trim('/')
        val selection: String
        val selectionArgs: Array<String>

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0 AND (" +
                    "${MediaStore.Audio.Media.RELATIVE_PATH} LIKE ? OR " +
                    "${MediaStore.Audio.Media.RELATIVE_PATH} = ? OR " +
                    "${MediaStore.Audio.Media.DATA} LIKE ?)"
            selectionArgs = arrayOf(
                "$cleanPath/%",
                "$cleanPath/",
                "%$cleanPath/%"
            )
        } else {
            selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0 AND (${MediaStore.Audio.Media.DATA} LIKE ?)"
            selectionArgs = arrayOf("%/$cleanPath/%")
        }

        val sortOrder = "${MediaStore.Audio.Media.TITLE} COLLATE NOCASE ASC"

        try {
            context.contentResolver.query(
                collection,
                projection,
                selection,
                selectionArgs,
                sortOrder
            )?.use { cursor ->
                parseSongsFromCursor(cursor, songsList)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        songsList
    }

    private fun scanFolderDocumentsContract(
        treeUri: Uri,
        parentDocId: String,
        songsList: MutableList<Song>
    ) {
        val childrenUri = try {
            DocumentsContract.buildChildDocumentsUriUsingTree(treeUri, parentDocId)
        } catch (e: Exception) {
            null
        } ?: return

        val projection = arrayOf(
            DocumentsContract.Document.COLUMN_DOCUMENT_ID,
            DocumentsContract.Document.COLUMN_DISPLAY_NAME,
            DocumentsContract.Document.COLUMN_MIME_TYPE,
            DocumentsContract.Document.COLUMN_SIZE
        )

        val subDirs = mutableListOf<String>()

        try {
            context.contentResolver.query(
                childrenUri,
                projection,
                null,
                null,
                null
            )?.use { cursor ->
                val idCol = cursor.getColumnIndex(DocumentsContract.Document.COLUMN_DOCUMENT_ID)
                val nameCol = cursor.getColumnIndex(DocumentsContract.Document.COLUMN_DISPLAY_NAME)
                val mimeCol = cursor.getColumnIndex(DocumentsContract.Document.COLUMN_MIME_TYPE)
                val sizeCol = cursor.getColumnIndex(DocumentsContract.Document.COLUMN_SIZE)

                while (cursor.moveToNext()) {
                    val docId = if (idCol >= 0) cursor.getString(idCol) else continue
                    val name = if (nameCol >= 0) cursor.getString(nameCol) ?: "" else ""
                    val mime = if (mimeCol >= 0) cursor.getString(mimeCol) ?: "" else ""
                    val size = if (sizeCol >= 0) cursor.getLong(sizeCol) else 0L

                    if (mime == DocumentsContract.Document.MIME_TYPE_DIR) {
                        subDirs.add(docId)
                    } else {
                        val ext = name.substringAfterLast('.', "").lowercase(Locale.ROOT)
                        val isAudio = ext in supportedExtensions || mime.startsWith("audio/") || mime == "application/ogg"
                        if (isAudio) {
                            val fileDocUri = DocumentsContract.buildDocumentUriUsingTree(treeUri, docId)
                            val song = extractSongFromUri(fileDocUri, name, ext.ifEmpty { "audio" }, size)
                            songsList.add(song)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        for (subDirDocId in subDirs) {
            scanFolderDocumentsContract(treeUri, subDirDocId, songsList)
        }
    }

    private fun scanDocumentFileRecursive(directory: DocumentFile, songsList: MutableList<Song>) {
        val files = directory.listFiles()
        for (file in files) {
            if (file.isDirectory) {
                scanDocumentFileRecursive(file, songsList)
            } else if (file.isFile) {
                val name = file.name ?: ""
                val ext = name.substringAfterLast('.', "").lowercase(Locale.ROOT)
                val mime = file.type ?: ""
                if (ext in supportedExtensions || mime.startsWith("audio/") || mime == "application/ogg") {
                    val song = extractSongFromUri(file.uri, name, ext.ifEmpty { "audio" }, file.length())
                    songsList.add(song)
                }
            }
        }
    }

    private fun extractSongFromUri(
        uri: Uri,
        displayName: String,
        ext: String,
        size: Long
    ): Song {
        val fallbackTitle = if (displayName.contains('.')) {
            displayName.substringBeforeLast('.')
        } else {
            displayName.ifEmpty { "Track ${uri.hashCode()}" }
        }

        var title = fallbackTitle
        var artist = "Unknown Artist"
        var album = "Unknown Album"
        var duration = 0L

        val retriever = MediaMetadataRetriever()
        try {
            var loaded = false
            try {
                retriever.setDataSource(context, uri)
                loaded = true
            } catch (ignored: Throwable) {}

            if (!loaded) {
                try {
                    context.contentResolver.openAssetFileDescriptor(uri, "r")?.use { afd ->
                        if (afd.declaredLength < 0) {
                            retriever.setDataSource(afd.fileDescriptor)
                        } else {
                            retriever.setDataSource(afd.fileDescriptor, afd.startOffset, afd.declaredLength)
                        }
                        loaded = true
                    }
                } catch (ignored: Throwable) {}
            }

            if (!loaded) {
                try {
                    context.contentResolver.openFileDescriptor(uri, "r")?.use { pfd ->
                        retriever.setDataSource(pfd.fileDescriptor)
                        loaded = true
                    }
                } catch (ignored: Throwable) {}
            }

            if (loaded) {
                val rawTitle = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
                val rawArtist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
                val rawAlbum = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
                val durationStr = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
                val dur = durationStr?.toLongOrNull() ?: 0L
                if (dur > 0L) duration = dur

                if (!rawTitle.isNullOrBlank() && rawTitle != "<unknown>") {
                    title = rawTitle.trim()
                }
                if (!rawArtist.isNullOrBlank() && rawArtist != "<unknown>") {
                    artist = rawArtist.trim()
                }
                if (!rawAlbum.isNullOrBlank() && rawAlbum != "<unknown>") {
                    album = rawAlbum.trim()
                }
            }
        } catch (e: Throwable) {
            // Keep fallback metadata
        } finally {
            try {
                retriever.release()
            } catch (ignored: Throwable) {}
        }

        if (duration <= 0L && (ext.equals("dsf", true) || ext.equals("dff", true))) {
            try {
                context.contentResolver.openInputStream(uri)?.use { stream ->
                    xyz.omniplay.dsd.DsdHeaderParser.parse(stream)?.let { dsdHeader ->
                        if (dsdHeader.durationMs > 0L) duration = dsdHeader.durationMs
                    }
                }
            } catch (ignored: Throwable) {}
        }

        val format = if (ext.isNotEmpty() && ext.length in 2..5) {
            ext.uppercase(Locale.ROOT)
        } else {
            "AUDIO"
        }

        val audioInfo = AudioInfoExtractor.extractFromUri(context, uri, format)
        val resolvedFormat = audioInfo.format.ifEmpty { format }

        return Song(
            id = (uri.toString() + displayName).hashCode().toLong(),
            title = title,
            artist = artist,
            album = album,
            duration = duration,
            contentUri = uri,
            albumArtUri = null,
            format = resolvedFormat,
            filePath = displayName,
            fileSize = size,
            audioQuality = audioInfo.formatQualityString(),
            isHiRes = audioInfo.isHiRes
        )
    }

    /**
     * Scans MediaStore for all audio tracks across the device.
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

        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0 AND ${MediaStore.Audio.Media.DURATION} >= 1000"
        val sortOrder = "${MediaStore.Audio.Media.TITLE} COLLATE NOCASE ASC"

        try {
            context.contentResolver.query(
                collection,
                projection,
                selection,
                null,
                sortOrder
            )?.use { cursor ->
                parseSongsFromCursor(cursor, songsList)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        songsList
    }

    private fun parseSongsFromCursor(cursor: Cursor, songsList: MutableList<Song>) {
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

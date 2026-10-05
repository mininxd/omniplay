package xyz.omniplay.data

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.OpenableColumns
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
        val folderRelativePath = getRelativePathFromTreeUri(treeUri)

        // 1. Direct SAF traversal using DocumentsContract to discover all actual files on disk
        val rootFolderName = folderRelativePath?.substringAfterLast('/')?.ifEmpty { "Music" } ?: "Music"
        try {
            val rootDocId = if (DocumentsContract.isDocumentUri(context, treeUri)) {
                DocumentsContract.getDocumentId(treeUri)
            } else {
                DocumentsContract.getTreeDocumentId(treeUri)
            }
            scanFolderDocumentsContract(treeUri, rootDocId, rootFolderName, songsList)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // 2. Fallback to DocumentFile traversal if DocumentsContract returned empty
        if (songsList.isEmpty()) {
            try {
                val rootDoc = DocumentFile.fromTreeUri(context, treeUri)
                if (rootDoc != null) {
                    val rootName = rootDoc.name ?: rootFolderName
                    scanDocumentFileRecursive(rootDoc, rootName, songsList)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // 3. Query MediaStore for this folder path with relaxed filter
        val mediaStoreSongs = if (!folderRelativePath.isNullOrEmpty()) {
            scanMediaStoreForFolder(folderRelativePath)
        } else {
            emptyList()
        }

        // Merge SAF-discovered songs with MediaStore songs (ensuring files like 04. Basket Case.flac are never omitted)
        val finalSongs = if (songsList.isNotEmpty()) {
            if (mediaStoreSongs.isNotEmpty()) {
                val mediaStoreByPath = mediaStoreSongs.associateBy { it.filePath.lowercase(Locale.ROOT) }
                val mediaStoreByName = mediaStoreSongs.groupBy { File(it.filePath).name.lowercase(Locale.ROOT) }

                songsList.map { safSong ->
                    val safFileName = File(safSong.filePath).name.lowercase(Locale.ROOT)
                    val match = (if (safSong.filePath.isNotBlank()) mediaStoreByPath[safSong.filePath.lowercase(Locale.ROOT)] else null)
                        ?: run {
                            val candidateList = mediaStoreByName[safFileName]
                            if (candidateList != null && candidateList.size == 1) {
                                val candidate = candidateList.first()
                                val sizeMatches = safSong.fileSize <= 0L || candidate.fileSize <= 0L ||
                                        kotlin.math.abs(safSong.fileSize - candidate.fileSize) < 4096
                                val durationMatches = safSong.duration <= 0L || candidate.duration <= 0L ||
                                        kotlin.math.abs(safSong.duration - candidate.duration) < 3000
                                if (sizeMatches && durationMatches) candidate else null
                            } else null
                        }

                    if (match != null) {
                        val resolvedFolder = if (safSong.folderName.isNotEmpty()) {
                            safSong.folderName
                        } else if (match.folderName.isNotEmpty()) {
                            match.folderName
                        } else {
                            File(match.filePath).parentFile?.name ?: ""
                        }
                        safSong.copy(
                            albumArtUri = safSong.albumArtUri ?: (if (match.album != "Unknown Album") match.albumArtUri else null),
                            title = if (safSong.title == safSong.filePath.substringBeforeLast('.')) match.title else safSong.title,
                            artist = if (safSong.artist == "Unknown Artist") match.artist else safSong.artist,
                            album = if (safSong.album == "Unknown Album") match.album else safSong.album,
                            duration = if (safSong.duration > 0L) safSong.duration else match.duration,
                            dateModified = if (match.dateModified > 0L) match.dateModified else safSong.dateModified,
                            folderName = resolvedFolder
                        )
                    } else {
                        safSong
                    }
                }
            } else {
                songsList
            }
        } else if (mediaStoreSongs.isNotEmpty()) {
            mediaStoreSongs
        } else if (folderRelativePath.isNullOrEmpty()) {
            scanMediaStore()
        } else {
            emptyList()
        }

        finalSongs.sortedBy { it.title.lowercase(Locale.ROOT) }
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
            MediaStore.Audio.Media.MIME_TYPE,
            MediaStore.Audio.Media.DATE_MODIFIED
        )

        val cleanPath = folderPath.trim('/')
        val selection: String
        val selectionArgs: Array<String>

        val audioFilter = "(${MediaStore.Audio.Media.IS_MUSIC} != 0 OR " +
                "${MediaStore.Audio.Media.MIME_TYPE} LIKE 'audio/%' OR " +
                "${MediaStore.Audio.Media.DATA} LIKE '%.flac' OR " +
                "${MediaStore.Audio.Media.DATA} LIKE '%.FLAC' OR " +
                "${MediaStore.Audio.Media.DATA} LIKE '%.mp3' OR " +
                "${MediaStore.Audio.Media.DATA} LIKE '%.m4a' OR " +
                "${MediaStore.Audio.Media.DATA} LIKE '%.wav')"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            selection = "$audioFilter AND (" +
                    "${MediaStore.Audio.Media.RELATIVE_PATH} LIKE ? OR " +
                    "${MediaStore.Audio.Media.RELATIVE_PATH} = ? OR " +
                    "${MediaStore.Audio.Media.DATA} LIKE ?)"
            selectionArgs = arrayOf(
                "$cleanPath/%",
                "$cleanPath/",
                "%$cleanPath/%"
            )
        } else {
            selection = "$audioFilter AND (${MediaStore.Audio.Media.DATA} LIKE ?)"
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
        currentFolderName: String,
        songsList: MutableList<Song>,
        visitedDocIds: MutableSet<String> = mutableSetOf()
    ) {
        if (!visitedDocIds.add(parentDocId)) return
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

        val subDirs = mutableListOf<Pair<String, String>>()

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
                val lastModCol = cursor.getColumnIndex(DocumentsContract.Document.COLUMN_LAST_MODIFIED)

                while (cursor.moveToNext()) {
                    val docId = if (idCol >= 0) cursor.getString(idCol) else continue
                    val name = if (nameCol >= 0) cursor.getString(nameCol) ?: "" else ""
                    val mime = if (mimeCol >= 0) cursor.getString(mimeCol) ?: "" else ""
                    val size = if (sizeCol >= 0) cursor.getLong(sizeCol) else 0L
                    val lastMod = if (lastModCol >= 0) cursor.getLong(lastModCol) else 0L

                    if (mime == DocumentsContract.Document.MIME_TYPE_DIR) {
                        val subName = name.ifEmpty { currentFolderName }
                        subDirs.add(Pair(docId, subName))
                    } else {
                        val ext = name.substringAfterLast('.', "").lowercase(Locale.ROOT)
                        val isAudio = ext in supportedExtensions ||
                                mime.startsWith("audio/") ||
                                mime.contains("flac", ignoreCase = true) ||
                                mime.contains("ogg", ignoreCase = true) ||
                                (mime == "application/octet-stream" && ext in supportedExtensions)
                        if (isAudio) {
                            val fileDocUri = DocumentsContract.buildDocumentUriUsingTree(treeUri, docId)
                            val song = extractSongFromUri(fileDocUri, name, ext.ifEmpty { "audio" }, size, lastMod, currentFolderName)
                            songsList.add(song)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        for ((subDirDocId, dirName) in subDirs) {
            scanFolderDocumentsContract(treeUri, subDirDocId, dirName, songsList, visitedDocIds)
        }
    }

    private fun scanDocumentFileRecursive(directory: DocumentFile, currentFolderName: String, songsList: MutableList<Song>) {
        val files = directory.listFiles()
        for (file in files) {
            if (file.isDirectory) {
                val dirName = file.name ?: currentFolderName
                scanDocumentFileRecursive(file, dirName, songsList)
            } else if (file.isFile) {
                val name = file.name ?: ""
                val ext = name.substringAfterLast('.', "").lowercase(Locale.ROOT)
                val mime = file.type ?: ""
                val isAudio = ext in supportedExtensions ||
                                mime.startsWith("audio/") ||
                                mime.contains("flac", ignoreCase = true) ||
                                mime.contains("ogg", ignoreCase = true) ||
                                (mime == "application/octet-stream" && ext in supportedExtensions)
                if (isAudio) {
                    val song = extractSongFromUri(file.uri, name, ext.ifEmpty { "audio" }, file.length(), file.lastModified(), currentFolderName)
                    songsList.add(song)
                }
            }
        }
    }

    fun extractSongFromUri(
        uri: Uri,
        displayName: String = "",
        ext: String = "",
        size: Long = 0L,
        dateModified: Long = 0L,
        folderName: String = ""
    ): Song {
        var resolvedName = displayName
        var resolvedSize = size
        if (resolvedName.isEmpty()) {
            try {
                context.contentResolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME, OpenableColumns.SIZE), null, null, null)?.use { cursor ->
                    if (cursor.moveToFirst()) {
                        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                        if (nameIndex != -1) {
                            resolvedName = cursor.getString(nameIndex) ?: ""
                        }
                        val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                        if (sizeIndex != -1 && resolvedSize <= 0L) {
                            resolvedSize = cursor.getLong(sizeIndex)
                        }
                    }
                }
            } catch (ignored: Throwable) {}
        }
        if (resolvedName.isEmpty()) {
            resolvedName = uri.lastPathSegment?.substringAfterLast('/') ?: "audio_track"
        }
        val resolvedExt = if (ext.isNotEmpty()) ext else resolvedName.substringAfterLast('.', "").lowercase(Locale.ROOT)

        val fallbackTitle = if (resolvedName.contains('.')) {
            resolvedName.substringBeforeLast('.')
        } else {
            resolvedName.ifEmpty { "Track ${uri.hashCode()}" }
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

        if (resolvedExt.equals("flac", true)) {
            try {
                context.contentResolver.openInputStream(uri)?.use { stream ->
                    xyz.omniplay.util.FlacHeaderParser.parse(stream)?.let { flacHeader ->
                        if (!flacHeader.title.isNullOrBlank()) title = flacHeader.title
                        if (!flacHeader.artist.isNullOrBlank()) artist = flacHeader.artist
                        if (!flacHeader.album.isNullOrBlank()) album = flacHeader.album
                        if (flacHeader.durationMs > 0L) duration = flacHeader.durationMs
                    }
                }
            } catch (ignored: Throwable) {}
        }

        if (duration <= 0L && (resolvedExt.equals("dsf", true) || resolvedExt.equals("dff", true))) {
            try {
                context.contentResolver.openInputStream(uri)?.use { stream ->
                    xyz.omniplay.dsd.DsdHeaderParser.parse(stream)?.let { dsdHeader ->
                        if (dsdHeader.durationMs > 0L) duration = dsdHeader.durationMs
                    }
                }
            } catch (ignored: Throwable) {}
        }

        val format = if (resolvedExt.isNotEmpty() && resolvedExt.length in 2..5) {
            resolvedExt.uppercase(Locale.ROOT)
        } else {
            "AUDIO"
        }

        val audioInfo = AudioInfoExtractor.extractFromUri(context, uri, format)
        val resolvedFormat = audioInfo.format.ifEmpty { format }

        val uniqueId = (java.util.UUID.nameUUIDFromBytes((uri.toString() + resolvedName).toByteArray()).mostSignificantBits and Long.MAX_VALUE).let {
            if (it == 0L) 1L else it
        }

        return Song(
            id = uniqueId,
            title = title,
            artist = artist,
            album = album,
            duration = duration,
            contentUri = uri,
            albumArtUri = null,
            format = resolvedFormat,
            filePath = resolvedName,
            fileSize = resolvedSize,
            audioQuality = audioInfo.formatQualityString(),
            isHiRes = audioInfo.isHiRes,
            dateModified = if (dateModified > 0L) dateModified else if (resolvedName.isNotEmpty()) File(resolvedName).lastModified() else 0L,
            folderName = folderName
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
            MediaStore.Audio.Media.MIME_TYPE,
            MediaStore.Audio.Media.DATE_MODIFIED
        )

        val selection = "(${MediaStore.Audio.Media.IS_MUSIC} != 0 OR " +
                "${MediaStore.Audio.Media.MIME_TYPE} LIKE 'audio/%' OR " +
                "${MediaStore.Audio.Media.DATA} LIKE '%.flac' OR " +
                "${MediaStore.Audio.Media.DATA} LIKE '%.FLAC' OR " +
                "${MediaStore.Audio.Media.DATA} LIKE '%.mp3' OR " +
                "${MediaStore.Audio.Media.DATA} LIKE '%.m4a' OR " +
                "${MediaStore.Audio.Media.DATA} LIKE '%.wav' OR " +
                "${MediaStore.Audio.Media.DATA} LIKE '%.ogg' OR " +
                "${MediaStore.Audio.Media.DATA} LIKE '%.opus' OR " +
                "${MediaStore.Audio.Media.DATA} LIKE '%.dsf' OR " +
                "${MediaStore.Audio.Media.DATA} LIKE '%.dff')"
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
        val idColumn = cursor.getColumnIndex(MediaStore.Audio.Media._ID)
        val titleColumn = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
        val artistColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)
        val albumColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)
        val durationColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)
        val dataColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DATA)
        val sizeColumn = cursor.getColumnIndex(MediaStore.Audio.Media.SIZE)
        val albumIdColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)
        val mimeColumn = cursor.getColumnIndex(MediaStore.Audio.Media.MIME_TYPE)
        val dateModifiedColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DATE_MODIFIED)

        val albumArtBaseUri = Uri.parse("content://media/external/audio/albumart")

        while (cursor.moveToNext()) {
            val id = if (idColumn >= 0) cursor.getLong(idColumn) else cursor.position.toLong()
            val rawTitle = if (titleColumn >= 0) cursor.getString(titleColumn) else null
            val rawArtist = if (artistColumn >= 0) cursor.getString(artistColumn) else null
            val rawAlbum = if (albumColumn >= 0) cursor.getString(albumColumn) else null
            val duration = if (durationColumn >= 0) cursor.getLong(durationColumn) else 0L
            val filePath = if (dataColumn >= 0) cursor.getString(dataColumn) ?: "" else ""
            val fileSize = if (sizeColumn >= 0) cursor.getLong(sizeColumn) else 0L
            val albumId = if (albumIdColumn >= 0) cursor.getLong(albumIdColumn) else -1L
            val mimeType = if (mimeColumn >= 0) cursor.getString(mimeColumn) ?: "" else ""
            val dateSec = if (dateModifiedColumn >= 0) cursor.getLong(dateModifiedColumn) else 0L
            val dateModified = if (dateSec > 0) dateSec * 1000L else if (filePath.isNotEmpty()) File(filePath).lastModified() else 0L

            val contentUri = ContentUris.withAppendedId(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                id
            )

            val albumArtUri = if (albumId > 0) {
                ContentUris.withAppendedId(albumArtBaseUri, albumId)
            } else {
                null
            }

            var title = if (rawTitle.isNullOrBlank() || rawTitle == "<unknown>") {
                File(filePath).nameWithoutExtension.ifEmpty { "Audio Track $id" }
            } else {
                rawTitle
            }

            var artist = if (rawArtist.isNullOrBlank() || rawArtist == "<unknown>") {
                "Unknown Artist"
            } else {
                rawArtist
            }

            var album = if (rawAlbum.isNullOrBlank() || rawAlbum == "<unknown>") {
                "Unknown Album"
            } else {
                rawAlbum
            }

            var resolvedDuration = duration

            if ((resolvedDuration <= 0L || artist == "Unknown Artist" || title.startsWith("Audio Track")) &&
                filePath.endsWith(".flac", ignoreCase = true)
            ) {
                try {
                    val file = File(filePath)
                    if (file.exists()) {
                        file.inputStream().use { stream ->
                            xyz.omniplay.util.FlacHeaderParser.parse(stream)?.let { flacHeader ->
                                if (!flacHeader.title.isNullOrBlank()) title = flacHeader.title
                                if (!flacHeader.artist.isNullOrBlank()) artist = flacHeader.artist
                                if (!flacHeader.album.isNullOrBlank()) album = flacHeader.album
                                if (flacHeader.durationMs > 0L) resolvedDuration = flacHeader.durationMs
                            }
                        }
                    }
                } catch (ignored: Throwable) {}
            }

            val format = resolveAudioFormat(filePath, mimeType)
            val isFormatHiRes = format.startsWith("DSD", true) || format == "DSF" || format == "DFF" ||
                    (format == "FLAC" && (fileSize > 25_000_000L || (resolvedDuration > 0 && (fileSize * 8) / resolvedDuration > 1500)))

            val parentFolderName = if (filePath.isNotEmpty()) {
                val p = File(filePath).parentFile?.name ?: ""
                if (p == "0" || p == "emulated") "" else p
            } else ""

            songsList.add(
                Song(
                    id = id,
                    title = title,
                    artist = artist,
                    album = album,
                    duration = resolvedDuration,
                    contentUri = contentUri,
                    albumArtUri = albumArtUri,
                    format = format,
                    filePath = filePath,
                    fileSize = fileSize,
                    isHiRes = isFormatHiRes,
                    dateModified = dateModified,
                    folderName = parentFolderName
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

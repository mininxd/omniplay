package xyz.omniplay.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.DocumentsContract
import xyz.omniplay.R
import xyz.omniplay.model.Song
import xyz.omniplay.ui.MainActivity
import java.io.File
import java.util.Locale

object MusicFolderManager {

    data class FolderMetadata(
        val uri: String,
        val displayName: String,
        val parentLabel: String,
        val rootTitle: String,
        val relativePathLower: String,
        val volumeId: String,
        val basePathPrefixLower: String
    )

    @Volatile
    private var cachedFoldersList: List<String>? = null
    @Volatile
    private var cachedMetadataList: List<FolderMetadata>? = null
    @Volatile
    private var cachedHasDuplicates: Boolean = false

    private fun clearCache() {
        cachedFoldersList = null
        cachedMetadataList = null
        cachedHasDuplicates = false
    }

    /**
     * Retrieves all saved music folder URI strings.
     * Backwards-compatible: if KEY_MUSIC_FOLDERS_SET is not yet populated,
     * falls back to legacy KEY_MUSIC_FOLDER_URI and migrates it.
     */
    fun getFolders(context: Context): List<String> {
        cachedFoldersList?.let { return it }
        val prefs = context.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
        val set = prefs.getStringSet(MainActivity.KEY_MUSIC_FOLDERS_SET, null)
        val result = if (set != null) {
            set.toList()
        } else {
            val legacy = prefs.getString(MainActivity.KEY_MUSIC_FOLDER_URI, null)
            if (!legacy.isNullOrEmpty()) {
                val initialSet = setOf(legacy)
                prefs.edit().putStringSet(MainActivity.KEY_MUSIC_FOLDERS_SET, initialSet).apply()
                initialSet.toList()
            } else {
                emptyList()
            }
        }
        cachedFoldersList = result
        return result
    }

    /**
     * Adds a new folder URI to the saved list and takes persistable URI permission.
     * Returns true if newly added, false if already present.
     */
    fun addFolder(context: Context, treeUri: Uri): Boolean {
        try {
            context.contentResolver.takePersistableUriPermission(
                treeUri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        } catch (_: Exception) {}

        val uriString = treeUri.toString()
        val prefs = context.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
        val currentSet = HashSet(getFolders(context))
        val added = currentSet.add(uriString)
        if (added) {
            clearCache()
            prefs.edit()
                .putStringSet(MainActivity.KEY_MUSIC_FOLDERS_SET, currentSet)
                .putString(MainActivity.KEY_MUSIC_FOLDER_URI, currentSet.firstOrNull() ?: "")
                .apply()
        }
        return added
    }

    /**
     * Removes a folder URI from the saved list.
     * Returns true if removed, false if not found.
     */
    fun removeFolder(context: Context, uriString: String): Boolean {
        try {
            context.contentResolver.releasePersistableUriPermission(
                Uri.parse(uriString),
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        } catch (_: Exception) {}

        val prefs = context.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
        val currentSet = HashSet(getFolders(context))
        val removed = currentSet.remove(uriString)
        if (removed) {
            clearCache()
            prefs.edit()
                .putStringSet(MainActivity.KEY_MUSIC_FOLDERS_SET, currentSet)
                .putString(MainActivity.KEY_MUSIC_FOLDER_URI, currentSet.firstOrNull() ?: "")
                .apply()
        }
        return removed
    }

    fun extractDocId(uriString: String): String {
        return try {
            val uri = Uri.parse(uriString)
            try {
                DocumentsContract.getTreeDocumentId(uri)
            } catch (_: Exception) {
                try {
                    DocumentsContract.getDocumentId(uri)
                } catch (_: Exception) {
                    uri.lastPathSegment ?: uriString
                }
            }
        } catch (_: Exception) {
            uriString
        }
    }

    fun getFolderStorageInfo(uriString: String): Pair<String, String> {
        val docId = extractDocId(uriString)
        return when {
            docId.startsWith("raw:") -> {
                val raw = docId.removePrefix("raw:").trim('/')
                Pair("", raw)
            }
            docId.contains(':') -> {
                val vol = docId.substringBefore(':')
                val path = docId.substringAfter(':').trim('/')
                Pair(vol, path)
            }
            else -> Pair("", docId.trim('/'))
        }
    }

    fun getFolderBasePathPrefix(uriString: String): String {
        val (vol, path) = getFolderStorageInfo(uriString)
        return when {
            vol.equals("primary", ignoreCase = true) -> {
                if (path.isEmpty()) "/storage/emulated/0" else "/storage/emulated/0/$path"
            }
            vol.isNotEmpty() -> {
                if (path.isEmpty()) "/storage/$vol" else "/storage/$vol/$path"
            }
            path.startsWith('/') -> path
            path.isNotEmpty() -> "/$path"
            else -> ""
        }
    }

    private fun extractRelativePathFromDocId(docId: String): String {
        return when {
            docId.startsWith("raw:") -> docId.removePrefix("raw:").trim('/')
            docId.contains(':') -> docId.substringAfter(':').trim('/')
            else -> docId.trim('/')
        }
    }

    /**
     * Returns a human-friendly name for the folder (e.g. "Music" or "Downloads").
     * Fast in-memory parsing from the Document ID; avoids slow DocumentFile / ContentProvider Binder queries.
     */
    fun getDisplayName(context: Context, uriString: String): String {
        cachedMetadataList?.firstOrNull { it.uri == uriString }?.let {
            return it.displayName
        }
        return try {
            val docId = extractDocId(uriString)
            val name = when {
                docId.startsWith("raw:") -> {
                    val raw = docId.removePrefix("raw:").trimEnd('/')
                    File(raw).name
                }
                docId.contains(':') -> {
                    val path = docId.substringAfter(':').trim('/')
                    if (path.contains('/')) path.substringAfterLast('/') else path
                }
                else -> {
                    val trimmed = docId.trim('/')
                    if (trimmed.contains('/')) trimmed.substringAfterLast('/') else trimmed
                }
            }
            if (name.isNotBlank()) name else "Music"
        } catch (_: Exception) {
            "Music"
        }
    }

    /**
     * Returns a human-friendly path or description for the folder.
     */
    fun getDisplayPath(context: Context, uriString: String): String {
        return try {
            val docId = extractDocId(uriString)
            when {
                docId.startsWith("raw:") -> docId.removePrefix("raw:")
                docId.contains(':') -> {
                    val storageType = docId.substringBefore(':')
                    val path = docId.substringAfter(':').trim('/')
                    if (storageType.equals("primary", ignoreCase = true)) {
                        if (path.isEmpty()) "Internal Storage" else "Internal Storage / $path"
                    } else {
                        if (path.isEmpty()) storageType else "$storageType / $path"
                    }
                }
                else -> docId
            }
        } catch (_: Exception) {
            uriString
        }
    }

    /**
     * Returns a summary description for the Settings screen subtitle.
     */
    fun getFoldersSummary(context: Context): String {
        val folders = getFolders(context)
        return when {
            folders.isEmpty() -> context.getString(R.string.music_folder_desc)
            folders.size == 1 -> getDisplayName(context, folders[0])
            else -> context.getString(R.string.folders_count_multiple, folders.size)
        }
    }

    /**
     * Returns the immediate parent folder name or storage volume for disambiguation.
     * e.g., for "/alanwalker/music" -> "alanwalker"
     *       for "/queen/music" -> "queen"
     *       for "/music" on internal storage -> "Internal Storage"
     *       for "/music" on SD card -> volume name (e.g. "SD Card" or "1234-5678")
     */
    fun getFolderParentLabel(context: Context, uriString: String): String {
        cachedMetadataList?.firstOrNull { it.uri == uriString }?.let {
            if (it.parentLabel.isNotEmpty()) return it.parentLabel
        }
        return try {
            val docId = extractDocId(uriString)
            when {
                docId.startsWith("raw:") -> {
                    val rawPath = docId.removePrefix("raw:").trimEnd('/')
                    val parent = File(rawPath).parentFile?.name
                    if (parent.isNullOrBlank() || parent == "0" || parent == "emulated") {
                        "Internal Storage"
                    } else {
                        parent
                    }
                }
                docId.contains(':') -> {
                    val storageType = docId.substringBefore(':')
                    val path = docId.substringAfter(':').trim('/')
                    if (path.contains('/')) {
                        path.substringBeforeLast('/').substringAfterLast('/')
                    } else {
                        if (storageType.equals("primary", ignoreCase = true)) {
                            "Internal Storage"
                        } else {
                            storageType
                        }
                    }
                }
                else -> {
                    val trimmed = docId.trim('/')
                    if (trimmed.contains('/')) {
                        trimmed.substringBeforeLast('/').substringAfterLast('/')
                    } else {
                        "Storage"
                    }
                }
            }
        } catch (_: Exception) {
            "Storage"
        }
    }

    fun ensureMetadataCache(context: Context): List<FolderMetadata> {
        val currentFolders = getFolders(context)
        val existing = cachedMetadataList
        if (existing != null && cachedFoldersList == currentFolders) {
            return existing
        }

        val displayNames = currentFolders.map { getDisplayName(context, it) }
        val nameCounts = displayNames.groupingBy { it.lowercase(Locale.ROOT) }.eachCount()
        val hasDuplicates = nameCounts.values.any { it > 1 }

        val metadataList = currentFolders.mapIndexed { idx, uriStr ->
            val disp = displayNames[idx]
            val isDup = (nameCounts[disp.lowercase(Locale.ROOT)] ?: 0) > 1
            val parentLabel = if (isDup) getFolderParentLabel(context, uriStr) else ""
            val title = if (isDup && parentLabel.isNotBlank()) "$disp ($parentLabel)" else disp
            val relPath = extractRelativePathFromDocId(extractDocId(uriStr)).lowercase(Locale.ROOT)
            val storageInfo = getFolderStorageInfo(uriStr)
            val basePrefix = getFolderBasePathPrefix(uriStr).lowercase(Locale.ROOT)
            FolderMetadata(
                uri = uriStr,
                displayName = disp,
                parentLabel = parentLabel,
                rootTitle = title,
                relativePathLower = relPath,
                volumeId = storageInfo.first,
                basePathPrefixLower = basePrefix
            )
        }.sortedByDescending { it.basePathPrefixLower.length }

        cachedFoldersList = currentFolders
        cachedMetadataList = metadataList
        cachedHasDuplicates = hasDuplicates
        return metadataList
    }

    /**
     * Checks if two or more configured folders share the same display name (e.g. two "Music" folders).
     */
    fun hasDuplicateFolderNames(context: Context): Boolean {
        ensureMetadataCache(context)
        return cachedHasDuplicates
    }

    /**
     * Returns the display title for a root folder.
     * If multiple configured folders share the same display name, disambiguates by appending the parent label:
     * e.g. "Music (alanwalker)" vs "Music (queen)".
     * Otherwise returns just the display name (e.g. "Music", "Podcasts").
     */
    fun getRootFolderTitle(context: Context, uriString: String): String {
        val metadata = ensureMetadataCache(context)
        return metadata.firstOrNull { it.uri == uriString }?.rootTitle ?: getDisplayName(context, uriString)
    }

    /**
     * Checks if a song belongs to the specified folder metadata.
     */
    fun isSongInFolder(folderMeta: FolderMetadata, song: Song): Boolean {
        val filePathLower = song.filePath.lowercase(Locale.ROOT).trim()
        val basePrefix = folderMeta.basePathPrefixLower

        // 1. Direct path check: song filePath starts with basePathPrefix
        if (filePathLower.isNotEmpty() && basePrefix.isNotEmpty()) {
            if (filePathLower.startsWith("$basePrefix/") || filePathLower == basePrefix) {
                return true
            }
            if (folderMeta.volumeId.isNotEmpty() && !folderMeta.volumeId.equals("primary", ignoreCase = true)) {
                val volLower = folderMeta.volumeId.lowercase(Locale.ROOT)
                val relLower = folderMeta.relativePathLower
                if (filePathLower.contains("/$volLower/") && (relLower.isEmpty() || filePathLower.contains("/$relLower/"))) {
                    return true
                }
            }
        }

        // 2. SAF contentUri matching
        val songUriStr = song.contentUri.toString()
        if (songUriStr.contains(folderMeta.uri, ignoreCase = true)) {
            return true
        }

        val decodedUri = try { Uri.decode(songUriStr) } catch (_: Exception) { "" }
        if (folderMeta.volumeId.isNotEmpty()) {
            val volLower = folderMeta.volumeId.lowercase(Locale.ROOT)
            val relLower = folderMeta.relativePathLower
            if (decodedUri.contains(volLower, ignoreCase = true)) {
                if (relLower.isEmpty() || decodedUri.contains(relLower, ignoreCase = true)) {
                    return true
                }
            }
        }

        // 3. Fallback: folderName matching
        val songFolder = song.folderName.trim()
        if (songFolder.isNotEmpty()) {
            if (songFolder.equals(folderMeta.rootTitle, ignoreCase = true) ||
                songFolder.startsWith("${folderMeta.rootTitle}/", ignoreCase = true)
            ) {
                return true
            }
        }

        return false
    }

    /**
     * Verifies if a song belongs to ANY of the currently configured music folders.
     */
    fun isSongInConfiguredFolders(context: Context, song: Song): Boolean {
        val metadata = ensureMetadataCache(context)
        if (metadata.isEmpty()) return false
        return metadata.any { isSongInFolder(it, song) }
    }

    /**
     * Finds the configured folder URI that this song belongs to.
     */
    fun findMatchingFolderUri(context: Context, song: Song): String? {
        val metadata = ensureMetadataCache(context)
        if (metadata.isEmpty()) return null
        if (metadata.size == 1) return metadata[0].uri

        for (meta in metadata) {
            if (isSongInFolder(meta, song)) {
                return meta.uri
            }
        }

        return metadata.firstOrNull()?.uri
    }

    /**
     * Returns the root folder title for a song, disambiguated if there are same-named roots.
     */
    fun getSongRootFolderTitle(context: Context, song: Song): String? {
        val metadata = ensureMetadataCache(context)
        for (meta in metadata) {
            if (isSongInFolder(meta, song)) {
                return meta.rootTitle
            }
        }
        val matchingUri = findMatchingFolderUri(context, song) ?: return null
        return getRootFolderTitle(context, matchingUri)
    }

    /**
     * Resolves any subpath inside the matched configured folder.
     * e.g., if song is at "/alanwalker/music/Rock/track.mp3" and root is "/alanwalker/music", returns "Rock".
     */
    fun getSubPathWithinFolder(context: Context, folderUriString: String, song: Song): String? {
        val metadata = ensureMetadataCache(context)
        val meta = metadata.firstOrNull { it.uri == folderUriString }
        val rootPathLower = meta?.relativePathLower ?: extractRelativePathFromDocId(extractDocId(folderUriString)).lowercase(Locale.ROOT)

        val filePath = song.filePath.trim()
        if (filePath.isNotEmpty() && rootPathLower.isNotEmpty()) {
            val idx = filePath.lowercase(Locale.ROOT).indexOf(rootPathLower)
            if (idx >= 0) {
                val afterRoot = filePath.substring(idx + rootPathLower.length).trim('/')
                if (afterRoot.contains('/')) {
                    val sub = afterRoot.substringBeforeLast('/').trim('/')
                    if (sub.isNotBlank()) return sub
                }
            }
        }

        val decodedUri = try { Uri.decode(song.contentUri.toString()) } catch (_: Exception) { "" }
        if (decodedUri.isNotEmpty() && rootPathLower.isNotEmpty()) {
            val idx = decodedUri.lowercase(Locale.ROOT).lastIndexOf(rootPathLower)
            if (idx >= 0) {
                val afterRoot = decodedUri.substring(idx + rootPathLower.length).trim('/')
                if (afterRoot.contains('/')) {
                    val sub = afterRoot.substringBeforeLast('/').trim('/')
                    if (sub.isNotBlank() && !sub.contains("document") && !sub.contains("tree")) return sub
                }
            }
        }

        val raw = song.folderName.trim().trim('/')
        if (raw.isNotBlank()) {
            val rootTitle = meta?.rootTitle ?: getRootFolderTitle(context, folderUriString)
            if (raw.startsWith(rootTitle, ignoreCase = true)) {
                val sub = raw.removePrefix(rootTitle).trim('/')
                if (sub.isNotBlank()) return sub
            }
            val leafName = meta?.displayName ?: getDisplayName(context, folderUriString)
            if (raw.startsWith(leafName, ignoreCase = true)) {
                val sub = raw.removePrefix(leafName).trim('/')
                if (sub.isNotBlank()) return sub
            }
        }

        return null
    }
}

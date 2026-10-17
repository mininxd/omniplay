package xyz.omniplay.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.DocumentsContract
import androidx.documentfile.provider.DocumentFile
import xyz.omniplay.R
import xyz.omniplay.model.Song
import xyz.omniplay.ui.MainActivity
import java.io.File
import java.util.Locale

object MusicFolderManager {

    /**
     * Retrieves all saved music folder URI strings.
     * Backwards-compatible: if KEY_MUSIC_FOLDERS_SET is not yet populated,
     * falls back to legacy KEY_MUSIC_FOLDER_URI and migrates it.
     */
    fun getFolders(context: Context): List<String> {
        val prefs = context.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
        val set = prefs.getStringSet(MainActivity.KEY_MUSIC_FOLDERS_SET, null)
        if (set != null) {
            return set.toList()
        }
        val legacy = prefs.getString(MainActivity.KEY_MUSIC_FOLDER_URI, null)
        if (!legacy.isNullOrEmpty()) {
            val initialSet = setOf(legacy)
            prefs.edit().putStringSet(MainActivity.KEY_MUSIC_FOLDERS_SET, initialSet).apply()
            return initialSet.toList()
        }
        return emptyList()
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
        } catch (ignored: Exception) {}

        val uriString = treeUri.toString()
        val prefs = context.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
        val currentSet = HashSet(getFolders(context))
        val added = currentSet.add(uriString)
        if (added) {
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
        } catch (ignored: Exception) {}

        val prefs = context.getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE)
        val currentSet = HashSet(getFolders(context))
        val removed = currentSet.remove(uriString)
        if (removed) {
            prefs.edit()
                .putStringSet(MainActivity.KEY_MUSIC_FOLDERS_SET, currentSet)
                .putString(MainActivity.KEY_MUSIC_FOLDER_URI, currentSet.firstOrNull() ?: "")
                .apply()
        }
        return removed
    }

    /**
     * Returns a human-friendly name for the folder (e.g. "Music" or "Downloads").
     */
    fun getDisplayName(context: Context, uriString: String): String {
        return try {
            val uri = Uri.parse(uriString)
            val docFile = DocumentFile.fromTreeUri(context, uri)
            val name = docFile?.name
            if (!name.isNullOrBlank()) {
                name
            } else {
                val docId = try {
                    if (DocumentsContract.isDocumentUri(context, uri)) {
                        DocumentsContract.getDocumentId(uri)
                    } else {
                        DocumentsContract.getTreeDocumentId(uri)
                    }
                } catch (e: Exception) {
                    uri.lastPathSegment
                } ?: uriString
                docId.substringAfterLast(':').substringAfterLast('/').ifEmpty { uriString }
            }
        } catch (e: Exception) {
            uriString
        }
    }

    /**
     * Returns a human-friendly path or description for the folder.
     */
    fun getDisplayPath(context: Context, uriString: String): String {
        return try {
            val uri = Uri.parse(uriString)
            val docId = try {
                if (DocumentsContract.isDocumentUri(context, uri)) {
                    DocumentsContract.getDocumentId(uri)
                } else {
                    DocumentsContract.getTreeDocumentId(uri)
                }
            } catch (e: Exception) {
                uri.lastPathSegment
            } ?: uriString

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
        } catch (e: Exception) {
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
     * Checks if two or more configured folders share the same display name (e.g. two "Music" folders).
     */
    fun hasDuplicateFolderNames(context: Context): Boolean {
        val folders = getFolders(context)
        if (folders.size <= 1) return false
        val names = folders.map { getDisplayName(context, it).lowercase(Locale.ROOT) }
        return names.size != names.distinct().size
    }

    /**
     * Returns the immediate parent folder name or storage volume for disambiguation.
     * e.g., for "/alanwalker/music" -> "alanwalker"
     *       for "/queen/music" -> "queen"
     *       for "/music" on internal storage -> "Internal Storage"
     *       for "/music" on SD card -> volume name (e.g. "SD Card" or "1234-5678")
     */
    fun getFolderParentLabel(context: Context, uriString: String): String {
        return try {
            val uri = Uri.parse(uriString)
            val docId = try {
                if (DocumentsContract.isDocumentUri(context, uri)) {
                    DocumentsContract.getDocumentId(uri)
                } else {
                    DocumentsContract.getTreeDocumentId(uri)
                }
            } catch (e: Exception) {
                uri.lastPathSegment
            } ?: uriString

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
        } catch (e: Exception) {
            "Storage"
        }
    }

    /**
     * Returns the display title for a root folder.
     * If multiple configured folders share the same display name, disambiguates by appending the parent label:
     * e.g. "Music (alanwalker)" vs "Music (queen)".
     * Otherwise returns just the display name (e.g. "Music", "Podcasts").
     */
    fun getRootFolderTitle(context: Context, uriString: String): String {
        val folders = getFolders(context)
        val displayName = getDisplayName(context, uriString)
        val hasDuplicate = folders.count {
            getDisplayName(context, it).equals(displayName, ignoreCase = true)
        } > 1

        return if (hasDuplicate) {
            val parentLabel = getFolderParentLabel(context, uriString)
            if (parentLabel.isNotBlank()) {
                "$displayName ($parentLabel)"
            } else {
                displayName
            }
        } else {
            displayName
        }
    }

    /**
     * Finds the configured folder URI that this song belongs to.
     */
    fun findMatchingFolderUri(context: Context, song: Song): String? {
        val folders = getFolders(context)
        if (folders.isEmpty()) return null
        if (folders.size == 1) return folders[0]

        // First check if folderName already has disambiguation e.g. "Music (alanwalker)"
        for (uriStr in folders) {
            val title = getRootFolderTitle(context, uriStr)
            if (song.folderName.equals(title, ignoreCase = true) || song.folderName.startsWith("$title/", ignoreCase = true)) {
                return uriStr
            }
        }

        val decodedUri = try { Uri.decode(song.contentUri.toString()) } catch (e: Exception) { song.contentUri.toString() }
        val filePath = song.filePath.lowercase(Locale.ROOT)

        // Sort by path length descending to match most specific folder first
        val sortedFolders = folders.sortedByDescending { uriStr ->
            getDisplayPath(context, uriStr).length
        }

        for (uriStr in sortedFolders) {
            val docId = try {
                DocumentsContract.getTreeDocumentId(Uri.parse(uriStr))
            } catch (e: Exception) {
                null
            } ?: uriStr
            val pathPart = if (docId.contains(':')) docId.substringAfter(':').trim('/') else docId.trim('/')
            val pathLower = pathPart.lowercase(Locale.ROOT)

            if (pathLower.isNotEmpty()) {
                if (decodedUri.contains(pathLower, ignoreCase = true) ||
                    filePath.contains("/$pathLower", ignoreCase = true) ||
                    filePath.contains("$pathLower/", ignoreCase = true) ||
                    song.folderName.contains(pathLower, ignoreCase = true)
                ) {
                    return uriStr
                }
            }
        }

        return folders.firstOrNull()
    }

    /**
     * Returns the root folder title for a song, disambiguated if there are same-named roots.
     */
    fun getSongRootFolderTitle(context: Context, song: Song): String? {
        val matchingUri = findMatchingFolderUri(context, song) ?: return null
        return getRootFolderTitle(context, matchingUri)
    }

    /**
     * Resolves any subpath inside the matched configured folder.
     * e.g., if song is at "/alanwalker/music/Rock/track.mp3" and root is "/alanwalker/music", returns "Rock".
     */
    fun getSubPathWithinFolder(context: Context, folderUriString: String, song: Song): String? {
        val docId = try {
            DocumentsContract.getTreeDocumentId(Uri.parse(folderUriString))
        } catch (e: Exception) {
            null
        } ?: folderUriString
        val rootPathPart = if (docId.contains(':')) docId.substringAfter(':').trim('/') else docId.trim('/')
        val rootPathLower = rootPathPart.lowercase(Locale.ROOT)

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

        val decodedUri = try { Uri.decode(song.contentUri.toString()) } catch (e: Exception) { "" }
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

        if (song.folderName.isNotBlank()) {
            val raw = song.folderName.trim().trim('/')
            val rootTitle = getRootFolderTitle(context, folderUriString)
            if (raw.startsWith(rootTitle, ignoreCase = true)) {
                val sub = raw.removePrefix(rootTitle).trim('/')
                if (sub.isNotBlank()) return sub
            }
            val leafName = getDisplayName(context, folderUriString)
            if (raw.startsWith(leafName, ignoreCase = true)) {
                val sub = raw.removePrefix(leafName).trim('/')
                if (sub.isNotBlank()) return sub
            }
        }

        return null
    }
}

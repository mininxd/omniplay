package xyz.omniplay.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.DocumentsContract
import androidx.documentfile.provider.DocumentFile
import xyz.omniplay.R
import xyz.omniplay.ui.MainActivity

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
}

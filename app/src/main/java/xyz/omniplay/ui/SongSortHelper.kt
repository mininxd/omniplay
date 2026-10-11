package xyz.omniplay.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import xyz.omniplay.R
import xyz.omniplay.model.Song

enum class SortField {
    TITLE, DATE, ARTIST, ALBUM
}

data class SortOption(
    val label: String,
    val field: SortField,
    val ascending: Boolean
)

object SongSortHelper {

    fun sortSongs(
        context: Context,
        songs: List<Song>,
        field: SortField,
        ascending: Boolean
    ): List<Song> {
        val unknownArtistStr = context.getString(R.string.unknown_artist)
        val unknownAlbumStr = context.getString(R.string.unknown_album)

        val comparator = Comparator<Song> { a, b ->
            when (field) {
                SortField.TITLE -> {
                    val res = a.title.compareTo(b.title, ignoreCase = true)
                    if (ascending) res else -res
                }
                SortField.DATE -> {
                    val res = a.dateModified.compareTo(b.dateModified)
                    if (res != 0) {
                        if (ascending) res else -res
                    } else {
                        a.title.compareTo(b.title, ignoreCase = true)
                    }
                }
                SortField.ARTIST -> {
                    val aArtist = a.artist.trim().ifEmpty { unknownArtistStr }
                    val bArtist = b.artist.trim().ifEmpty { unknownArtistStr }
                    val res = aArtist.compareTo(bArtist, ignoreCase = true)
                    if (res != 0) {
                        if (ascending) res else -res
                    } else {
                        a.title.compareTo(b.title, ignoreCase = true)
                    }
                }
                SortField.ALBUM -> {
                    val aAlbum = a.album.trim().ifEmpty { unknownAlbumStr }
                    val bAlbum = b.album.trim().ifEmpty { unknownAlbumStr }
                    val res = aAlbum.compareTo(bAlbum, ignoreCase = true)
                    if (res != 0) {
                        if (ascending) res else -res
                    } else {
                        a.title.compareTo(b.title, ignoreCase = true)
                    }
                }
            }
        }
        return songs.sortedWith(comparator)
    }

    fun showSortDialog(
        activity: AppCompatActivity,
        currentFilterMode: LibraryFilterMode,
        currentSortField: SortField,
        isSortAscending: Boolean,
        onSortSelected: (SortField, Boolean) -> Unit
    ) {
        val options = mutableListOf<SortOption>()
        options.add(SortOption("Title (A → Z)", SortField.TITLE, true))
        options.add(SortOption("Title (Z → A)", SortField.TITLE, false))
        options.add(SortOption("Date Added (Newest)", SortField.DATE, false))
        options.add(SortOption("Date Added (Oldest)", SortField.DATE, true))

        when (currentFilterMode) {
            LibraryFilterMode.TRACK, LibraryFilterMode.FOLDER -> {
                options.add(SortOption("Artist (A → Z)", SortField.ARTIST, true))
                options.add(SortOption("Artist (Z → A)", SortField.ARTIST, false))
                options.add(SortOption("Album (A → Z)", SortField.ALBUM, true))
                options.add(SortOption("Album (Z → A)", SortField.ALBUM, false))
            }
            LibraryFilterMode.ARTIST -> {
                options.add(SortOption("Album (A → Z)", SortField.ALBUM, true))
                options.add(SortOption("Album (Z → A)", SortField.ALBUM, false))
            }
            LibraryFilterMode.ALBUM -> {
                options.add(SortOption("Artist (A → Z)", SortField.ARTIST, true))
                options.add(SortOption("Artist (Z → A)", SortField.ARTIST, false))
            }
        }

        val checkedIndex = options.indexOfFirst { it.field == currentSortField && it.ascending == isSortAscending }
            .let { if (it >= 0) it else 0 }

        val labels = options.map { it.label }.toTypedArray()

        MaterialAlertDialogBuilder(activity)
            .setTitle(R.string.sort_queue)
            .setSingleChoiceItems(labels, checkedIndex) { dialog, which ->
                val selected = options[which]
                onSortSelected(selected.field, selected.ascending)
                dialog.dismiss()
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }
}

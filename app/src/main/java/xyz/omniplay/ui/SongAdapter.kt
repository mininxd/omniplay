package xyz.omniplay.ui

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.omniplay.R
import xyz.omniplay.databinding.ItemSongBinding
import xyz.omniplay.model.Song
import xyz.omniplay.util.AlbumArtLoader
import xyz.omniplay.util.AlbumColorGenerator

class SongAdapter(
    private var showAlbumArt: Boolean = true,
    private val onSongClicked: (Song, Int) -> Unit
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    private var songs: List<Song> = emptyList()
    private var currentPlayingSongId: Long = -1L
    private val adapterScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    fun setSongs(newSongs: List<Song>) {
        songs = newSongs
        notifyDataSetChanged()
    }

    fun setShowAlbumArt(show: Boolean) {
        if (showAlbumArt != show) {
            showAlbumArt = show
            notifyDataSetChanged()
        }
    }

    fun getSongs(): List<Song> = songs

    fun setCurrentPlayingSongId(id: Long) {
        val oldId = currentPlayingSongId
        currentPlayingSongId = id
        val oldIndex = songs.indexOfFirst { it.id == oldId }
        val newIndex = songs.indexOfFirst { it.id == id }
        if (oldIndex != -1) notifyItemChanged(oldIndex)
        if (newIndex != -1) notifyItemChanged(newIndex)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = ItemSongBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.bind(song, song.id == currentPlayingSongId)
    }

    override fun getItemCount(): Int = songs.size

    inner class SongViewHolder(private val binding: ItemSongBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var loadJob: Job? = null

        fun bind(song: Song, isPlaying: Boolean) {
            loadJob?.cancel()
            val context = binding.root.context
            val pad = (11 * context.resources.displayMetrics.density).toInt()
            val albumColor = AlbumColorGenerator.getColorForAlbum(song.album, song.artist)

            binding.itemTitleText.text = song.title
            binding.itemSubtitleText.text = "${song.artist} • ${song.format}"
            binding.itemDurationText.text = Song.formatTime(song.duration)

            if (isPlaying) {
                binding.itemTitleText.setTextColor(ContextCompat.getColor(context, R.color.primary_accent))
                binding.thumbnailCard.strokeWidth = (2 * context.resources.displayMetrics.density).toInt()
                binding.thumbnailCard.strokeColor = ContextCompat.getColor(context, R.color.primary_accent)
            } else {
                binding.itemTitleText.setTextColor(ContextCompat.getColor(context, R.color.text_primary))
                binding.thumbnailCard.strokeWidth = 0
            }

            fun showMusicNoteFallback() {
                binding.thumbnailCard.setCardBackgroundColor(albumColor)
                binding.itemThumbnailImage.setImageDrawable(null)
                binding.itemThumbnailImage.scaleType = ImageView.ScaleType.CENTER_INSIDE
                binding.itemThumbnailImage.setPadding(pad, pad, pad, pad)
                binding.itemThumbnailImage.setImageResource(R.drawable.ic_music_note)
                binding.itemThumbnailImage.imageTintList = ColorStateList.valueOf(Color.WHITE)
            }

            fun showAlbumArt(bitmap: Bitmap) {
                binding.thumbnailCard.setCardBackgroundColor(Color.TRANSPARENT)
                binding.itemThumbnailImage.imageTintList = null
                binding.itemThumbnailImage.clearColorFilter()
                binding.itemThumbnailImage.setPadding(0, 0, 0, 0)
                binding.itemThumbnailImage.scaleType = ImageView.ScaleType.CENTER_CROP
                binding.itemThumbnailImage.setImageBitmap(bitmap)
            }

            if (!showAlbumArt) {
                showMusicNoteFallback()
            } else {
                val cachedBitmap = AlbumArtLoader.getCachedAlbumArt(song.id)
                if (cachedBitmap != null) {
                    showAlbumArt(cachedBitmap)
                } else {
                    showMusicNoteFallback()
                    val songId = song.id
                    binding.itemThumbnailImage.tag = songId

                    loadJob = adapterScope.launch {
                        val bitmap = AlbumArtLoader.loadAlbumArt(context, song)
                        if (binding.itemThumbnailImage.tag == songId) {
                            withContext(Dispatchers.Main) {
                                if (bitmap != null) {
                                    showAlbumArt(bitmap)
                                } else {
                                    showMusicNoteFallback()
                                }
                            }
                        }
                    }
                }
            }

            binding.root.setOnClickListener {
                onSongClicked(song, bindingAdapterPosition)
            }
        }
    }
}

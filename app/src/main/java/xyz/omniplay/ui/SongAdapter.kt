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

    override fun onViewRecycled(holder: SongViewHolder) {
        super.onViewRecycled(holder)
        holder.cancelLoading()
    }

    inner class SongViewHolder(private val binding: ItemSongBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var loadJob: Job? = null

        fun cancelLoading() {
            loadJob?.cancel()
            loadJob = null
            binding.itemThumbnailImage.tag = null
        }

        fun bind(song: Song, isPlaying: Boolean) {
            cancelLoading()
            val context = binding.root.context

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

            fun showDefaultAlbumArt() {
                binding.thumbnailCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.surface_container_high))
                binding.itemThumbnailImage.imageTintList = null
                binding.itemThumbnailImage.clearColorFilter()
                binding.itemThumbnailImage.setPadding(0, 0, 0, 0)
                binding.itemThumbnailImage.scaleType = ImageView.ScaleType.CENTER_CROP
                binding.itemThumbnailImage.setImageResource(R.drawable.default_album_art)
            }

            fun showAlbumArt(bitmap: Bitmap) {
                binding.thumbnailCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.surface_container_high))
                binding.itemThumbnailImage.imageTintList = null
                binding.itemThumbnailImage.clearColorFilter()
                binding.itemThumbnailImage.setPadding(0, 0, 0, 0)
                binding.itemThumbnailImage.scaleType = ImageView.ScaleType.CENTER_CROP
                binding.itemThumbnailImage.setImageBitmap(bitmap)
            }

            if (!showAlbumArt) {
                showDefaultAlbumArt()
            } else {
                val cachedBitmap = AlbumArtLoader.getCachedAlbumArt(song)
                if (cachedBitmap != null) {
                    showAlbumArt(cachedBitmap)
                } else {
                    showDefaultAlbumArt()
                    val imageKey = AlbumArtLoader.getCacheKey(song)
                    binding.itemThumbnailImage.tag = imageKey

                    loadJob = adapterScope.launch {
                        val bitmap = AlbumArtLoader.loadAlbumArt(context, song)
                        withContext(Dispatchers.Main) {
                            if (binding.itemThumbnailImage.tag == imageKey) {
                                if (bitmap != null) {
                                    showAlbumArt(bitmap)
                                } else {
                                    showDefaultAlbumArt()
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

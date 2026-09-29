package xyz.omniplay.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.omniplay.R
import xyz.omniplay.databinding.ItemSongBinding
import xyz.omniplay.model.Song
import xyz.omniplay.util.AlbumArtLoader

class SongAdapter(
    private val onSongClicked: (Song, Int) -> Unit
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    private var songs: List<Song> = emptyList()
    private var currentPlayingSongId: Long = -1L
    private val adapterScope = CoroutineScope(Dispatchers.Main + Job())

    fun setSongs(newSongs: List<Song>) {
        songs = newSongs
        notifyDataSetChanged()
    }

    fun setCurrentPlayingSongId(id: Long) {
        currentPlayingSongId = id
        notifyDataSetChanged()
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
            val pad = (10 * context.resources.displayMetrics.density).toInt()

            binding.itemTitleText.text = song.title
            binding.itemSubtitleText.text = "${song.artist} • ${song.format}"
            binding.itemDurationText.text = Song.formatTime(song.duration)

            // Setup default music note icon with proper padding, scaleType and colors
            binding.itemThumbnailImage.setImageDrawable(null)
            binding.itemThumbnailImage.scaleType = ImageView.ScaleType.CENTER_INSIDE
            binding.itemThumbnailImage.setPadding(pad, pad, pad, pad)
            binding.itemThumbnailImage.setImageResource(R.drawable.ic_music_note)

            if (isPlaying) {
                binding.itemTitleText.setTextColor(ContextCompat.getColor(context, R.color.primary_accent))
                binding.thumbnailCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.primary_accent))
                binding.itemThumbnailImage.setColorFilter(ContextCompat.getColor(context, R.color.on_primary))
            } else {
                binding.itemTitleText.setTextColor(ContextCompat.getColor(context, R.color.text_primary))
                binding.thumbnailCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.surface_container_high))
                binding.itemThumbnailImage.setColorFilter(ContextCompat.getColor(context, R.color.primary_accent))
            }

            val songId = song.id
            binding.itemThumbnailImage.tag = songId

            loadJob = adapterScope.launch {
                val bitmap = AlbumArtLoader.loadAlbumArt(context, song)
                if (bitmap != null && binding.itemThumbnailImage.tag == songId) {
                    withContext(Dispatchers.Main) {
                        binding.itemThumbnailImage.clearColorFilter()
                        binding.itemThumbnailImage.setPadding(0, 0, 0, 0)
                        binding.itemThumbnailImage.scaleType = ImageView.ScaleType.CENTER_CROP
                        binding.itemThumbnailImage.setImageBitmap(bitmap)
                    }
                }
            }

            binding.root.setOnClickListener {
                onSongClicked(song, bindingAdapterPosition)
            }
        }
    }
}

package xyz.omniplay.ui

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import xyz.omniplay.R
import xyz.omniplay.databinding.ItemSongBinding
import xyz.omniplay.model.Song

class SongAdapter(
    private val onSongClicked: (Song, Int) -> Unit
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    private var allSongs: List<Song> = emptyList()
    private var displayedSongs: List<Song> = emptyList()
    private var currentPlayingSongId: Long = -1L

    fun setSongs(songs: List<Song>) {
        allSongs = songs
        displayedSongs = songs
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        displayedSongs = if (query.isBlank()) {
            allSongs
        } else {
            val q = query.trim().lowercase()
            allSongs.filter {
                it.title.lowercase().contains(q) ||
                        it.artist.lowercase().contains(q) ||
                        it.album.lowercase().contains(q) ||
                        it.format.lowercase().contains(q)
            }
        }
        notifyDataSetChanged()
    }

    fun setCurrentPlayingSongId(id: Long) {
        val prevId = currentPlayingSongId
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
        val song = displayedSongs[position]
        holder.bind(song, song.id == currentPlayingSongId)
    }

    override fun getItemCount(): Int = displayedSongs.size

    inner class SongViewHolder(private val binding: ItemSongBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(song: Song, isPlaying: Boolean) {
            binding.itemTitleText.text = song.title
            binding.itemSubtitleText.text = "${song.artist} • ${song.format}"
            binding.itemDurationText.text = Song.formatTime(song.duration)

            val context = binding.root.context

            if (isPlaying) {
                binding.itemTitleText.setTextColor(ContextCompat.getColor(context, R.color.primary_accent))
                binding.thumbnailCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.primary_accent))
                binding.itemThumbnailImage.setColorFilter(ContextCompat.getColor(context, R.color.on_primary))
            } else {
                binding.itemTitleText.setTextColor(ContextCompat.getColor(context, R.color.text_primary))
                binding.thumbnailCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.surface_container))
                binding.itemThumbnailImage.setColorFilter(ContextCompat.getColor(context, R.color.control_tint))
            }

            // Load album art thumbnail if present
            if (song.albumArtUri != null) {
                try {
                    val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, song.albumArtUri))
                    } else {
                        @Suppress("DEPRECATION")
                        MediaStore.Images.Media.getBitmap(context.contentResolver, song.albumArtUri)
                    }
                    binding.itemThumbnailImage.clearColorFilter()
                    binding.itemThumbnailImage.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    binding.itemThumbnailImage.setImageResource(R.drawable.ic_music_note)
                }
            } else {
                binding.itemThumbnailImage.setImageResource(R.drawable.ic_music_note)
            }

            binding.root.setOnClickListener {
                val originalIndex = allSongs.indexOf(song)
                onSongClicked(song, if (originalIndex >= 0) originalIndex else bindingAdapterPosition)
            }
        }
    }
}

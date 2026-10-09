package xyz.omniplay.ui

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.color.MaterialColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.omniplay.R
import xyz.omniplay.databinding.ItemDrawerFilterBinding
import xyz.omniplay.model.Song
import xyz.omniplay.util.AlbumArtLoader
import xyz.omniplay.util.ThemeColors
import java.util.Locale

data class FilterItem(
    val title: String,
    val count: Int,
    val isAlbum: Boolean,
    val isSelected: Boolean = false,
    val representativeSong: Song? = null,
    val isFolder: Boolean = false
)

class DrawerFilterAdapter(
    private val onItemClick: (FilterItem) -> Unit
) : RecyclerView.Adapter<DrawerFilterAdapter.ViewHolder>() {

    private var items: List<FilterItem> = emptyList()
    private val adapterScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    fun setItems(newItems: List<FilterItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDrawerFilterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cancelPendingLoad()
        val item = items[position]
        holder.bind(item)
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.cancelPendingLoad()
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemDrawerFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var loadJob: Job? = null

        fun cancelPendingLoad() {
            loadJob?.cancel()
            loadJob = null
        }

        fun bind(item: FilterItem) {
            val context = binding.root.context
            val displayTitle = item.title.trim().ifEmpty {
                when {
                    item.isFolder -> "Folder"
                    item.isAlbum -> context.getString(R.string.unknown_album)
                    else -> context.getString(R.string.unknown_artist)
                }
            }
            binding.filterItemTitle.text = displayTitle
            binding.filterItemCount.text = "${item.count}"

            fun showAlbumArt(bitmap: Bitmap) {
                binding.filterItemIcon.imageTintList = null
                binding.filterItemIcon.clearColorFilter()
                binding.filterItemIcon.setPadding(0, 0, 0, 0)
                binding.filterItemIcon.scaleType = ImageView.ScaleType.CENTER_CROP
                binding.filterItemIcon.setImageBitmap(bitmap)
                binding.filterItemIcon.alpha = 1.0f
            }

            fun showDefaultIcon() {
                binding.filterItemIcon.imageTintList = null
                binding.filterItemIcon.scaleType = ImageView.ScaleType.CENTER_INSIDE
                val pad = (8 * context.resources.displayMetrics.density).toInt()
                binding.filterItemIcon.setPadding(pad, pad, pad, pad)
                binding.filterItemIcon.setImageResource(
                    when {
                        item.isFolder -> R.drawable.ic_folder
                        item.isAlbum -> R.drawable.ic_album
                        else -> R.drawable.ic_person
                    }
                )
                val tintColor = if (item.isSelected) {
                    ThemeColors.getPrimary(context)
                } else {
                    ThemeColors.getOnSurfaceVariant(context)
                }
                binding.filterItemIcon.setColorFilter(tintColor)
                binding.filterItemIcon.alpha = if (item.isSelected) 1.0f else 0.7f
            }

            val cachedArt = if (item.isAlbum && !item.isFolder) {
                item.representativeSong?.let { AlbumArtLoader.getCachedAlbumArt(it) }
                    ?: AlbumArtLoader.getAlbumArt(item.title)
            } else if (!item.isAlbum && !item.isFolder) {
                AlbumArtLoader.getCachedArtistArt(item.title)
                    ?: (item.representativeSong?.let { AlbumArtLoader.getCachedAlbumArt(it) })
            } else null

            if (cachedArt != null) {
                showAlbumArt(cachedArt)
            } else {
                showDefaultIcon()
                if (item.isAlbum && item.representativeSong != null) {
                    val repSong = item.representativeSong
                    val imageKey = AlbumArtLoader.getCacheKey(repSong)
                    binding.filterItemIcon.tag = imageKey

                    loadJob = adapterScope.launch {
                        val bitmap = AlbumArtLoader.loadAlbumArt(context, repSong)
                        withContext(Dispatchers.Main) {
                            if (binding.filterItemIcon.tag == imageKey && bitmap != null) {
                                showAlbumArt(bitmap)
                            }
                        }
                    }
                } else if (!item.isAlbum && !item.isFolder && item.title.isNotBlank()) {
                    val artistKey = "artist_${item.title.lowercase(Locale.ROOT)}"
                    binding.filterItemIcon.tag = artistKey

                    loadJob = adapterScope.launch {
                        val bitmap = AlbumArtLoader.loadArtistArt(context, item.title, item.representativeSong)
                        withContext(Dispatchers.Main) {
                            if (binding.filterItemIcon.tag == artistKey && bitmap != null) {
                                showAlbumArt(bitmap)
                            }
                        }
                    }
                }
            }

            val primaryColor = ThemeColors.getPrimary(context)
            if (item.isSelected) {
                binding.filterItemTitle.setTextColor(primaryColor)
                binding.filterItemCount.setTextColor(primaryColor)
                binding.filterItemCard.strokeWidth = (1.5f * context.resources.displayMetrics.density).toInt()
                binding.filterItemCard.strokeColor = primaryColor
            } else {
                binding.filterItemTitle.setTextColor(ThemeColors.getOnSurface(context))
                binding.filterItemCount.setTextColor(ThemeColors.getOutline(context))
                binding.filterItemCard.strokeWidth = 0
            }

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }
}

package xyz.omniplay.ui

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
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
    val count: Int = 0,
    val isAlbum: Boolean = false,
    val isSelected: Boolean = false,
    val representativeSong: Song? = null,
    val isFolder: Boolean = false,
    val isBack: Boolean = false,
    val song: Song? = null,
    val subtitle: String? = null,
    val folderPath: String? = null
)

class DrawerFilterAdapter(
    private val onItemClick: (FilterItem) -> Unit,
    private val onStartDragListener: ((RecyclerView.ViewHolder) -> Unit)? = null,
    private val onItemLongClick: ((FilterItem, View) -> Boolean)? = null
) : RecyclerView.Adapter<DrawerFilterAdapter.ViewHolder>() {

    private var items: List<FilterItem> = emptyList()
    private val adapterScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    fun setItems(newItems: List<FilterItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    fun getItems(): List<FilterItem> = items

    fun getItemAt(position: Int): FilterItem? = items.getOrNull(position)

    fun moveItem(fromPosition: Int, toPosition: Int) {
        if (fromPosition in items.indices && toPosition in items.indices) {
            val mutable = items.toMutableList()
            val item = mutable.removeAt(fromPosition)
            mutable.add(toPosition, item)
            items = mutable
            notifyItemMoved(fromPosition, toPosition)
        }
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
            val displayTitle = if (item.isBack) {
                ".."
            } else if (item.song != null) {
                item.title
            } else {
                item.title.trim().ifEmpty {
                    when {
                        item.isFolder -> "Folder"
                        item.isAlbum -> context.getString(R.string.unknown_album)
                        else -> context.getString(R.string.unknown_artist)
                    }
                }
            }
            binding.filterItemTitle.text = displayTitle

            if (!item.subtitle.isNullOrBlank()) {
                binding.filterItemSubtitle.visibility = android.view.View.VISIBLE
                binding.filterItemSubtitle.text = item.subtitle
            } else {
                binding.filterItemSubtitle.visibility = android.view.View.GONE
            }

            if (item.song != null) {
                binding.filterDragHandle.visibility = View.VISIBLE
                binding.filterDragHandle.setOnTouchListener { _, event ->
                    if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                        onStartDragListener?.invoke(this)
                    }
                    false
                }
            } else {
                binding.filterDragHandle.visibility = View.GONE
                binding.filterDragHandle.setOnTouchListener(null)
            }

            if (item.isBack) {
                binding.filterItemCount.visibility = android.view.View.GONE
            } else if (item.song != null) {
                binding.filterItemCount.visibility = android.view.View.VISIBLE
                binding.filterItemCount.text = Song.formatTime(item.song.duration)
            } else {
                binding.filterItemCount.visibility = android.view.View.VISIBLE
                binding.filterItemCount.text = "${item.count}"
            }

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
                val pad = (6 * context.resources.displayMetrics.density).toInt()
                binding.filterItemIcon.setPadding(pad, pad, pad, pad)
                binding.filterItemIcon.setImageResource(
                    when {
                        item.isBack -> R.drawable.ic_arrow_back
                        item.isFolder -> R.drawable.ic_folder
                        item.song != null -> R.drawable.ic_music_note
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

            val cachedArt = when {
                item.isBack -> null
                item.song != null -> AlbumArtLoader.getCachedAlbumArt(item.song)
                item.isFolder -> null
                item.isAlbum -> item.representativeSong?.let { AlbumArtLoader.getCachedAlbumArt(it) }
                    ?: AlbumArtLoader.getAlbumArt(item.title)
                else -> AlbumArtLoader.getCachedArtistArt(item.title)
                    ?: (item.representativeSong?.let { AlbumArtLoader.getCachedAlbumArt(it) })
            }

            if (cachedArt != null) {
                showAlbumArt(cachedArt)
            } else {
                showDefaultIcon()
                if (item.song != null) {
                    val s = item.song
                    val imageKey = AlbumArtLoader.getCacheKey(s)
                    binding.filterItemIcon.tag = imageKey

                    loadJob = adapterScope.launch {
                        val bitmap = AlbumArtLoader.loadAlbumArt(context, s)
                        withContext(Dispatchers.Main) {
                            if (binding.filterItemIcon.tag == imageKey && bitmap != null) {
                                showAlbumArt(bitmap)
                            }
                        }
                    }
                } else if (item.isAlbum && item.representativeSong != null) {
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
                } else if (!item.isAlbum && !item.isFolder && !item.isBack && item.title.isNotBlank()) {
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

                val bgAlphaColor = androidx.core.graphics.ColorUtils.setAlphaComponent(primaryColor, (255 * 0.10f).toInt())
                val shape = android.graphics.drawable.GradientDrawable().apply {
                    setColor(bgAlphaColor)
                    cornerRadius = 14 * context.resources.displayMetrics.density
                }
                binding.root.background = shape
            } else {
                binding.filterItemTitle.setTextColor(ThemeColors.getOnSurface(context))
                binding.filterItemCount.setTextColor(ThemeColors.getOutline(context))
                binding.filterItemCard.strokeWidth = 0

                val typedValue = android.util.TypedValue()
                context.theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)
                binding.root.setBackgroundResource(typedValue.resourceId)
            }

            binding.root.setOnClickListener {
                onItemClick(item)
            }

            if (item.song != null) {
                binding.root.setOnLongClickListener {
                    onItemLongClick?.invoke(item, binding.root) ?: false
                }
            } else {
                binding.root.setOnLongClickListener(null)
            }
        }
    }
}

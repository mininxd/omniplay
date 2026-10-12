package xyz.omniplay.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.util.TypedValue
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.color.MaterialColors
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
import xyz.omniplay.util.ThemeColors

class SongAdapter(
    private var showAlbumArt: Boolean = true,
    private val onSongClicked: (Song, Int) -> Unit,
    private val onStartDragListener: ((RecyclerView.ViewHolder) -> Unit)? = null,
    private val onSongLongClicked: ((Song, View) -> Boolean)? = null
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    private var songs: List<Song> = emptyList()
    private var currentPlayingSongId: Long = -1L
    private val adapterScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var pendingPulseSongId: Long? = null

    fun pulseHighlightSong(songId: Long, recyclerView: RecyclerView) {
        val pos = songs.indexOfFirst { it.id == songId }
        if (pos == -1) return
        pendingPulseSongId = songId

        val lm = recyclerView.layoutManager as? LinearLayoutManager
        val itemHeight = (68 * recyclerView.context.resources.displayMetrics.density).toInt()
        val offset = (recyclerView.height / 2) - (itemHeight / 2)
        lm?.scrollToPositionWithOffset(pos, offset.coerceAtLeast(0))

        recyclerView.postDelayed({
            val holder = recyclerView.findViewHolderForAdapterPosition(pos) as? SongViewHolder
            if (holder != null) {
                val s = songs.getOrNull(pos) ?: return@postDelayed
                holder.startPulse(s, s.id == currentPlayingSongId)
                pendingPulseSongId = null
            } else {
                notifyItemChanged(pos)
            }
        }, 150L)
    }

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

    fun moveItem(fromPosition: Int, toPosition: Int) {
        if (fromPosition in songs.indices && toPosition in songs.indices) {
            val mutable = songs.toMutableList()
            val item = mutable.removeAt(fromPosition)
            mutable.add(toPosition, item)
            songs = mutable
            notifyItemMoved(fromPosition, toPosition)
        }
    }

    fun removeItem(position: Int): Song? {
        if (position in songs.indices) {
            val mutable = songs.toMutableList()
            val item = mutable.removeAt(position)
            songs = mutable
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, songs.size - position)
            return item
        }
        return null
    }

    fun setCurrentPlayingSongId(id: Long) {
        val oldId = currentPlayingSongId
        currentPlayingSongId = id
        val oldIndex = songs.indexOfFirst { it.id == oldId }
        val newIndex = songs.indexOfFirst { it.id == id }
        if (oldIndex != -1) notifyItemChanged(oldIndex)
        if (newIndex != -1) notifyItemChanged(newIndex)
    }

    override fun getItemCount(): Int = songs.size

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
        if (pendingPulseSongId != null && song.id == pendingPulseSongId) {
            pendingPulseSongId = null
            holder.itemView.post {
                holder.startPulse(song, song.id == currentPlayingSongId)
            }
        }
    }

    override fun onViewRecycled(holder: SongViewHolder) {
        super.onViewRecycled(holder)
        holder.cancelLoading()
        holder.cancelPulse()
    }

    inner class SongViewHolder(private val binding: ItemSongBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var loadJob: Job? = null
        private var pulseAnimator: Animator? = null

        fun cancelPulse() {
            pulseAnimator?.cancel()
            pulseAnimator = null
        }

        fun startPulse(song: Song, isPlaying: Boolean) {
            cancelPulse()
            val context = binding.root.context
            val primaryColor = ThemeColors.getPrimary(context)

            try {
                binding.root.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
            } catch (ignored: Exception) {}

            val colorAnim = ValueAnimator.ofFloat(0f, 1f, 0.25f, 0.85f, 0f).apply {
                duration = 1100L
                interpolator = AccelerateDecelerateInterpolator()
                addUpdateListener { anim ->
                    val fraction = anim.animatedValue as Float
                    val alphaColor = ColorUtils.setAlphaComponent(primaryColor, (255 * 0.25f * fraction).toInt())
                    val strokeColor = ColorUtils.setAlphaComponent(primaryColor, (255 * fraction).toInt())
                    val shape = GradientDrawable().apply {
                        setColor(alphaColor)
                        cornerRadius = 14 * context.resources.displayMetrics.density
                        setStroke((2 * context.resources.displayMetrics.density).toInt(), strokeColor)
                    }
                    binding.root.background = shape
                }
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        if (isPlaying) {
                            val bgAlphaColor = ColorUtils.setAlphaComponent(primaryColor, (255 * 0.10f).toInt())
                            val shape = GradientDrawable().apply {
                                setColor(bgAlphaColor)
                                cornerRadius = 14 * context.resources.displayMetrics.density
                            }
                            val rippleColor = ColorStateList.valueOf(ThemeColors.getColorControlHighlight(context))
                            binding.root.background = RippleDrawable(rippleColor, shape, null)
                        } else {
                            val typedValue = TypedValue()
                            context.theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)
                            binding.root.setBackgroundResource(typedValue.resourceId)
                        }
                    }
                })
            }

            pulseAnimator = colorAnim
            colorAnim.start()
        }

        fun cancelLoading() {
            loadJob?.cancel()
            loadJob = null
            binding.itemThumbnailImage.tag = null
        }

        fun bind(song: Song, isPlaying: Boolean) {
            cancelLoading()
            cancelPulse()
            val context = binding.root.context

            binding.itemTitleText.text = song.title
            val displayArtist = song.artist.trim().ifEmpty { context.getString(R.string.unknown_artist) }
            binding.itemSubtitleText.text = "$displayArtist • ${song.format}"
            binding.itemDurationText.text = Song.formatTime(song.duration)

            val primaryColor = ThemeColors.getPrimary(context)
            val onSurfaceColor = ThemeColors.getOnSurface(context)

            if (isPlaying) {
                binding.itemTitleText.setTextColor(primaryColor)
                binding.thumbnailCard.strokeWidth = (2 * context.resources.displayMetrics.density).toInt()
                binding.thumbnailCard.strokeColor = primaryColor

                val bgAlphaColor = ColorUtils.setAlphaComponent(primaryColor, (255 * 0.10f).toInt())
                val shape = GradientDrawable().apply {
                    setColor(bgAlphaColor)
                    cornerRadius = 14 * context.resources.displayMetrics.density
                }
                val rippleColor = ColorStateList.valueOf(ThemeColors.getColorControlHighlight(context))
                binding.songItemRoot.background = RippleDrawable(rippleColor, shape, shape)
            } else {
                binding.itemTitleText.setTextColor(onSurfaceColor)
                binding.thumbnailCard.strokeWidth = 0

                val typedValue = TypedValue()
                context.theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)
                binding.songItemRoot.setBackgroundResource(typedValue.resourceId)
            }

            fun showDefaultAlbumArt() {
                val surfaceContainerHigh = ThemeColors.getSurfaceContainerHigh(context)
                binding.thumbnailCard.setCardBackgroundColor(surfaceContainerHigh)
                binding.itemThumbnailImage.imageTintList = null
                binding.itemThumbnailImage.clearColorFilter()
                binding.itemThumbnailImage.setPadding(0, 0, 0, 0)
                binding.itemThumbnailImage.scaleType = ImageView.ScaleType.CENTER_CROP
                binding.itemThumbnailImage.setImageResource(R.drawable.default_album_art)
            }

            fun showAlbumArt(bitmap: Bitmap) {
                binding.thumbnailCard.setCardBackgroundColor(ThemeColors.getSurfaceContainerHigh(context))
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

            binding.dragHandle.setOnTouchListener { _, event ->
                if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                    onStartDragListener?.invoke(this)
                }
                false
            }

            binding.root.setOnClickListener {
                onSongClicked(song, bindingAdapterPosition)
            }

            binding.root.setOnLongClickListener {
                onSongLongClicked?.invoke(song, binding.root) ?: false
            }
        }
    }
}

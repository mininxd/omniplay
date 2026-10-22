package xyz.omniplay.ui

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.Gravity
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import xyz.omniplay.R
import xyz.omniplay.lyrics.LyricLine
import xyz.omniplay.util.ThemeColors

import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.FrameLayout

class LyricAdapter(
    private var lines: List<LyricLine> = emptyList(),
    val isFullScreen: Boolean = false,
    var onLineClick: ((LyricLine) -> Unit)? = null,
    var onLineDoubleTap: ((View?) -> Unit)? = null,
    var onLineSelectionStart: ((Int) -> Unit)? = null,
    var onSelectionLineClick: ((Int) -> Unit)? = null,
    var onSelectionLineDragStart: ((Int) -> Unit)? = null,
    var onSelectionLineDrag: ((Int) -> Unit)? = null
) : RecyclerView.Adapter<LyricAdapter.LyricViewHolder>() {

    var activeIndex: Int = -1
        private set

    /** Base font size in SP for fullscreen active line (default 48sp, adjustable via pinch). */
    var baseFontSizeSp: Float = 48f

    private var shareSelectionMode = false
    private val shareSelectionLines = mutableSetOf<Int>()
    private var shareSelectionFontScale = 1f

    companion object {
        private var cachedBlackTypeface: Typeface? = null
        private var cachedMediumTypeface: Typeface? = null

        // Letter spacing per the design guide (-2%)
        private const val LETTER_SPACING = -0.02f

        fun getBlackTypeface(ctx: Context): Typeface {
            if (cachedBlackTypeface == null) {
                cachedBlackTypeface = try {
                    ResourcesCompat.getFont(ctx, R.font.inter_black)
                } catch (e: Exception) {
                    Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
                }
            }
            return cachedBlackTypeface ?: Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
        }

        fun getMediumTypeface(ctx: Context): Typeface {
            if (cachedMediumTypeface == null) {
                cachedMediumTypeface = try {
                    ResourcesCompat.getFont(ctx, R.font.inter_medium)
                } catch (e: Exception) {
                    Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
                }
            }
            return cachedMediumTypeface ?: Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
        }
    }

    private fun dpToPx(ctx: Context, dp: Int): Int =
        (dp * ctx.resources.displayMetrics.density).toInt()

    fun submitLines(newLines: List<LyricLine>) {
        if (newLines == lines) return
        lines = newLines
        activeIndex = -1
        notifyDataSetChanged()
    }

    fun setActiveIndex(newIndex: Int): Boolean {
        if (newIndex == activeIndex) return false
        val oldIndex = activeIndex
        activeIndex = newIndex
        if (oldIndex in lines.indices) {
            notifyItemChanged(oldIndex)
        }
        if (newIndex in lines.indices) {
            notifyItemChanged(newIndex)
        }
        return true
    }

    fun setShareSelectionMode(enabled: Boolean, selectedLines: Set<Int> = emptySet()) {
        shareSelectionMode = enabled
        shareSelectionLines.clear()
        if (enabled) shareSelectionLines.addAll(selectedLines.filter { it in lines.indices })
        shareSelectionFontScale = 1f
        notifyDataSetChanged()
    }

    fun setShareSelectionLines(selectedLines: Set<Int>) {
        if (!shareSelectionMode) return
        val changedPositions = (shareSelectionLines union selectedLines)
            .filter { (it in shareSelectionLines) != (it in selectedLines) && it in lines.indices }
        if (changedPositions.isEmpty()) return
        shareSelectionLines.clear()
        shareSelectionLines.addAll(selectedLines.filter { it in lines.indices })
        changedPositions.forEach(::notifyItemChanged)
    }

    fun setShareSelectionFontScale(scale: Float) {
        shareSelectionFontScale = scale
    }

    fun updateSelectionTextSize(holder: LyricViewHolder) {
        val position = holder.bindingAdapterPosition
        if (!shareSelectionMode || position !in lines.indices) return
        holder.updateSelectionTextSize(shareSelectionFontScale)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LyricViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lyric_line, parent, false)
        return LyricViewHolder(view)
    }

    override fun onBindViewHolder(holder: LyricViewHolder, position: Int) {
        val selected = shareSelectionMode && position in shareSelectionLines
        holder.bind(
            lines[position],
            position == activeIndex,
            shareSelectionMode,
            selected
        )
    }

    override fun getItemCount(): Int = lines.size

    inner class LyricViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val lyricText: TextView = itemView.findViewById(R.id.lyric_line_text)
        private val normalBackground = itemView.background
        private val normalTextBackground = lyricText.background
        private val gestureDetector: GestureDetector

        init {
            gestureDetector = GestureDetector(itemView.context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    val pos = bindingAdapterPosition
                    if (pos !in lines.indices || lines[pos].text.isBlank()) return super.onSingleTapUp(e)
                    if (shareSelectionMode) onSelectionLineClick?.invoke(pos)
                    else onLineSelectionStart?.invoke(pos)
                    return true
                }

                override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                    val pos = bindingAdapterPosition
                    if (pos in lines.indices) {
                        if (!shareSelectionMode && pos != activeIndex) {
                            onLineClick?.invoke(lines[pos])
                        }
                    }
                    return true
                }

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    onLineDoubleTap?.invoke(lyricText)
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    val pos = bindingAdapterPosition
                    if (pos in lines.indices && lines[pos].text.isNotBlank()) {
                        itemView.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
                        onLineSelectionStart?.invoke(pos)
                        itemView.parent?.requestDisallowInterceptTouchEvent(true)
                    }
                }
            })
            
            itemView.setOnTouchListener { view, event ->
                gestureDetector.onTouchEvent(event)
                if (shareSelectionMode && event.actionMasked == MotionEvent.ACTION_DOWN) {
                    val pos = bindingAdapterPosition
                    if (pos in lines.indices) onSelectionLineDragStart?.invoke(pos)
                }
                if (shareSelectionMode && event.actionMasked == MotionEvent.ACTION_MOVE) {
                    val recyclerView = view.parent as? RecyclerView
                    if (recyclerView != null) {
                        val location = IntArray(2)
                        recyclerView.getLocationOnScreen(location)
                        val child = recyclerView.findChildViewUnder(
                            event.rawX - location[0],
                            event.rawY - location[1]
                        )
                        val hoveredPosition = if (child != null) {
                            recyclerView.getChildAdapterPosition(child)
                        } else {
                            RecyclerView.NO_POSITION
                        }
                        if (hoveredPosition in lines.indices) {
                            onSelectionLineDrag?.invoke(hoveredPosition)
                        }
                    }
                }
                true
            }
        }

        fun updateTextSize(baseSp: Float) {
            if (!isFullScreen) return
            val pos = bindingAdapterPosition
            if (shareSelectionMode) {
                updateSelectionTextSize(shareSelectionFontScale)
                return
            }
            val isActive = (pos == activeIndex)
            val targetSp = if (isActive) baseSp else (baseSp * 0.75f).coerceAtLeast(16f)
            lyricText.setTextSize(TypedValue.COMPLEX_UNIT_SP, targetSp)
        }

        fun updateSelectionTextSize(scale: Float) {
            lyricText.setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                selectionFontSizeSp() * scale
            )
        }

        fun bind(
            line: LyricLine,
            isActive: Boolean,
            selectionMode: Boolean,
            isSelected: Boolean
        ) {
            lyricText.text = if (line.text.isBlank()) "• • •" else line.text
            val ctx = itemView.context

            // Reset any item scale to 1.0f to prevent left-overflow and matrix clipping
            itemView.scaleX = 1.0f
            itemView.scaleY = 1.0f
            itemView.pivotX = 0f
            itemView.pivotY = 0f

            // Keep row geometry fixed; selection padding belongs inside the text highlight.
            val rowHorizontalInset = if (isFullScreen) 24 else 16
            if (isFullScreen) {
                lyricText.gravity = Gravity.START or Gravity.CENTER_VERTICAL
                lyricText.textAlignment = View.TEXT_ALIGNMENT_VIEW_START
                itemView.setPadding(dpToPx(ctx, rowHorizontalInset), dpToPx(ctx, 8), dpToPx(ctx, rowHorizontalInset), dpToPx(ctx, 8))
            } else {
                lyricText.gravity = Gravity.CENTER
                lyricText.textAlignment = View.TEXT_ALIGNMENT_CENTER
                itemView.setPadding(dpToPx(ctx, rowHorizontalInset), dpToPx(ctx, 10), dpToPx(ctx, rowHorizontalInset), dpToPx(ctx, 10))
            }

            // Apply letter spacing (-2%)
            lyricText.letterSpacing = LETTER_SPACING

            if (selectionMode) {
                if (isSelected) {
                    val highlight = GradientDrawable().apply {
                        color = ColorStateList.valueOf(ThemeColors.getPrimaryContainer(ctx))
                        cornerRadius = dpToPx(ctx, 20).toFloat()
                    }
                    val params = lyricText.layoutParams as? FrameLayout.LayoutParams
                    params?.width = ViewGroup.LayoutParams.WRAP_CONTENT
                    params?.gravity = if (isFullScreen) Gravity.START else Gravity.CENTER
                    if (params != null) lyricText.layoutParams = params
                    val availableWidth = (itemView.width.takeIf { it > 0 }
                        ?: ctx.resources.displayMetrics.widthPixels) - dpToPx(ctx, rowHorizontalInset * 2)
                    lyricText.maxWidth = availableWidth.coerceAtLeast(dpToPx(ctx, 120))
                    lyricText.background = highlight
                    val textInset = dpToPx(ctx, 16)
                    lyricText.setPadding(textInset, dpToPx(ctx, 6), textInset, dpToPx(ctx, 6))
                    lyricText.alpha = 1f
                    lyricText.setTextColor(ThemeColors.getOnPrimaryContainer(ctx))
                } else {
                    itemView.background = normalBackground
                    lyricText.background = normalTextBackground
                    lyricText.layoutParams = lyricText.layoutParams.apply {
                        width = ViewGroup.LayoutParams.MATCH_PARENT
                    }
                    lyricText.maxWidth = Int.MAX_VALUE
                    lyricText.setPadding(0, 0, 0, 0)
                    lyricText.alpha = 0.42f
                    lyricText.setTextColor(ThemeColors.getOnSurfaceVariant(ctx))
                }
                lyricText.typeface = if (isSelected) getBlackTypeface(ctx) else getMediumTypeface(ctx)
                lyricText.setTextSize(
                    TypedValue.COMPLEX_UNIT_SP,
                    selectionFontSizeSp() * shareSelectionFontScale
                )
                return
            }

            itemView.background = normalBackground
            lyricText.background = normalTextBackground
            lyricText.layoutParams = lyricText.layoutParams.apply {
                width = ViewGroup.LayoutParams.MATCH_PARENT
            }
            lyricText.maxWidth = Int.MAX_VALUE
            lyricText.setPadding(0, 0, 0, 0)

            if (isActive) {
                lyricText.alpha = 1.0f
                lyricText.setTextColor(ThemeColors.getOnSurface(ctx))
                // Static weight 900 (Black)
                lyricText.typeface = getBlackTypeface(ctx)

                if (isFullScreen) {
                    lyricText.setTextSize(TypedValue.COMPLEX_UNIT_SP, baseFontSizeSp)
                } else {
                    lyricText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17f)
                }
            } else {
                lyricText.alpha = 0.40f
                lyricText.setTextColor(ThemeColors.getOnSurfaceVariant(ctx))
                // Static weight 500 (Medium)
                lyricText.typeface = getMediumTypeface(ctx)

                if (isFullScreen) {
                    val inactiveSp = (baseFontSizeSp * 0.75f).coerceAtLeast(16f)
                    lyricText.setTextSize(TypedValue.COMPLEX_UNIT_SP, inactiveSp)
                } else {
                    lyricText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                }
            }
        }

        private fun selectionFontSizeSp(): Float = if (isFullScreen) {
            (baseFontSizeSp * 0.75f).coerceAtLeast(16f)
        } else {
            16f
        }
    }
}

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
import android.view.ViewConfiguration
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

    private enum class SelectionGestureState { IDLE, PRESSED, DRAGGING, LONG_PRESSED }

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
        private val selectionIndicator: View? = itemView.findViewById(R.id.lyric_selection_indicator)
        private val normalBackground = itemView.background
        private val normalTextBackground = lyricText.background
        private val gestureDetector: GestureDetector
        private val touchSlop = ViewConfiguration.get(itemView.context).scaledTouchSlop
        private var selectionGestureState = SelectionGestureState.IDLE
        private var selectionTapHandled = false
        private var selectionDownX = 0f
        private var selectionDownY = 0f

        init {
            gestureDetector = GestureDetector(itemView.context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    val pos = bindingAdapterPosition
                    if (pos !in lines.indices) return super.onSingleTapUp(e)
                    if (shareSelectionMode) {
                        selectionTapHandled = true
                        onSelectionLineClick?.invoke(pos)
                        return true
                    }
                    if (lines[pos].text.isBlank()) return super.onSingleTapUp(e)
                    return true
                }

                override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                    if (selectionTapHandled) {
                        selectionTapHandled = false
                        return true
                    }
                    val pos = bindingAdapterPosition
                    if (pos in lines.indices) {
                        if (shareSelectionMode) {
                            onSelectionLineClick?.invoke(pos)
                        } else if (pos != activeIndex && lines[pos].text.isNotBlank()) {
                            onLineClick?.invoke(lines[pos])
                        }
                    }
                    return true
                }

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    if (!shareSelectionMode) onLineDoubleTap?.invoke(lyricText)
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    val pos = bindingAdapterPosition
                    if (pos in lines.indices) {
                        selectionGestureState = SelectionGestureState.LONG_PRESSED
                        itemView.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
                        onLineSelectionStart?.invoke(pos)
                        itemView.parent?.requestDisallowInterceptTouchEvent(true)
                    }
                }
            })
            
            itemView.setOnTouchListener { view, event ->
                gestureDetector.onTouchEvent(event)
                when (event.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        selectionGestureState = SelectionGestureState.PRESSED
                        selectionTapHandled = false
                        selectionDownX = event.x
                        selectionDownY = event.y
                        val pos = bindingAdapterPosition
                        if (shareSelectionMode && pos in lines.indices) onSelectionLineDragStart?.invoke(pos)
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val dx = event.x - selectionDownX
                        val dy = event.y - selectionDownY
                        if (selectionGestureState == SelectionGestureState.PRESSED &&
                            dx * dx + dy * dy > touchSlop * touchSlop
                        ) {
                            selectionGestureState = SelectionGestureState.DRAGGING
                            view.parent?.requestDisallowInterceptTouchEvent(true)
                        }
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        selectionGestureState = SelectionGestureState.IDLE
                    }
                }
                if (shareSelectionMode && event.actionMasked == MotionEvent.ACTION_MOVE &&
                    (selectionGestureState == SelectionGestureState.DRAGGING ||
                        selectionGestureState == SelectionGestureState.LONG_PRESSED)
                ) {
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
                val isSelected = pos in shareSelectionLines
                val targetSp = if (isSelected) baseSp else (baseSp * 0.75f).coerceAtLeast(16f)
                lyricText.setTextSize(TypedValue.COMPLEX_UNIT_SP, targetSp)
                return
            }
            val isActive = (pos == activeIndex)
            val targetSp = if (isActive) baseSp else (baseSp * 0.75f).coerceAtLeast(16f)
            lyricText.setTextSize(TypedValue.COMPLEX_UNIT_SP, targetSp)
        }

        fun updateSelectionTextSize(scale: Float) {
            // Keep stable without jarring resizing
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
                itemView.background = normalBackground
                lyricText.background = null
                lyricText.setPadding(0, 0, 0, 0)
                val params = lyricText.layoutParams as? FrameLayout.LayoutParams
                params?.width = ViewGroup.LayoutParams.MATCH_PARENT
                params?.gravity = if (isFullScreen) Gravity.START or Gravity.CENTER_VERTICAL else Gravity.CENTER
                if (params != null) lyricText.layoutParams = params
                lyricText.maxWidth = Int.MAX_VALUE

                if (isSelected) {
                    selectionIndicator?.visibility = View.VISIBLE
                    val pos = bindingAdapterPosition
                    val hasPrev = (pos - 1) in shareSelectionLines
                    val hasNext = (pos + 1) in shareSelectionLines

                    val r = dpToPx(ctx, 3).toFloat()
                    val indicatorDrawable = GradientDrawable().apply {
                        shape = GradientDrawable.RECTANGLE
                        setColor(ThemeColors.getPrimary(ctx))
                        cornerRadii = when {
                            !hasPrev && !hasNext -> floatArrayOf(r, r, r, r, r, r, r, r)
                            !hasPrev && hasNext -> floatArrayOf(r, r, r, r, 0f, 0f, 0f, 0f)
                            hasPrev && hasNext -> floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
                            else -> floatArrayOf(0f, 0f, 0f, 0f, r, r, r, r)
                        }
                    }
                    selectionIndicator?.background = indicatorDrawable
                    selectionIndicator?.alpha = 1.0f

                    lyricText.translationX = if (isFullScreen) dpToPx(ctx, 12).toFloat() else 0f
                    lyricText.alpha = 1.0f
                    lyricText.setTextColor(ThemeColors.getPrimary(ctx))
                    lyricText.typeface = getBlackTypeface(ctx)
                } else {
                    selectionIndicator?.visibility = View.GONE
                    lyricText.translationX = 0f
                    lyricText.alpha = 0.28f
                    lyricText.setTextColor(ThemeColors.getOnSurfaceVariant(ctx))
                    lyricText.typeface = getMediumTypeface(ctx)
                }

                if (isFullScreen) {
                    val activeSp = baseFontSizeSp
                    val inactiveSp = (baseFontSizeSp * 0.75f).coerceAtLeast(16f)
                    lyricText.setTextSize(TypedValue.COMPLEX_UNIT_SP, if (isSelected) activeSp else inactiveSp)
                } else {
                    lyricText.setTextSize(TypedValue.COMPLEX_UNIT_SP, if (isSelected) 17f else 15f)
                }
                return
            }

            selectionIndicator?.visibility = View.GONE
            lyricText.translationX = 0f
            itemView.background = normalBackground
            lyricText.background = normalTextBackground
            val params = lyricText.layoutParams as? FrameLayout.LayoutParams
            params?.width = ViewGroup.LayoutParams.MATCH_PARENT
            params?.gravity = if (isFullScreen) Gravity.START or Gravity.CENTER_VERTICAL else Gravity.CENTER
            if (params != null) lyricText.layoutParams = params
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

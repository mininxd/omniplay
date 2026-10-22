package xyz.omniplay.ui

import android.content.Context
import android.graphics.Typeface
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

class LyricAdapter(
    private var lines: List<LyricLine> = emptyList(),
    val isFullScreen: Boolean = false,
    var onLineClick: ((LyricLine) -> Unit)? = null,
    var onLineDoubleTap: ((View?) -> Unit)? = null,
    var onLineLongPress: ((Int) -> Unit)? = null
) : RecyclerView.Adapter<LyricAdapter.LyricViewHolder>() {

    var activeIndex: Int = -1
        private set

    /** Base font size in SP for fullscreen active line (default 48sp, adjustable via pinch). */
    var baseFontSizeSp: Float = 48f

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LyricViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lyric_line, parent, false)
        return LyricViewHolder(view)
    }

    override fun onBindViewHolder(holder: LyricViewHolder, position: Int) {
        holder.bind(lines[position], position == activeIndex)
    }

    override fun getItemCount(): Int = lines.size

    inner class LyricViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val lyricText: TextView = itemView.findViewById(R.id.lyric_line_text)
        private val gestureDetector: GestureDetector

        init {
            gestureDetector = GestureDetector(itemView.context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                    val pos = bindingAdapterPosition
                    if (pos in lines.indices) {
                        if (pos != activeIndex) onLineClick?.invoke(lines[pos])
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
                        onLineLongPress?.invoke(pos)
                    }
                }
            })
            
            itemView.setOnTouchListener { _, event ->
                gestureDetector.onTouchEvent(event)
                true
            }
        }

        fun updateTextSize(baseSp: Float) {
            if (!isFullScreen) return
            val pos = bindingAdapterPosition
            val isActive = (pos == activeIndex)
            val targetSp = if (isActive) baseSp else (baseSp * 0.75f).coerceAtLeast(16f)
            lyricText.setTextSize(TypedValue.COMPLEX_UNIT_SP, targetSp)
        }

        fun bind(line: LyricLine, isActive: Boolean) {
            lyricText.text = if (line.text.isBlank()) "• • •" else line.text
            val ctx = itemView.context

            // Reset any item scale to 1.0f to prevent left-overflow and matrix clipping
            itemView.scaleX = 1.0f
            itemView.scaleY = 1.0f
            itemView.pivotX = 0f
            itemView.pivotY = 0f

            // Layout gravity & padding
            if (isFullScreen) {
                lyricText.gravity = Gravity.START or Gravity.CENTER_VERTICAL
                lyricText.textAlignment = View.TEXT_ALIGNMENT_VIEW_START
                itemView.setPadding(dpToPx(ctx, 24), dpToPx(ctx, 8), dpToPx(ctx, 24), dpToPx(ctx, 8))
            } else {
                lyricText.gravity = Gravity.CENTER
                lyricText.textAlignment = View.TEXT_ALIGNMENT_CENTER
                itemView.setPadding(dpToPx(ctx, 16), dpToPx(ctx, 10), dpToPx(ctx, 16), dpToPx(ctx, 10))
            }

            // Apply letter spacing (-2%)
            lyricText.letterSpacing = LETTER_SPACING

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
    }
}

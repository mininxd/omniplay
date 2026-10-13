package xyz.omniplay.ui

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import xyz.omniplay.R
import xyz.omniplay.lyrics.LyricLine
import xyz.omniplay.util.ThemeColors

class LyricAdapter(
    private var lines: List<LyricLine> = emptyList(),
    val isFullScreen: Boolean = false,
    var onLineClick: ((LyricLine) -> Unit)? = null
) : RecyclerView.Adapter<LyricAdapter.LyricViewHolder>() {

    var activeIndex: Int = -1
        private set

    companion object {
        private var cachedInterTypeface: Typeface? = null

        // Fullscreen GPU scale for active line (larger feel without CPU textSize changes)
        private const val FS_ACTIVE_SCALE = 1.25f
        private const val FS_INACTIVE_SCALE = 1.0f

        // Non-fullscreen scales (keep existing subtle bump)
        private const val CARD_ACTIVE_SCALE = 1.02f
        private const val CARD_INACTIVE_SCALE = 1.0f

        // Letter spacing per the design guide (-2%)
        private const val LETTER_SPACING = -0.02f

        // Fullscreen colors
        private val COLOR_FS_ACTIVE = Color.parseColor("#FFFFFF")
        // 40% white = #66FFFFFF
        private val COLOR_FS_INACTIVE = Color.parseColor("#66FFFFFF")

        fun getInterTypeface(ctx: Context): Typeface? {
            if (cachedInterTypeface == null) {
                cachedInterTypeface = try {
                    ResourcesCompat.getFont(ctx, R.font.inter_variable)
                } catch (e: Exception) {
                    null
                }
            }
            return cachedInterTypeface
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

        init {
            itemView.setOnClickListener {
                val pos = bindingAdapterPosition
                if (pos in lines.indices) {
                    onLineClick?.invoke(lines[pos])
                }
            }
        }

        fun bind(line: LyricLine, isActive: Boolean) {
            lyricText.text = if (line.text.isBlank()) "• • •" else line.text
            val ctx = itemView.context
            val interTypeface = getInterTypeface(ctx)

            // Layout gravity & padding
            if (isFullScreen) {
                lyricText.gravity = Gravity.START or Gravity.CENTER_VERTICAL
                lyricText.textAlignment = View.TEXT_ALIGNMENT_VIEW_START
                itemView.setPadding(dpToPx(ctx, 24), dpToPx(ctx, 12), dpToPx(ctx, 24), dpToPx(ctx, 12))
            } else {
                lyricText.gravity = Gravity.CENTER
                lyricText.textAlignment = View.TEXT_ALIGNMENT_CENTER
                itemView.setPadding(dpToPx(ctx, 16), dpToPx(ctx, 10), dpToPx(ctx, 16), dpToPx(ctx, 10))
            }

            // Apply letter spacing to all lines (-2%)
            lyricText.letterSpacing = LETTER_SPACING

            if (isActive) {
                if (isFullScreen) {
                    // GPU scale — no textSize change
                    itemView.scaleX = FS_ACTIVE_SCALE
                    itemView.scaleY = FS_ACTIVE_SCALE
                    lyricText.alpha = 1.0f
                    lyricText.setTextColor(COLOR_FS_ACTIVE)
                } else {
                    itemView.scaleX = CARD_ACTIVE_SCALE
                    itemView.scaleY = CARD_ACTIVE_SCALE
                    lyricText.alpha = 1.0f
                    lyricText.setTextColor(ThemeColors.getOnSurface(ctx))
                    // Keep card active size slightly larger (non-fullscreen can use textSize safely)
                    lyricText.textSize = 17f
                }

                // Weight 800 (Inter Black) for active line
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    try {
                        lyricText.fontVariationSettings = "'wght' 800"
                    } catch (ignored: Exception) {}
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && interTypeface != null) {
                    lyricText.typeface = Typeface.create(interTypeface, 800, false)
                } else if (interTypeface != null) {
                    lyricText.setTypeface(interTypeface, Typeface.BOLD)
                } else {
                    lyricText.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)
                }
            } else {
                if (isFullScreen) {
                    // GPU scale — no textSize change
                    itemView.scaleX = FS_INACTIVE_SCALE
                    itemView.scaleY = FS_INACTIVE_SCALE
                    lyricText.alpha = 1.0f   // alpha baked into color (#66FFFFFF)
                    lyricText.setTextColor(COLOR_FS_INACTIVE)
                } else {
                    itemView.scaleX = CARD_INACTIVE_SCALE
                    itemView.scaleY = CARD_INACTIVE_SCALE
                    lyricText.alpha = 0.90f
                    lyricText.setTextColor(ThemeColors.getOnSurface(ctx))
                    lyricText.textSize = 15f
                }

                // Weight 500 (Inter Medium) for inactive lines
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    try {
                        lyricText.fontVariationSettings = "'wght' 500"
                    } catch (ignored: Exception) {}
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && interTypeface != null) {
                    lyricText.typeface = Typeface.create(interTypeface, 500, false)
                } else if (interTypeface != null) {
                    lyricText.setTypeface(interTypeface, Typeface.NORMAL)
                } else {
                    lyricText.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL)
                }
            }
        }
    }
}

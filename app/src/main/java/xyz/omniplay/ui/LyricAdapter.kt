package xyz.omniplay.ui

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import xyz.omniplay.R
import xyz.omniplay.lyrics.LyricLine

class LyricAdapter(
    private var lines: List<LyricLine> = emptyList(),
    var onLineClick: ((LyricLine) -> Unit)? = null
) : RecyclerView.Adapter<LyricAdapter.LyricViewHolder>() {

    var activeIndex: Int = -1
        private set

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
            if (isActive) {
                lyricText.setTextColor(ContextCompat.getColor(ctx, R.color.text_primary))
                lyricText.alpha = 1.0f
                lyricText.textSize = 17f
                lyricText.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)
                itemView.scaleX = 1.02f
                itemView.scaleY = 1.02f
            } else {
                lyricText.setTextColor(ContextCompat.getColor(ctx, R.color.text_secondary))
                lyricText.alpha = 0.40f
                lyricText.textSize = 15f
                lyricText.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL)
                itemView.scaleX = 1.0f
                itemView.scaleY = 1.0f
            }
        }
    }
}

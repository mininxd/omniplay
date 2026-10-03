package xyz.omniplay.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import xyz.omniplay.R
import xyz.omniplay.databinding.ItemDrawerFilterBinding

data class FilterItem(
    val title: String,
    val count: Int,
    val isAlbum: Boolean,
    val isSelected: Boolean = false
)

class DrawerFilterAdapter(
    private val onItemClick: (FilterItem) -> Unit
) : RecyclerView.Adapter<DrawerFilterAdapter.ViewHolder>() {

    private var items: List<FilterItem> = emptyList()

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
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemDrawerFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FilterItem) {
            val context = binding.root.context
            binding.filterItemTitle.text = item.title
            binding.filterItemCount.text = "${item.count}"
            binding.filterItemIcon.setImageResource(
                if (item.isAlbum) R.drawable.ic_album else R.drawable.ic_person
            )

            if (item.isSelected) {
                binding.filterItemTitle.setTextColor(ContextCompat.getColor(context, R.color.primary_accent))
                binding.filterItemIcon.setColorFilter(ContextCompat.getColor(context, R.color.primary_accent))
                binding.filterItemCount.setTextColor(ContextCompat.getColor(context, R.color.primary_accent))
            } else {
                binding.filterItemTitle.setTextColor(ContextCompat.getColor(context, R.color.text_primary))
                binding.filterItemIcon.setColorFilter(ContextCompat.getColor(context, R.color.control_tint))
                binding.filterItemCount.setTextColor(ContextCompat.getColor(context, R.color.text_tertiary))
            }

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }
}

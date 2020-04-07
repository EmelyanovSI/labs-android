package by.gsu.siemeljanov.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.gsu.siemeljanov.R
import by.gsu.siemeljanov.model.ListItem

class ListAdapter(
    private val items: List<ListItem>,
    private val rowLayout: Int,
    private val clickListener: OnListItemClickListener
) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val number: TextView = itemView.findViewById(R.id.number)

        fun initialize(item: ListItem, action: OnListItemClickListener) {
            title.text = item.title
            name.text = item.name
            number.text = (item.number.toInt() + 1).toString()
            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        return ListViewHolder(v)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.initialize(items[position], clickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

interface OnListItemClickListener {
    fun onItemClick(item: ListItem, position: Int)
}

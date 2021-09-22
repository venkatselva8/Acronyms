package task.acronyms.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import task.acronyms.databinding.ItemAcronymLfBinding
import task.acronyms.model.LongForms

class AcronymLfAdapter : RecyclerView.Adapter<AcronymLfAdapter.LfViewHolder>() {

    var listOfLongForm = mutableListOf<LongForms>()

    fun setAcronymLfList(movies: List<LongForms>) {
        this.listOfLongForm = movies.toMutableList()
        notifyDataSetChanged()
    }

    fun clearData() {
        this.listOfLongForm.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LfViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ItemAcronymLfBinding.inflate(inflater, parent, false)
        return LfViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LfViewHolder, position: Int) {
        holder.binding.longForm = listOfLongForm[position]
    }

    override fun getItemCount(): Int {
        return listOfLongForm.size
    }

    class LfViewHolder(val binding: ItemAcronymLfBinding) : RecyclerView.ViewHolder(binding.root)
}


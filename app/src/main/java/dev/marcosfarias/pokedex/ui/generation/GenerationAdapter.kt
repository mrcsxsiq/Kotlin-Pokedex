package dev.marcosfarias.pokedex.ui.generation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.databinding.ItemGenerationBinding
import dev.marcosfarias.pokedex.model.Generation

class GenerationAdapter(
    private val list: List<Generation>,
    private val context: Context
) : RecyclerView.Adapter<GenerationAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val viewBinding = ItemGenerationBinding.bind(itemView)
        fun bindView(item: Generation) {
            viewBinding.textViewTitle.text = itemView.context.getString(item.title)
            viewBinding.imageView.setImageResource(item.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_generation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bindView(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

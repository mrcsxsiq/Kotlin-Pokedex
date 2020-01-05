package dev.marcosfarias.pokedex.adapter

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.ui.pokedex.PokedexFragmentDirections
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonAdapter(
    private val list: List<Pokemon>,
    private val context: Context
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Pokemon) {
            itemView.textViewName.text = item.name
            itemView.textViewID.text = item.id

            val color = PokemonColorUtil(itemView.context).getPokemonColor(item.typeofpokemon)
            itemView.relativeLayoutBackground.background.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

            item.typeofpokemon?.elementAtOrNull(0).let {
                itemView.textViewType3.text = it
                if (it == null) {
                    itemView.textViewType3.visibility = View.GONE
                }
            }

            item.typeofpokemon?.elementAtOrNull(1).let {
                itemView.textViewType2.text = it
                if (it == null) {
                    itemView.textViewType2.visibility = View.GONE
                }
            }

            item.typeofpokemon?.elementAtOrNull(2).let {
                itemView.textViewType1.text = it
                if (it == null) {
                    itemView.textViewType1.visibility = View.GONE
                }
            }

            Glide.with(itemView.context)
                .load(item.imageurl)
                .placeholder(android.R.color.transparent)
                .into(itemView.imageView)

            itemView.setOnClickListener {
                it.findNavController().navigate(PokedexFragmentDirections.actionNavigationPokedexToNavigationDashboard(item.id!!))
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false)
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
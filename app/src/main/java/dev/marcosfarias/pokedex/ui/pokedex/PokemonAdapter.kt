package dev.marcosfarias.pokedex.ui.pokedex

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.model.Pokemon
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
            itemView.relativeLayoutBackground.background.colorFilter =
                PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

            item.typeofpokemon?.getOrNull(0).let { firstType ->
                itemView.textViewType3.text = firstType
                if (firstType == null) {
                    itemView.textViewType3.visibility = View.GONE
                }
            }

            item.typeofpokemon?.getOrNull(1).let { secondType ->
                itemView.textViewType2.text = secondType
                if (secondType == null) {
                    itemView.textViewType2.visibility = View.GONE
                }
            }

            item.typeofpokemon?.getOrNull(2).let { thirdType ->
                itemView.textViewType1.text = thirdType
                if (thirdType == null) {
                    itemView.textViewType1.visibility = View.GONE
                }
            }

            Glide.with(itemView.context)
                .load(item.imageurl)
                .placeholder(android.R.color.transparent)
                .into(itemView.imageView)

            itemView.setOnClickListener {
                var bundle = bundleOf("id" to item.id)
                it.findNavController()
                    .navigate(R.id.action_navigation_pokedex_to_navigation_dashboard, bundle)
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
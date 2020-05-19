package dev.marcosfarias.pokedex.ui.pokedex

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import dev.marcosfarias.pokedex.utils.PokemonImageUtil
import dev.marcosfarias.pokedex.utils.PokemonStringUtil
import dev.marcosfarias.pokedex.utils.TypeIndexEnum
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokedexAdapter : RecyclerView.Adapter<PokedexAdapter.PokedexViewHolder>() {

    private var pokedexList: List<Pokemon> = mutableListOf()

    init {
        setHasStableIds(true)
    }

    class PokedexViewHolder(pokemonItemView: View) : RecyclerView.ViewHolder(pokemonItemView) {
        fun bindView(pokemonItem: Pokemon) {
            itemView.pokemonNameLabel.text = pokemonItem.name.capitalize()
            itemView.pokemonIDLabel.text = PokemonStringUtil().formatId(pokemonItem.id)

            val color = PokemonColorUtil(itemView.context).getColor(pokemonItem.types.last().type.name)
            itemView.relativeLayoutBackground.background.colorFilter =
                PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

            pokemonItem.types.getOrNull(TypeIndexEnum.THIRD.index)?.type?.let { thirdType ->
                itemView.thirdTypeLabel.text = thirdType.name.capitalize()
            }
            pokemonItem.types.getOrNull(TypeIndexEnum.SECOND.index)?.type?.let { secondType ->
                itemView.secondTypeLabel.text = secondType.name.capitalize()
            }
            pokemonItem.types.getOrNull(TypeIndexEnum.FIRST.index)?.type?.let { firstType ->
                itemView.firstTypeLabel.text = firstType.name.capitalize()
            }

            itemView.firstTypeLabel.visibility = if(itemView.firstTypeLabel.text.isNotEmpty()) View.VISIBLE else View.GONE
            itemView.secondTypeLabel.visibility = if(itemView.secondTypeLabel.text.isNotEmpty() && itemView.secondTypeLabel.text != itemView.firstTypeLabel.text) View.VISIBLE else View.GONE
            itemView.thirdTypeLabel.visibility = if(itemView.thirdTypeLabel.text.isNotEmpty() && itemView.thirdTypeLabel.text != itemView.secondTypeLabel.text) View.VISIBLE else View.GONE

            PokemonImageUtil().loadPokemonImage(itemView.context, pokemonItem.id, itemView.imageView)

            itemView.setOnClickListener {
                val bundle = bundleOf("id" to pokemonItem.id)
                it.findNavController()
                    .navigate(R.id.action_navigation_pokedex_to_navigation_dashboard, bundle)
            }
        }
    }

    fun updatePokedexListData(updateList: List<Pokemon>) {
        pokedexList = updateList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexViewHolder {
        val pokemonItemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokedexViewHolder(pokemonItemView)
    }

    override fun onBindViewHolder(holderPokedex: PokedexViewHolder, position: Int) {
        holderPokedex.bindView(pokedexList[position])
    }

    override fun getItemCount(): Int = pokedexList.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position
}

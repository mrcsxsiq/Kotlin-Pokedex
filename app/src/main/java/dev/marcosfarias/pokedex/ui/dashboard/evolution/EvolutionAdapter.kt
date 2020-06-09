package dev.marcosfarias.pokedex.ui.dashboard.evolution

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import dev.marcosfarias.pokedex.utils.PokemonImageUtil
import dev.marcosfarias.pokedex.utils.PokemonStringUtil
import dev.marcosfarias.pokedex.utils.TypeIndexEnum
import kotlinx.android.synthetic.main.item_pokemon.view.*

class EvolutionAdapter(
    private val context: Context
) : RecyclerView.Adapter<EvolutionAdapter.PokedexViewHolder>() {

    private var evolutionList: MutableList<Pokemon> = mutableListOf()

    class PokedexViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(pokemonItem: Pokemon) {
            itemView.pokemonNameLabel.text = pokemonItem.name.capitalize()
            itemView.pokemonIDLabel.text = PokemonStringUtil().formatId(pokemonItem.id)

            val color = PokemonColorUtil(itemView.context).getColor(pokemonItem.types.first().type.name)
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
        }
    }

    fun setPokemonInList(pokemonList: MutableList<Pokemon>) {
        evolutionList = pokemonList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false)
        return PokedexViewHolder(view)
    }

    override fun onBindViewHolder(holderPokedex: PokedexViewHolder, position: Int) {
        holderPokedex.bindView(evolutionList[position])
    }

    override fun getItemCount(): Int {
        return evolutionList.size
    }
}

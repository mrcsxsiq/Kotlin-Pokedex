package dev.marcosfarias.pokedex.ui.pokedex

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
import dev.marcosfarias.pokedex.utils.PokemonStringUtil
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokedexAdapter : RecyclerView.Adapter<PokedexAdapter.PokedexViewHolder>() {

    private var pokedexList: MutableList<Pokemon>
    init {
        pokedexList = mutableListOf()
        setHasStableIds(true)
    }

    class PokedexViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Pokemon) {
            itemView.textViewName.text = item.name.capitalize()
            itemView.textViewID.text = PokemonStringUtil.formatId(item.id)

            val color = PokemonColorUtil(itemView.context).getColor(item.types.last().type.name)
            itemView.relativeLayoutBackground.background.colorFilter =
                PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

            item.types.getOrNull(0)?.type?.let { firstType ->
                itemView.textViewType3.text = firstType.name.capitalize()
            }
            itemView.textViewType3.visibility = if(itemView.textViewType3.text.isNotEmpty()) View.VISIBLE else View.GONE

            item.types.getOrNull(1)?.type?.let { secondType ->
                itemView.textViewType2.text = secondType.name.capitalize()
            }
            itemView.textViewType2.visibility = if(itemView.textViewType2.text.isNotEmpty()) View.VISIBLE else View.GONE

            item.types.getOrNull(2)?.type?.let { thirdType ->
                itemView.textViewType1.text = thirdType.name.capitalize()
            }
            itemView.textViewType1.visibility = if(itemView.textViewType1.text.isNotEmpty()) View.VISIBLE else View.GONE

//            TODO: Fazer carregamento de Imagem
            Glide.with(itemView.context)
                .load("https://pokeres.bastionbot.org/images/pokemon/" + item.id + ".png")
                .placeholder(android.R.color.transparent)
                .into(itemView.imageView)

            itemView.setOnClickListener {
                val bundle = bundleOf("id" to item.id)
                it.findNavController()
                    .navigate(R.id.action_navigation_pokedex_to_navigation_dashboard, bundle)
            }
        }
    }

    fun updatePokedexListData(updateList: MutableList<Pokemon>) {
        this.pokedexList = updateList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokedexViewHolder(view)
    }

    override fun onBindViewHolder(holderPokedex: PokedexViewHolder, position: Int) {
        holderPokedex.bindView(pokedexList[position])
    }

    override fun getItemCount(): Int = pokedexList.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position
}

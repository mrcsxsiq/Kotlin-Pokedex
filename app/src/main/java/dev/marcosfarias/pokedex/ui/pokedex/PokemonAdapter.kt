package dev.marcosfarias.pokedex.ui.pokedex

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.utils.ImageLoadingListener
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonAdapter(
    private val list: List<Pokemon>,
    private val itemClickedListener: OnItemClickedListener? = null,
    private val imageLoadedListener: OnImageLoadedListener? = null
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(
            item: Pokemon,
            itemClickedListener: OnItemClickedListener?,
            imageLoadedListener: OnImageLoadedListener?
        ) {
            itemView.textViewName.text = item.name
            itemView.textViewID.text = item.id

            val color = PokemonColorUtil(itemView.context).getPokemonColor(item.typeofpokemon)
            itemView.relativeLayoutBackground.background.colorFilter =
                PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

            item.typeofpokemon?.getOrNull(0).let { firstType ->
                itemView.textViewType3.text = firstType
                itemView.textViewType3.isVisible = firstType != null
            }

            item.typeofpokemon?.getOrNull(1).let { secondType ->
                itemView.textViewType2.text = secondType
                itemView.textViewType2.isVisible = secondType != null
            }

            item.typeofpokemon?.getOrNull(2).let { thirdType ->
                itemView.textViewType1.text = thirdType
                itemView.textViewType1.isVisible = thirdType != null
            }

            Glide.with(itemView.context)
                .load(item.imageurl)
                .placeholder(android.R.color.transparent)
                .addListener(ImageLoadingListener {
                    imageLoadedListener?.invoke(item, itemView.imageView)
                })
                .into(itemView.imageView)

            itemView.imageView.transitionName = item.name
            itemView.setOnClickListener {
                itemClickedListener?.invoke(item, this)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bindView(item, itemClickedListener, imageLoadedListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

typealias OnImageLoadedListener = (pokemon: Pokemon, imageView: ImageView) -> Unit

typealias OnItemClickedListener = (pokemon: Pokemon, viewHolder: PokemonAdapter.ViewHolder) -> Unit

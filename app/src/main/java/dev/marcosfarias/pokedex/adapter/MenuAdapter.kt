package dev.marcosfarias.pokedex.adapter

import android.content.Context
import android.content.res.Resources
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.model.Menu
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.utils.PokemonColorUtil
import kotlinx.android.synthetic.main.item_pokemon.view.*

class MenuAdapter(
    private val list: List<Menu>,
    private val context: Context
) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Menu) {
            itemView.textViewName.text = item.name

            val color = PokemonColorUtil(itemView.context).covertColor(item.color)
            itemView.relativeLayoutBackground.background.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP )

            itemView.setOnClickListener {
                it.findNavController().navigate(R.id.action_navigation_home_to_navigation_pokedex)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false)
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
package dev.marcosfarias.pokedex.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class PokemonImageUtil {

    private val pokeApiImageUrl = "https://pokeres.bastionbot.org/images/pokemon/"
    private val pngType = ".png"

    fun loadPokemonImage(context: Context, pokemonId: Int, pokemonImageView: ImageView) {
        Glide.with(context)
            .load(pokeApiImageUrl + pokemonId + pngType)
            .placeholder(android.R.color.transparent)
            .into(pokemonImageView)
    }
}
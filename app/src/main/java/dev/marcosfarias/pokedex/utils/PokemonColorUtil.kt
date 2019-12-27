package dev.marcosfarias.pokedex.utils

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import dev.marcosfarias.pokedex.R

class PokemonColorUtil(var context: Context) {

    fun getPokemonColor(typeOfPokemon: List<String>?): Int {
        val type = typeOfPokemon?.elementAtOrNull(0)
        val color = when (type?.toLowerCase()) {
            "grass", "bug" -> R.color.lightTeal
            "fire" -> R.color.lightRed
            "water", "fighting", "normal" -> R.color.lightBlue
            "electric", "psychic" -> R.color.lightYellow
            "poison", "ghost" -> R.color.lightPurple
            "ground", "rock" -> R.color.lightBrown
            "dark" -> R.color.black
            else -> return R.color.lightBlue
        }
        return covertColor(color)
    }

    fun covertColor(color: Int): Int {
        return Color.parseColor("#" + Integer.toHexString(ContextCompat.getColor(context, color)))
    }

}
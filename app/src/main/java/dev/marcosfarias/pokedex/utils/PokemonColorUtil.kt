package dev.marcosfarias.pokedex.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import dev.marcosfarias.pokedex.R

class PokemonColorUtil(var context: Context) {
    enum class PokemonTypeEnum(val type: String, val color: Int) {
        NORMAL("normal", R.color.lightBlue),
        FIGHTING("fighting", R.color.lightBlue),
        FLYING("flying", R.color.lightBlue),
        POISON("poison", R.color.lightPurple),
        GROUND("ground", R.color.lightBrown),
        ROCK("rock", R.color.lightBrown),
        BUG("bug", R.color.lightTeal),
        GHOST("ghost", R.color.lightPurple),
        STEEL("steel", R.color.black),
        FIRE("fire", R.color.lightRed),
        WATER("water", R.color.lightBlue),
        GRASS("grass", R.color.lightTeal),
        ELECTRIC("electric", R.color.lightYellow),
        PSYCHIC("psychic", R.color.lightYellow),
        ICE("ice", R.color.lightBlue),
        DRAGON("dragon", R.color.lightBlue),
        DARK("dark", R.color.black),
        FAIRY("fairy", R.color.lightYellow),
        UNKNOWN("unknown", R.color.black),
        SHADOW("shadow", R.color.black)
    }

    @SuppressLint("DefaultLocale")
    @ColorInt
    fun getColor(type: String): Int {
        val color = PokemonTypeEnum.valueOf(type.toUpperCase()).color
        return convertColor(color)
    }

    @ColorInt
    fun convertColor(@ColorRes color: Int): Int {
        return ContextCompat.getColor(context, color)
    }
}

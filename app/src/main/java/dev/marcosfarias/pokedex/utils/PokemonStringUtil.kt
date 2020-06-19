package dev.marcosfarias.pokedex.utils

class PokemonStringUtil {
    fun formatId(id: Int) : String {
        return when (id.length()) {
            1 -> "#00$id"
            2 -> "#0$id"
            else -> "#$id"
        }
    }

    fun formatPounds(value: Float) : String {
        return value.toString().split(".")[0] + "' " + value.toString().split(".")[1].substring(0, 2) + "\""
    }

    fun formatFeet(value: Float) : String {
        return value.toString().substring(0, 4) + " lbs"
    }

    fun formatGenterRate(value: Int): String {
        return when (value) {
            1 -> "\u2641 87.5%  \u2640 12.5%"
            2 -> "\u2641 75.0%  \u2640 25.0%"
            3 -> "\u2641 62.5%  \u2640 37.5%"
            4 -> "\u2641 50.0%  \u2640 50.0%"
            5 -> "\u2641 37.5%  \u2640 62.5%"
            7 -> "\u2641 25.0%  \u2640 75.0%"
            8 -> "\u2641 12.5%  \u2640 87.5%"
            else -> "Genderless"
        }
    }

    companion object {
        const val ENGLISH = "en"
        const val HP = "hp"
        const val ATTACK = "attack"
        const val DEFENSE = "defense"
        const val SPECIAL_ATTACK = "special-attack"
        const val SPECIAL_DEFENSE = "special-defense"
        const val SPEED = "speed"
    }
}

private fun Int.length() = when(this) {
    0 -> 1
    else -> kotlin.math.log10(kotlin.math.abs(toDouble())).toInt() + 1
}

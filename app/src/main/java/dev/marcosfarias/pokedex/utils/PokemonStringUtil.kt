package dev.marcosfarias.pokedex.utils

class PokemonStringUtil {
    fun formatId(id: Int) : String {
        return when (id.length()) {
            1 -> "#00$id"
            2 -> "#0$id"
            else -> "#$id"
        }
    }
}

private fun Int.length() = when(this) {
    0 -> 1
    else -> kotlin.math.log10(kotlin.math.abs(toDouble())).toInt() + 1
}

package dev.marcosfarias.pokedex.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource

@Entity
data class PokemonStat(
    @SerializedName("stat") override val stat: NamedApiResource,
    @SerializedName("effort") override val effort: Int,
    @SerializedName("base_stat") override val baseStat: Int
) : PokemonStatApiInterface

interface PokemonStatApiInterface {
    val stat: NamedApiResource
    val effort: Int
    val baseStat: Int
}
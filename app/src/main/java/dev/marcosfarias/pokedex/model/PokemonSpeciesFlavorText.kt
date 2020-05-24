package dev.marcosfarias.pokedex.model

import com.google.gson.annotations.SerializedName
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource

data class PokemonSpeciesFlavorText(
    @SerializedName("flavor_text") override val flavorText: String,
    @SerializedName("language") override val language: NamedApiResource,
    @SerializedName("version") override val version: NamedApiResource

) : PokemonSpeciesFlavorTextApiInterface

interface PokemonSpeciesFlavorTextApiInterface {
    val flavorText: String
    val language: NamedApiResource
    val version: NamedApiResource
}
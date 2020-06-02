package dev.marcosfarias.pokedex.model

import com.google.gson.annotations.SerializedName
import me.sargunvohra.lib.pokekotlin.model.EvolutionDetail

data class ChainLink(
    @SerializedName("is_baby") override val isBaby: Boolean,
    @SerializedName("species") override val species: SpeciesName,
    @SerializedName("evolution_details") override val evolutionDetails: List<EvolutionDetail>,
    @SerializedName("evolves_to") override val evolvesTo: List<ChainLink>
) : ChainLinkApiInterface

interface ChainLinkApiInterface {
    val isBaby: Boolean
    val species: SpeciesName
    val evolutionDetails: List<EvolutionDetail>
    val evolvesTo: List<ChainLink>
}
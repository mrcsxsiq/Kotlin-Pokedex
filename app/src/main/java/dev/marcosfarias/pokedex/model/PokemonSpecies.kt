package dev.marcosfarias.pokedex.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import dev.marcosfarias.pokedex.utils.Converters
import me.sargunvohra.lib.pokekotlin.model.*

@Entity
@TypeConverters(Converters::class)
data class PokemonSpecies (
    @PrimaryKey
    @NonNull
    @SerializedName("id") override val id: Int,
    @SerializedName("name") override val name: String,
    @SerializedName("order") override val order: Int,
    @SerializedName("gender_rate") override val genderRate: Int,
    @SerializedName("capture_rate") override val captureRate: Int,
    @SerializedName("base_happiness") override val baseHappiness: Int,
    @SerializedName("is_baby") override val isBaby: Boolean,
    @SerializedName("hatch_counter") override val hatchCounter: Int,
    @SerializedName("has_gender_differences") override val hasGenderDifferences: Boolean,
    @SerializedName("forms_switchable") override val formsSwitchable: Boolean,
    @SerializedName("growth_rate") override val growthRate: NamedApiResource,
    @SerializedName("pokedex_numbers") override val pokedexNumbers: List<PokemonSpeciesDexEntry>,
    @SerializedName("egg_groups") override val eggGroups: List<NamedApiResource>,
    @SerializedName("color") override val color: NamedApiResource,
    @SerializedName("shape") override val shape: NamedApiResource,
    @SerializedName("evolves_from_species") override val evolvesFromSpecies: NamedApiResource?,
    @SerializedName("evolution_chain") override val evolutionChain: EvolutionChain,
    @SerializedName("habitat") override val habitat: NamedApiResource?,
    @SerializedName("generation") override val generation: NamedApiResource,
    @SerializedName("names") override val names: List<Name>,
    @SerializedName("pal_park_encounters") override val palParkEncounters: List<PalParkEncounterArea>,
    @SerializedName("form_descriptions") override val formDescriptions: List<Description>,
    @SerializedName("genera") override val genera: List<Genus>,
    @SerializedName("varieties") override val varieties: List<PokemonSpeciesVariety>,
    @SerializedName("flavor_text_entries") override val flavorTextEntries: List<PokemonSpeciesFlavorText>
) : PokemonSpeciesApiInterface

interface PokemonSpeciesApiInterface {
    val id: Int
    val name: String
    val order: Int
    val genderRate: Int
    val captureRate: Int
    val baseHappiness: Int
    val isBaby: Boolean
    val hatchCounter: Int
    val hasGenderDifferences: Boolean
    val formsSwitchable: Boolean
    val growthRate: NamedApiResource
    val pokedexNumbers: List<PokemonSpeciesDexEntry>
    val eggGroups: List<NamedApiResource>
    val color: NamedApiResource
    val shape: NamedApiResource
    val evolvesFromSpecies: NamedApiResource?
    val evolutionChain: EvolutionChain
    val habitat: NamedApiResource?
    val generation: NamedApiResource
    val names: List<Name>
    val palParkEncounters: List<PalParkEncounterArea>
    val formDescriptions: List<Description>
    val genera: List<Genus>
    val varieties: List<PokemonSpeciesVariety>
    val flavorTextEntries: List<PokemonSpeciesFlavorText>
}
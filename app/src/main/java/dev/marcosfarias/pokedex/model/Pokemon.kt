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
data class Pokemon(
    @PrimaryKey
    @NonNull
    @SerializedName("id") override val id: Int,
    @SerializedName("name") override val name: String,
    @SerializedName("base_experience") override val baseExperience: Int?,
    @SerializedName("height") override val height: Int?,
    @SerializedName("is_default") override val isDefault: Boolean?,
    @SerializedName("order") override val order: Int?,
    @SerializedName("weight") override val weight: Int?,
    @SerializedName("species") override val species: PokemonSpecies,
    @SerializedName("abilities") override val abilities: List<PokemonAbility>?,
    @SerializedName("forms") override val forms: List<NamedApiResource>?,
    @SerializedName("game_indices") override val gameIndices: List<VersionGameIndex>?,
    @SerializedName("held_items") override val heldItems: List<PokemonHeldItem>?,
    @SerializedName("moves") override val moves: List<PokemonMove>?,
    @SerializedName("stats") override val stats: List<PokemonStat>?,
    @SerializedName("types") override val types: List<PokemonType>,
    @SerializedName("sprites") override val sprites: PokemonSprites?
) : PokemonApiInterface

interface PokemonApiInterface {
    val id: Int
    val name: String?
    val baseExperience: Int?
    val height: Int?
    val isDefault: Boolean?
    val order: Int?
    val weight: Int?
    val species: PokemonSpecies
    val abilities: List<PokemonAbility>?
    val forms: List<NamedApiResource>?
    val gameIndices: List<VersionGameIndex>?
    val heldItems: List<PokemonHeldItem>?
    val moves: List<PokemonMove>?
    val stats: List<PokemonStat>?
    val types: List<PokemonType>?
    val sprites: PokemonSprites?
}
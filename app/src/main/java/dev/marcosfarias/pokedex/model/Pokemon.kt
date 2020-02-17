package dev.marcosfarias.pokedex.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import dev.marcosfarias.pokedex.utils.ListStringConverter

@Entity
@TypeConverters(ListStringConverter::class)
class Pokemon(
    @PrimaryKey
    @NonNull
    @SerializedName("id") val id: String,
    @SerializedName("abilities") val abilities: List<String>?,
    @SerializedName("attack") val attack: Int?,
    @SerializedName("base_exp") val baseExp: String?,
    @SerializedName("category") val category: String?,
    @SerializedName("cycles") val cycles: String?,
    @SerializedName("defense") val defense: Int?,
    @SerializedName("egg_groups") val eggGroups: String?,
    @SerializedName("evolutions") val evolutions: List<String>?,
    @SerializedName("evolvedfrom") val evolvedfrom: String?,
    @SerializedName("female_percentage") val femalePercentage: String?,
    @SerializedName("genderless") val genderless: Int?,
    @SerializedName("height") val height: String?,
    @SerializedName("hp") val hp: Int?,
    @SerializedName("imageurl") val imageurl: String?,
    @SerializedName("male_percentage") val malePercentage: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("reason") val reason: String?,
    @SerializedName("special_attack") val specialAttack: Int?,
    @SerializedName("special_defense") val specialDefense: Int?,
    @SerializedName("speed") val speed: Int?,
    @SerializedName("total") val total: Int?,
    @SerializedName("typeofpokemon") val typeofpokemon: List<String>?,
    @SerializedName("weaknesses") val weaknesses: List<String>?,
    @SerializedName("weight") val weight: String?,
    @SerializedName("xdescription") val xdescription: String?,
    @SerializedName("ydescription") val ydescription: String?
)
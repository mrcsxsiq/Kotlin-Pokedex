package dev.marcosfarias.pokedex.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import dev.marcosfarias.pokedex.utils.ListStringConverter

@Entity
@TypeConverters(ListStringConverter::class)
class Pokemon(
    @PrimaryKey
    @NonNull
    val id: String,
    val abilities: List<String>?,
    val attack: Int?,
    val base_exp: String?,
    val category: String?,
    val cycles: String?,
    val defense: Int?,
    val egg_groups: String?,
    val evolutions: List<String>?,
    val evolvedfrom: String?,
    val female_percentage: String?,
    val genderless: Int?,
    val height: String?,
    val hp: Int?,
    val imageurl: String?,
    val male_percentage: String?,
    val name: String?,
    val reason: String?,
    val special_attack: Int?,
    val special_defense: Int?,
    val speed: Int?,
    val total: Int?,
    val typeofpokemon: List<String>?,
    val weaknesses: List<String>?,
    val weight: String?,
    val xdescription: String?,
    val ydescription: String?
)
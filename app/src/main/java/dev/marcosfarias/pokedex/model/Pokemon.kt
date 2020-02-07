package dev.marcosfarias.pokedex.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import dev.marcosfarias.pokedex.utils.ListStringConverter

@Entity
@TypeConverters(ListStringConverter::class)
class Pokemon {
    @PrimaryKey
    @NonNull
    var id: String? = null
    var abilities: List<String>? = null
    var attack: Int? = null
    var base_exp: String? = null
    var category: String? = null
    var cycles: String? = null
    var defense: Int? = null
    var egg_groups: String? = null
    var evolutions: List<String>? = null
    var evolvedfrom: String? = null
    var female_percentage: String? = null
    var genderless: Int? = null
    var height: String? = null
    var hp: Int? = null
    var imageurl: String? = null
    var male_percentage: String? = null
    var name: String? = null
    var reason: String? = null
    var special_attack: Int? = null
    var special_defense: Int? = null
    var speed: Int? = null
    var total: Int? = null
    var typeofpokemon: List<String>? = null
    var weaknesses: List<String>? = null
    var weight: String? = null
    var xdescription: String? = null
    var ydescription: String? = null
}

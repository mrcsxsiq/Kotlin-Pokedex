package dev.marcosfarias.pokedex.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.sargunvohra.lib.pokekotlin.model.*
import java.lang.reflect.Type

class Converters {
    val gson = Gson()

    @TypeConverter
    fun fromStringToNamedApiResource(json: String?) : NamedApiResource? {
        val type: Type = object : TypeToken<NamedApiResource>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromNamedApiResourceToString(namedApiResource: NamedApiResource?) : String? {
        val type: Type = object : TypeToken<NamedApiResource>() {}.type
        return gson.toJson(namedApiResource, type)
    }

    @TypeConverter
    fun fromStringToListPokemonAbility(json: String?) : List<PokemonAbility>? {
        val type: Type = object : TypeToken<List<PokemonAbility>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListPokemonAbilityToString(listPokemonAbility: List<PokemonAbility>?) : String? {
        val type: Type = object : TypeToken<List<PokemonAbility>>() {}.type
        return gson.toJson(listPokemonAbility, type)
    }

    @TypeConverter
    fun fromStringToListNamedApiResource(json: String?) : List<NamedApiResource>? {
        val type: Type = object : TypeToken<List<NamedApiResource>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListNamedApiResourceToString(listNamedApiResource: List<NamedApiResource>?) : String? {
        val type: Type = object : TypeToken<List<NamedApiResource>>() {}.type
        return gson.toJson(listNamedApiResource, type)
    }

    @TypeConverter
    fun fromStringToListVersionGameIndex(json: String?) : List<VersionGameIndex>? {
        val type: Type = object : TypeToken<List<VersionGameIndex>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListVersionGameIndexToString(listVersionGameIndex: List<VersionGameIndex>?) : String? {
        val type: Type = object : TypeToken<List<VersionGameIndex>>() {}.type
        return gson.toJson(listVersionGameIndex, type)
    }

    @TypeConverter
    fun fromStringToListPokemonHeldItem(json: String?) : List<PokemonHeldItem>? {
        val type: Type = object : TypeToken<List<PokemonHeldItem>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListPokemonHeldItemToString(listPokemonHeldItem: List<PokemonHeldItem>?) : String? {
        val type: Type = object : TypeToken<List<PokemonHeldItem>>() {}.type
        return gson.toJson(listPokemonHeldItem, type)
    }

    @TypeConverter
    fun fromStringToListPokemonMove(json: String?) : List<PokemonMove>? {
        val type: Type = object : TypeToken<List<PokemonMove>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListPokemonMoveToString(listPokemonMove: List<PokemonMove>?) : String? {
        val type: Type = object : TypeToken<List<PokemonMove>>() {}.type
        return gson.toJson(listPokemonMove, type)
    }

    @TypeConverter
    fun fromStringToListPokemonStat(json: String?) : List<PokemonStat>? {
        val type: Type = object : TypeToken<List<PokemonStat>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListPokemonStatToString(listPokemonStat: List<PokemonStat>?) : String? {
        val type: Type = object : TypeToken<List<PokemonStat>>() {}.type
        return gson.toJson(listPokemonStat, type)
    }

    @TypeConverter
    fun fromStringToListPokemonType(json: String?) : List<PokemonType>? {
        val type: Type = object : TypeToken<List<PokemonType>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListPokemonTypeToString(listPokemonType: List<PokemonType>?) : String? {
        val type: Type = object : TypeToken<List<PokemonType>>() {}.type
        return gson.toJson(listPokemonType, type)
    }

    @TypeConverter
    fun fromStringToPokemonSprites(json: String?) : PokemonSprites? {
        val type: Type = object : TypeToken<PokemonSprites>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromPokemonSpritesToString(listPokemonType: PokemonSprites?) : String? {
        val type: Type = object : TypeToken<PokemonSprites>() {}.type
        return gson.toJson(listPokemonType, type)
    }
}
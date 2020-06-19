package dev.marcosfarias.pokedex.model.commons

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.sargunvohra.lib.pokekotlin.model.*
import java.lang.reflect.Type

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromStringToNamedApiResource(json: String) : NamedApiResource? {
        val type: Type = object : TypeToken<NamedApiResource?>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromNamedApiResourceToString(namedApiResource: NamedApiResource?) : String {
        val type: Type = object : TypeToken<NamedApiResource?>() {}.type
        return gson.toJson(namedApiResource, type)
    }

    @TypeConverter
    fun fromStringToListNamedApiResource(json: String) : List<NamedApiResource> {
        val type: Type = object : TypeToken<List<NamedApiResource>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListNamedApiResourceToString(listNamedApiResource: List<NamedApiResource>) : String {
        val type: Type = object : TypeToken<List<NamedApiResource>>() {}.type
        return gson.toJson(listNamedApiResource, type)
    }

    @TypeConverter
    fun fromStringToListPokemonAbility(json: String) : List<PokemonAbility> {
        val type: Type = object : TypeToken<List<PokemonAbility>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListPokemonAbilityToString(listPokemonAbility: List<PokemonAbility>) : String {
        val type: Type = object : TypeToken<List<PokemonAbility>>() {}.type
        return gson.toJson(listPokemonAbility, type)
    }

    @TypeConverter
    fun fromStringToListVersionGameIndex(json: String) : List<VersionGameIndex> {
        val type: Type = object : TypeToken<List<VersionGameIndex>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListVersionGameIndexToString(listVersionGameIndex: List<VersionGameIndex>) : String {
        val type: Type = object : TypeToken<List<VersionGameIndex>>() {}.type
        return gson.toJson(listVersionGameIndex, type)
    }

    @TypeConverter
    fun fromStringToListPokemonHeldItem(json: String) : List<PokemonHeldItem> {
        val type: Type = object : TypeToken<List<PokemonHeldItem>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListPokemonHeldItemToString(listPokemonHeldItem: List<PokemonHeldItem>) : String {
        val type: Type = object : TypeToken<List<PokemonHeldItem>>() {}.type
        return gson.toJson(listPokemonHeldItem, type)
    }

    @TypeConverter
    fun fromStringToListPokemonMove(json: String) : List<PokemonMove> {
        val type: Type = object : TypeToken<List<PokemonMove>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListPokemonMoveToString(listPokemonMove: List<PokemonMove>) : String {
        val type: Type = object : TypeToken<List<PokemonMove>>() {}.type
        return gson.toJson(listPokemonMove, type)
    }

    @TypeConverter
    fun fromStringToListPokemonStat(json: String) : List<PokemonStat> {
        val type: Type = object : TypeToken<List<PokemonStat>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListPokemonStatToString(listPokemonStat: List<PokemonStat>) : String {
        val type: Type = object : TypeToken<List<PokemonStat>>() {}.type
        return gson.toJson(listPokemonStat, type)
    }

    @TypeConverter
    fun fromStringToListPokemonType(json: String) : List<PokemonType> {
        val type: Type = object : TypeToken<List<PokemonType>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListPokemonTypeToString(listPokemonType: List<PokemonType>) : String {
        val type: Type = object : TypeToken<List<PokemonType>>() {}.type
        return gson.toJson(listPokemonType, type)
    }

    @TypeConverter
    fun fromStringToPokemonSprites(json: String) : PokemonSprites {
        val type: Type = object : TypeToken<PokemonSprites>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromPokemonSpritesToString(pokemonSprites: PokemonSprites) : String {
        val type: Type = object : TypeToken<PokemonSprites>() {}.type
        return gson.toJson(pokemonSprites, type)
    }

    @TypeConverter
    fun fromStringToListPokemonSpeciesDexEntry(json: String) : List<PokemonSpeciesDexEntry> {
        val type: Type = object : TypeToken<List<PokemonSpeciesDexEntry>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListPokemonSpeciesDexEntryToString(listPokemonSpeciesDexEntry: List<PokemonSpeciesDexEntry>) : String {
        val type: Type = object : TypeToken<List<PokemonSpeciesDexEntry>>() {}.type
        return gson.toJson(listPokemonSpeciesDexEntry, type)
    }

    @TypeConverter
    fun fromStringToApiResource(json: String) : ApiResource {
        val type: Type = object : TypeToken<ApiResource>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListApiResourceToString(apiResource: ApiResource) : String {
        val type: Type = object : TypeToken<ApiResource>() {}.type
        return gson.toJson(apiResource, type)
    }

    @TypeConverter
    fun fromStringToListName(json: String) : List<Name> {
        val type: Type = object : TypeToken<List<Name>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListNameToString(listName: List<Name>) : String {
        val type: Type = object : TypeToken<List<Name>>() {}.type
        return gson.toJson(listName, type)
    }

    @TypeConverter
    fun fromStringToListPalParkEncounterArea(json: String) : List<PalParkEncounterArea> {
        val type: Type = object : TypeToken<List<PalParkEncounterArea>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListPalParkEncounterAreaToString(listPalParkEncounterArea: List<PalParkEncounterArea>) : String {
        val type: Type = object : TypeToken<List<PalParkEncounterArea>>() {}.type
        return gson.toJson(listPalParkEncounterArea, type)
    }

    @TypeConverter
    fun fromStringToListDescription(json: String) : List<Description> {
        val type: Type = object : TypeToken<List<Description>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListDescriptionToString(listDescription: List<Description>) : String {
        val type: Type = object : TypeToken<List<Description>>() {}.type
        return gson.toJson(listDescription, type)
    }

    @TypeConverter
    fun fromStringToListGenus(json: String) : List<Genus> {
        val type: Type = object : TypeToken<List<Genus>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListGenusToString(listGenus: List<Genus>) : String {
        val type: Type = object : TypeToken<List<Genus>>() {}.type
        return gson.toJson(listGenus, type)
    }

    @TypeConverter
    fun fromStringToListPokemonSpeciesVariety(json: String) : List<PokemonSpeciesVariety> {
        val type: Type = object : TypeToken<List<PokemonSpeciesVariety>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListPokemonSpeciesVarietyToString(listPokemonSpeciesVariety: List<PokemonSpeciesVariety>) : String {
        val type: Type = object : TypeToken<List<PokemonSpeciesVariety>>() {}.type
        return gson.toJson(listPokemonSpeciesVariety, type)
    }

    @TypeConverter
    fun fromStringToListPokemonSpeciesFlavorText(json: String) : List<PokemonSpeciesFlavorText> {
        val type: Type = object : TypeToken<List<PokemonSpeciesFlavorText>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromListPokemonSpeciesFlavorTextToString(listPokemonSpeciesFlavorText: List<PokemonSpeciesFlavorText>) : String {
        val type: Type = object : TypeToken<List<PokemonSpeciesFlavorText>>() {}.type
        return gson.toJson(listPokemonSpeciesFlavorText, type)
    }

    @TypeConverter
    fun fromStringToChainLink(json: String) : ChainLink {
        val type: Type = object : TypeToken<ChainLink>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromChainLinkToString(chainLink: ChainLink) : String {
        val type: Type = object : TypeToken<ChainLink>() {}.type
        return gson.toJson(chainLink, type)
    }

    fun fromKilogramsToPounds(kilograms: Int): Float {
        return (kilograms * 0.220462F)
    }

    fun fromCentimetersToFeet(centimeters: Int): Float {
        return (centimeters * 0.328084F)
    }
}
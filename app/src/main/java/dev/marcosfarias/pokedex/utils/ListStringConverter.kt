package dev.marcosfarias.pokedex.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ListStringConverter {
    val gson = Gson()
    val type: Type = object : TypeToken<List<String?>?>() {}.type

    @TypeConverter
    fun fromString(json: String?): List<String> {
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromList(list: List<String?>?): String {
        return gson.toJson(list, type)
    }
}

package com.coolhabit.filmsearchapp.data.db.converters

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class StringArrayConverter {

    @TypeConverter
    fun listToJson(value: List<String>): String = Json.encodeToString(value.toTypedArray())

    @TypeConverter
    fun jsonToList(value: String) = ArrayList<String>().apply {
        addAll(Json.decodeFromString(value))
    }.toList()
}

package com.example.rickandmorty.database

import androidx.room.TypeConverter
import com.example.rickandmorty.model.Info
import com.example.rickandmorty.model.Results
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MapToJsonConverter {

    @TypeConverter
    fun mapToJson(map: Map<String, Results>): String {
        return Gson().toJson(map)
    }

    @TypeConverter
    fun jsonToMap(json: String): Map<String, Results> {
        val type = object : TypeToken<Map<String, Results>>() {}.type
        return Gson().fromJson(json, type)
    }
}
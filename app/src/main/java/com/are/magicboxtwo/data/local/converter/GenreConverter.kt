package com.are.magicboxtwo.data.local.converter

import androidx.room.TypeConverter
import com.are.magicboxtwo.data.model.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreConverter {

    @TypeConverter
    fun fromString(value: String): List<Genre>? {
        val listType = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson<List<Genre>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Genre>?): String {
        return Gson().toJson(list)
    }
}
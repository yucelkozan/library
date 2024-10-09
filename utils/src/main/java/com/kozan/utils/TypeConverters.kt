package com.kozan.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.python.coding.education.model.Answer


object TypeConverters {


    @TypeConverter
    fun fromResultList(value: MutableList<Answer>): String {
        val gson = Gson()
        val type = object : TypeToken<MutableList<Answer>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toResultList(value: String): MutableList<Answer> {
        val gson = Gson()
        val type = object : TypeToken<MutableList<Answer>>() {}.type
        return gson.fromJson(value, type)
    }



    @TypeConverter
    fun fromList(value: List<String>): String {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toList(value: String): List<String> {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }









}
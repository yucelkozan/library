package com.kozan.utils

import android.content.Context
import android.os.Environment
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.io.BufferedReader
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter

class JsonHelper(val context: Context) {


    /*
        Caused by: java.lang.ClassCastException: com.google.gson.internal.LinkedTreeMap cannot be cast to com.python.coding.education.model.TextLessonDTO
        inline fun <reified T : Any
    */
    inline fun <reified T> readJsonInAssets(fileName: String): List<T> {
        CoroutineScope(Dispatchers.IO)
        val jsonFileString =
            context.assets.open(fileName).bufferedReader().use {
                it.readText() }
        val type = object : TypeToken<List<T>>() {}.type
        val list: List<T> = Gson().fromJson(jsonFileString, type)
        return list
    }


    fun saveJsonFile(context: Context, fileName: String,list: Any,onSuccess : Runnable) {

        val file = File(context.filesDir, fileName)
        try {
            PrintWriter(FileWriter(file)).use {
                val gson = Gson()
                val jsonString = gson.toJson(list)
                it.write(jsonString)
                onSuccess.run()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

   inline fun <reified T> readJsonFile(fileName: String): List<T>{
       val file = File(context.filesDir, fileName)
       val jsonString = file.readText()
       val type = object : TypeToken<List<T>>() {}.type
       val list: MutableList<T> = Gson().fromJson(jsonString, type)
       return list
   }



}
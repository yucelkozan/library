package com.kozan.translate

import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import java.lang.Exception

object TranslateHelper {

    val langList = listOf(
        TranslateLanguage.ARABIC,
        TranslateLanguage.CHINESE,
        TranslateLanguage.RUSSIAN,
//        TranslateLanguage.FRENCH,
//        TranslateLanguage.SPANISH,
//        TranslateLanguage.BENGALI,
//        TranslateLanguage.PORTUGUESE,
//        TranslateLanguage.URDU,
//        TranslateLanguage.HINDI,
//        TranslateLanguage.INDONESIAN
    )

    lateinit var translator: Translator
    var translator0 : Translator? = null
    var translator1 : Translator? = null
    var translator2 : Translator? = null
    var translator3 : Translator? = null
    var translator4 : Translator? = null
    var translator5 : Translator? = null
    var translator6 : Translator? = null
    var translator7 : Translator? = null
    var translator8 : Translator? = null
    var translator9 : Translator? = null
    var translator10 : Translator? = null
    var translator11 : Translator? = null

    val translatorList = mutableListOf(
        translator0,
        translator1,
        translator2,
        translator3,
        translator4,
        translator5,
        translator6,
        translator7,
        translator8,
        translator9,
        translator10,
        translator11,
        )


    //val lang = TranslateLanguage.RUSSIAN

    fun build(lang : String,isDownloaded: Runnable) {
        val options = TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.TURKISH)
                .setTargetLanguage(lang)
                .build()
          translator = Translation.getClient(options)
            translator.downloadModelIfNeeded()
                    .addOnSuccessListener {
                        println("$lang indirildi.")
                        isDownloaded.run()
                    }
                    .addOnFailureListener { exception ->

                    }
            }



  fun translate(text:String,onTranslatedText:(String?)->Unit){
      translator.translate(text)
              .addOnSuccessListener { translatedText ->
                  onTranslatedText.invoke(translatedText)
              }
              .addOnFailureListener { exception ->
                  onTranslatedText.invoke(null)
              }
      }
    }

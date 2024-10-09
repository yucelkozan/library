package com.kozan.utils

import android.content.Context
import android.media.MediaPlayer

object PlayerHelper {

    private var player: MediaPlayer? = null

    fun playRemoteAudio( url : String) {
        stopSound()
        player = MediaPlayer().apply {
            /*
            * You must either catch or pass IllegalArgumentException and IOException when using setDataSource(),
            *  because the file you are referencing might not exist.
            * */
            try {
                setDataSource(url)
            } catch (e : Exception) {
                e.printStackTrace()
            }
            prepareAsync()
            setOnPreparedListener {
                it.start()
                setOnCompletionListener {
                    stopSound()
                }
            }
        }
    }



    fun playRawAudio(context: Context?, rawResId:Int) {
        context?.let {
            stopSound()
            player = MediaPlayer.create(context, rawResId)
            player?.start()
            player?.setOnCompletionListener {
                stopSound() }
        }
    }

    fun stopSound() {
        player?.let {
            player!!.stop()
            player!!.release()
            player = null
        }
    }

}



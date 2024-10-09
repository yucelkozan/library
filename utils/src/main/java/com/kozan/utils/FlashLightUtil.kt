package com.kozan.utils

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager

class FlashlightUtil private constructor(private val context: Context) {

    private var isFlashOn: Boolean = false
    private var cameraManager: CameraManager? = null
    private var cameraId: String? = null

    init {
        cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        cameraId = cameraManager?.cameraIdList?.firstOrNull { id ->
            cameraManager?.getCameraCharacteristics(id)
                ?.get(CameraCharacteristics.FLASH_INFO_AVAILABLE) == true
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: FlashlightUtil? = null

        fun getInstance(context: Context): FlashlightUtil {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: FlashlightUtil(context).also { INSTANCE = it }
            }
        }
    }

    fun toggleFlashlight() {
        if (isFlashOn) {
            turnFlashlightOff()
        } else {
            turnFlashlightOn()
        }
    }

    private fun turnFlashlightOn() {
        if (cameraId != null) {
            try {
                cameraManager?.setTorchMode(cameraId!!, true)
                isFlashOn = true
            } catch (e: CameraAccessException) {
                e.printStackTrace()
            }
        }
    }

    private fun turnFlashlightOff() {
        if (cameraId != null) {
            try {
                cameraManager?.setTorchMode(cameraId!!, false)
                isFlashOn = false
            } catch (e: CameraAccessException) {
                e.printStackTrace()
            }
        }
    }

    fun isFlashlightOn(): Boolean {
        return isFlashOn
    }
}

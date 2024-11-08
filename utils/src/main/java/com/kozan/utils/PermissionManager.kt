package com.kozan.utils

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

object PermissionManager {

    private lateinit var resultCallback: (Boolean) -> Unit
    private lateinit var activityResultLauncher: ActivityResultLauncher<Array<String>>

    // İzin isteklerinin başlatılması için Activity'ye kaydolma
    fun register(appCompatActivity: AppCompatActivity) {
        activityResultLauncher = appCompatActivity.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val allGranted = permissions.values.all { it }
            resultCallback.invoke(allGranted)
        }
    }

    // İzinleri kontrol et ve sonucu geri bildir
    fun checkPermissions(permissions: Array<String>, resultCallback: (Boolean) -> Unit) {
        this.resultCallback = resultCallback
        activityResultLauncher.launch(permissions)
    }
}

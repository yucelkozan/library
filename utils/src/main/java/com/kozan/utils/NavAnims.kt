package com.kozan.utils

import androidx.navigation.NavOptions

object NavAnims {

    fun fromRight(): NavOptions {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.from_right)   // Ekrana sağdan giriş
            .setExitAnim(R.anim.to_left)       // Ekrandan sola çıkış
            .setPopEnterAnim(R.anim.from_left) // Geriye dönüşte soldan giriş
            .setPopExitAnim(R.anim.to_right)   // Geriye dönüşte sağa çıkış
            .build()
    }

    fun fromLeft(): NavOptions {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.from_left)    // Ekrana soldan giriş
            .setExitAnim(R.anim.to_right)      // Ekrandan sağa çıkış
            .setPopEnterAnim(R.anim.from_right)// Geriye dönüşte sağdan giriş
            .setPopExitAnim(R.anim.to_left)    // Geriye dönüşte sola çıkış
            .build()
    }

    fun fromBottom(): NavOptions {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.from_top)     // Ekrana yukarıdan giriş
            .setExitAnim(R.anim.to_bottom)     // Ekrandan aşağıya çıkış
            .setPopEnterAnim(R.anim.from_bottom)// Geriye dönüşte aşağıdan giriş
            .setPopExitAnim(R.anim.to_top)     // Geriye dönüşte yukarı çıkış
            .build()
    }

    fun fromTop(): NavOptions {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.from_bottom)
            .setExitAnim(R.anim.to_top)
            .setPopEnterAnim(R.anim.from_top)
            .setPopExitAnim(R.anim.to_bottom)
            .build()
    }

}

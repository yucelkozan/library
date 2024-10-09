package com.kozan.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.navigation.NavController


fun Activity.startAnotherActivityWithAnim(activityClass: Class<*>){
    this.startActivity(Intent(this as Context,activityClass))
    this.overridePendingTransition(R.anim.from_right, R.anim.to_left);
}




/**
 * intArrayOf(
 * Color.parseColor("#E8F8F8"),
 * Color.parseColor("#64E3E3"),
 * Color.parseColor("#E064E3"))
 *
 */
fun TextView.paintTextView(colorIntArray: IntArray) {
    val paint = this.paint
    val width = paint.measureText(this.text.toString())
    val textShader: Shader = LinearGradient(0f, 0f, width, this.textSize,
        colorIntArray,
        null, Shader.TileMode.REPEAT)
    this.paint.setShader(textShader)
}


fun NavController.navigateWithAnim(destinationId:Int, bundle : Bundle? = null){
    this.navigate(destinationId,bundle,AnimationBuilder.getFromRightAnimations())
}

fun Context?.showToastMessage(text: String){
    Toast.makeText(this,text, Toast.LENGTH_LONG).show()
}

fun View.hideKeyboard() {
    val imm =
        this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun ComponentActivity.makeFullScreen() {
    window.setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
    )
}

fun Context.openBrowse(link: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
    this.startActivity(browserIntent)
}
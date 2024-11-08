
package com.kozan.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.setPadding
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins
import androidx.navigation.NavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration


fun Activity.startAnotherActivityWithAnim(activityClass: Class<*>,finishCurrent : Boolean = false){
    this.startActivity(Intent(this as Context,activityClass))
    this.overridePendingTransition(R.anim.from_right, R.anim.to_left)
    if (finishCurrent) this.finish()
}


fun RecyclerView.addVerticalDivider(colorResId : Int) {
   val itemDecoration =  MaterialDividerItemDecoration(
        this@addVerticalDivider.context,
        DividerItemDecoration.VERTICAL
    ).apply {
        dividerInsetEnd = 60
        dividerInsetStart = 60
        dividerColor = ContextCompat.getColor(this@addVerticalDivider.context, colorResId)
    }

    this.addItemDecoration(itemDecoration)

}
fun View.setStatusBarPadding() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        val statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
        view.setPadding(0, statusBarHeight, 0, 0) // Status bar yüksekliği kadar padding ekleyin
        insets // İşlemi tamamlarken insets'i geri döndür
    }
}

fun Activity.hideSystemUI() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.setDecorFitsSystemWindows(false)
        window.insetsController?.let {
            it.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    } else {
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
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
    this.navigate(destinationId,bundle,NavAnims.fromRight())
}

fun Context?.showToast(text: String,isLong: Boolean = false){
    Toast.makeText(this,text, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
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

fun Context.openBrowse(uriString: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uriString))
    this.startActivity(browserIntent)
}


/**
 * clipToPadding=false to ensure the list is visible while scrolling behind the navigation bar.
 * */
fun View.updateBottomPaddingByBottomSystemBar() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        view.setPadding(view.paddingLeft,view.paddingTop, view.paddingRight, systemBars.bottom)
        insets
    }
}

fun View.updateMarginsBySystemBars() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            val currentMargins = (view.layoutParams as? ViewGroup.MarginLayoutParams)
            updateMargins(
                left = (currentMargins?.leftMargin ?: 0) + systemBars.left,
                top = (currentMargins?.topMargin ?: 0) + systemBars.top,
                right = (currentMargins?.rightMargin ?: 0) + systemBars.right,
                bottom = (currentMargins?.bottomMargin ?: 0) + systemBars.bottom
            )
        }
        insets
    }
}

package com.kozan.utils

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import java.util.Calendar

object AppUtils {



    googleMap?.let {
        val cameraPosition = CameraPosition.Builder()
            .target(googleMap!!.cameraPosition.target)
            .zoom(googleMap!!.cameraPosition.zoom)
            .bearing(location.bearing)
            .build()


        googleMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
            object : GoogleMap.CancelableCallback {
                override fun onFinish() {
                    initialAnimationCompleted = true
                }
                override fun onCancel() {
                    initialAnimationCompleted = true
                }

            })
        addLocationRadarMarkers(currentLocation)
    }

   /* <style name="QzCustomDialog" parent="Theme.MaterialComponents.Light.Dialog">
    <item name="android:windowBackground">@drawable/qz_bg_leave</item>
    <item name="android:windowMinWidthMajor">75%</item>
    <item name="windowMinWidthMinor">75%</item>
    </style>*/


    fun handleBackpress(activity: AppCompatActivity){
    activity.onBackPressedDispatcher.addCallback(activity, object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (navController.currentDestination?.id == R.id.homeFragment) {
                ExitDialog.show(
                    this@MainActivity,
                    this@MainActivity
                )
            } else {
                navController.popBackStack()
            }

        }
    })

    fun setupToolbarWithNavController(activity: AppCompatActivity, navController: NavController, appBarConfiguration: AppBarConfiguration, toolbar: Toolbar){

        activity.setSupportActionBar(toolbar)
        activity.setupActionBarWithNavController(navController, appBarConfiguration)

        toolbar.setupWithNavController(navController, appBarConfiguration)
    }






   /*
   TOOLBAR THEME

   <!--    back button color-->
    <style name="ToolbarTheme" parent="ThemeOverlay.AppCompat.ActionBar">
    <item name="android:textColorPrimary">@android:color/white</item>
    </style>

    <style name="ToolbarTitle">
    <item name="android:textSize">22sp</item>
    <item name="android:fontFamily">@font/base_font_semi_bold</item>
    </style>

    */



/*
  <com.google.android.material.imageview.ShapeableImageView
                android:id="@+id/trafficSignImageV"
                loadCarInfoImage="@{category}"
                viewModel="@{viewModel}"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:adjustViewBounds="true"
                app:shapeAppearanceOverlay="@style/RoundedCornersShape"
                tools:srcCompat="@tools:sample/avatars" />


    <style name="RoundedCornersShape" parent="">
        <item name="cornerFamily">rounded</item>
        <item name="cornerSize">16dp</item>
    </style>

*
* */




    /*
     * dataBinding hardcoded text
     * android:text="@{`Level ` + String.valueOf(level.ordinal()+1)}"
     *  android:text='@{@string/you_re_now_in_s + " "+ context.getString(viewModel.level.textResId) }'
     */




    private fun pxToDp(context: Context, pixels: Int): Int {
        val screenPixelDensity = context.resources.displayMetrics.density
        val dpValue = pixels / screenPixelDensity
        return dpValue.toInt()
    }

    fun dpToPx(context: Context, dp: Int): Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        context.resources.displayMetrics
    ).toInt()




    /**
  fun upDownReverseAnimation() {
      val volumeUpAnimator = ObjectAnimator.ofFloat(player, "volume", 0.0f, 0.2f)
      volumeUpAnimator.duration = player!!.duration.toLong() / 2
      volumeUpAnimator.repeatCount = 1
      volumeUpAnimator.repeatMode = ObjectAnimator.REVERSE
      volumeUpAnimator.start()
  }


    fun getHijrahDate(){
        if (Build.VERSION.SDK_INT >= 26) {  // added Api level 26. below cause crash // java.lang.ClassNotFoundException - Didn't find class "java.time.chrono.HijrahDate" on path: DexPathList[[zip file
            AndroidThreeTen.init(requireContext().applicationContext as Application)
            val hijriDate = HijrahDate.now()
            val formatter =
                java.time.format.DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.getDefault())
            val date2 = hijriDate.format(formatter)
            date2.let { binding!!.imageLayout.hijriDateTextV.text = it }

        }
    }
  */


    fun setAlphaAnimation(view: View) {
       val alphaAnim = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.2f).apply {
            duration = 800
            repeatCount = 5
            startDelay = 1000
            repeatMode = ObjectAnimator.REVERSE
           start()
        }




    }

    private fun paintTextView(textView: TextView) {
        val paint = textView.paint
        val width = paint.measureText(textView.text.toString())
        val textShader: Shader = LinearGradient(0f, 0f, width, textView.textSize, intArrayOf(
            Color.parseColor("#E8F8F8"),
            Color.parseColor("#64E3E3"),
            Color.parseColor("#E064E3")
        ), null, Shader.TileMode.REPEAT)
        textView.paint.setShader(textShader)
    }

    fun setFullScreenActivity(activity: Activity) {
        activity.apply {
            window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.setDecorFitsSystemWindows(false)
            } else {
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                )
            }
        }
    }

    fun changeNavStartDestination(navController : NavController,navGraphResId : Int,destinationId: Int){
        val navGraph = navController.navInflater.inflate(navGraphResId)
        navGraph.setStartDestination(destinationId)
        navController.graph = navGraph

    }

    fun hideStatusBar(activity: Activity){
        WindowCompat.getInsetsController(activity.window, activity.window.decorView)
            .hide(WindowInsetsCompat.Type.statusBars())
    }


    var clickCountForInters = 0
    fun showTwoIntersPerDay(context: Context,proceedToInters:(Boolean)->Unit) {


        val INTERS_COUNT = "inters_count"
        val LAST_INTERS_DATE = "last_inters_date"


        val currentDate = Calendar.getInstance()
        val lastIntersDate = Calendar.getInstance()
        val sharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

        lastIntersDate.timeInMillis = sharedPreferences.getLong(LAST_INTERS_DATE, 0)

        if (currentDate.get(Calendar.DAY_OF_YEAR) != lastIntersDate.get(Calendar.DAY_OF_YEAR)) {
            // new day, reset
            sharedPreferences.edit().apply {
                putLong(LAST_INTERS_DATE, currentDate.timeInMillis)
                putInt(INTERS_COUNT, 0)
                apply()
            }

        }


        clickCountForInters++
        val intersFrequency = 3
        if (clickCountForInters>intersFrequency*2){
            proceedToInters.invoke(false)
            return
        }

        var shownIntersCount = sharedPreferences.getInt(INTERS_COUNT, 0)
        if (shownIntersCount>1){
            proceedToInters.invoke(false)
            return
        }

        if (clickCountForInters  % intersFrequency  == 0) {
            shownIntersCount++
            sharedPreferences.edit().putInt(INTERS_COUNT,shownIntersCount).apply()

            proceedToInters.invoke(true)

        } else  proceedToInters.invoke(true)

    }




}
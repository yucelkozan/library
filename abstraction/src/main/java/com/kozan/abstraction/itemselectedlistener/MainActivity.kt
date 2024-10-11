package com.kozan.abstraction.itemselectedlistener

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kozan.abstraction.R
import com.kozan.utils.ToastUtil

class MainActivity : AppCompatActivity(),OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


    }

    override fun onItemSelected(item: View) {
        ToastUtil.shortToast(this,"Text tıklandı")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
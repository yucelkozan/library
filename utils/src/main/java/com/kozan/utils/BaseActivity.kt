package com.kozan.utils

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController


abstract class BaseActivity<DB : ViewDataBinding>() : AppCompatActivity() {

    class ToolbarConfig(
        val toolbar: Toolbar,
        val appBarConfiguration: AppBarConfiguration,
    )

    lateinit var binding: DB
    var navController: NavController? = null

    abstract fun getLayoutResId(): Int
    abstract fun getFragmentContainerId(): Int?
    abstract fun getToolbarConfig(): ToolbarConfig?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // 35de default olarak true. 35 altı için
        binding = DataBindingUtil.setContentView(this, getLayoutResId())

        getFragmentContainerId()?.let {
            navController = (supportFragmentManager.findFragmentById(it) as NavHostFragment).navController
            getToolbarConfig()?.let {
                setupToolbarWithNavController(it, navController!!)
            }
        }
    }

    private fun AppCompatActivity.setupToolbarWithNavController(
        toolbarConfig: ToolbarConfig,
        navController: NavController
    ) {
        setSupportActionBar(toolbarConfig.toolbar)
        setupActionBarWithNavController(navController, toolbarConfig.appBarConfiguration)
        toolbarConfig.toolbar.setupWithNavController(navController, toolbarConfig.appBarConfiguration)
    }
}


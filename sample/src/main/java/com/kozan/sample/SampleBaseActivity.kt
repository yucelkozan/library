package com.kozan.sample

import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration
import com.kozan.sample.databinding.ActivitySampleBinding
import com.kozan.utils.BaseActivity
import com.kozan.utils.updateMarginsBySystemBars
import com.kozan.utils.updateTopPaddingBySystemBar


class SampleBaseActivity : BaseActivity<ActivitySampleBinding>() {
    override fun getLayoutResId(): Int = R.layout.activity_sample
    override fun getFragmentContainerId(): Int = R.id.fragmentContainerView22

    override fun getToolbarConfig(): ToolbarConfig = ToolbarConfig(
        toolbar = binding.materialToolbar,
        appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.floatingActionButton.updateMarginsBySystemBars()
        binding.materialToolbar.updateTopPaddingBySystemBar()
    }
}
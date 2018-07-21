package com.wololo.hulkify.ui.main

import android.content.Intent
import android.os.Bundle
import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseActivity
import com.wololo.hulkify.databinding.ActivityMainBinding
import com.wololo.hulkify.utils.extensions.hasOreo
import com.wololo.hulkify.utils.service.SensorService

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {
    override fun initViewModel(viewModel: MainActivityViewModel) {
        binding.viewModel = viewModel
    }

    override fun getLayoutRes() = R.layout.activity_main

    override fun getViewModelKey() = MainActivityViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startSensorService()
    }

    private fun startSensorService() {
        val intent = Intent(this, SensorService::class.java)
        if (hasOreo()) {
            startForegroundService(intent)
        } else {
            startService(intent)
        }
    }

}

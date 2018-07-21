package com.wololo.hulkify.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseActivity
import com.wololo.hulkify.databinding.ActivityMainBinding
import com.wololo.hulkify.ui.game.GameActivity
import com.wololo.hulkify.ui.loki.LokiActivity
import com.wololo.hulkify.ui.music.MusicActivity
import com.wololo.hulkify.utils.extensions.hasOreo
import com.wololo.hulkify.utils.service.SensorService
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.View.SYSTEM_UI_FLAG_IMMERSIVE
import android.view.WindowManager


class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    override fun getLayoutRes() = R.layout.activity_main

    override fun getViewModelKey() = MainActivityViewModel::class.java

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        startSensorService()
        binding.rootView.setOnTouchListener { v, event ->
            printCrash(event.x, event.y);true
        }
    }

    override fun initViewModel(viewModel: MainActivityViewModel) {
        binding.viewModel = viewModel
    }


    fun printCrash(x: Float, y: Float) {
        val imageView = ImageView(this)
        imageView.setImageResource(R.drawable.glass)
        val layoutParams = CoordinatorLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(x.toInt() - 64, y.toInt() - 64, 0, 0)
        imageView.layoutParams = layoutParams
        binding.rootView.addView(imageView)
        playSound()
    }

    fun playSound() {
        val player = MediaPlayer.create(this, R.raw.household038);
        player.start()
        startActivity(Intent(this,GameActivity::class.java))
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
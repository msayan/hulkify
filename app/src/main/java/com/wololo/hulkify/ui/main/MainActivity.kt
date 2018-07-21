package com.wololo.hulkify.ui.main

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseActivity
import com.wololo.hulkify.databinding.ActivityMainBinding
import com.wololo.hulkify.ui.music.MusicActivity

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.rootView.setOnTouchListener { v, event ->
            printCrash(event.x, event.y);true
        }
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
        startActivity(Intent(this,MusicActivity::class.java))
    }


    override fun initViewModel(viewModel: MainActivityViewModel) {
        binding.viewModel = viewModel
    }

    override fun getLayoutRes() = R.layout.activity_main

    override fun getViewModelKey() = MainActivityViewModel::class.java
}

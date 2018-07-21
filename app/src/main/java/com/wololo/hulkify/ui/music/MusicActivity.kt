package com.wololo.hulkify.ui.music

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseActivity
import com.wololo.hulkify.databinding.ActivityMusicBinding


class MusicActivity : BaseActivity<MusicViewModel, ActivityMusicBinding>() {

    private lateinit var animator: ObjectAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListeners()

        animator = ObjectAnimator.ofFloat(binding.musicImage, "rotation", 0f, 360f).apply {
            duration = 5000
            interpolator = LinearInterpolator()
            repeatCount = Animation.INFINITE
            start()
        }

        viewModel.executePlayer()
    }

    private fun initListeners() {
        binding.playButton.setOnClickListener { viewModel.executePlayer() }
        binding.imageviewBack.setOnClickListener { finish() }
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_music
    }

    override fun getViewModelKey(): Class<MusicViewModel> {
        return MusicViewModel::class.java
    }

    override fun initViewModel(viewModel: MusicViewModel) {
        viewModel.playListener = object : MusicViewModel.PlayListener {
            override fun onPause() {
                animator.pause()
            }

            override fun onPlay() {
                animator.resume()
            }

        }
        binding.viewModel = viewModel
    }
}
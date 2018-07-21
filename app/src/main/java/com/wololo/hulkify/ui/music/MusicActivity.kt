package com.wololo.hulkify.ui.music

import android.os.Bundle
import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseActivity
import com.wololo.hulkify.databinding.ActivityMusicBinding
import android.view.animation.LinearInterpolator
import android.view.animation.Animation
import android.view.animation.RotateAnimation



class MusicActivity : BaseActivity<MusicViewModel, ActivityMusicBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListeners()

        val rotate = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotate.duration = 10000
        rotate.interpolator = LinearInterpolator()
        rotate.repeatCount = Animation.INFINITE

        binding.musicImage.startAnimation(rotate)

        viewModel.executePlayer()
    }

    private fun initListeners() {
        binding.playButton.setOnClickListener { viewModel.executePlayer() }
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_music
    }

    override fun getViewModelKey(): Class<MusicViewModel> {
        return MusicViewModel::class.java
    }

    override fun initViewModel(viewModel: MusicViewModel) {
        binding.viewModel = viewModel
    }
}
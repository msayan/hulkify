package com.wololo.hulkify.ui.music

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseActivity
import com.wololo.hulkify.databinding.ActivityMusicBinding

class MusicActivity : BaseActivity<MusicViewModel, ActivityMusicBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.playButton.setOnClickListener { playSound() }
    }

    fun playSound() {
        val player = MediaPlayer.create(this, R.raw.household038);
        player.start()
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
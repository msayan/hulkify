package com.wololo.hulkify.ui.music

import android.app.Application
import android.databinding.ObservableField
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.support.v4.content.ContextCompat
import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseViewModel
import com.wololo.hulkify.utils.extensions.emptyString

class MusicViewModel(val app: Application) : BaseViewModel(app) {

    val playingText: ObservableField<String> = ObservableField()
    val remainingTime: ObservableField<String> = ObservableField()
    val imageResource: ObservableField<Drawable> = ObservableField(ContextCompat.getDrawable(app, R.drawable.ic_play_circle_filled_black_24dp)!!)
    lateinit var playListener: PlayListener
    private var timer: CountDownTimer
    private val player = MediaPlayer.create(app, R.raw.black_widow)


    init {
        timer = object : CountDownTimer(player.duration.toLong(), 1000) {
            override fun onFinish() = playLayout()

            override fun onTick(millisUntilFinished: Long) {
                pauseLayout()
                remainingTime.set(remainingTime(player))
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        player.pause()
        player.reset()
        player.release()
        timer.cancel()
    }

    fun executePlayer() {
        if (player.isPlaying) {
            playListener.onPause()
            player.pause()
            timer.cancel()
            playLayout()
            return
        }
        player.seekTo(player.currentPosition)
        player.start()
        pauseLayout()
        timer.start()
        playListener.onPlay()
    }

    fun playLayout() {
        imageResource.set(ContextCompat.getDrawable(app, R.drawable.ic_play_circle_filled_black_24dp))
        playingText.set("")
    }

    fun pauseLayout() {
        imageResource.set(ContextCompat.getDrawable(app, R.drawable.ic_pause_circle_filled_black_24dp))
        playingText.set("Çalıyor..")
    }

    fun remainingTime(player: MediaPlayer): String {
        return if (player.isPlaying) {
            var seconds = (player.duration - player.currentPosition) / 1000.0
            val minutes = (seconds / 60)
            if (seconds > 60) {
                seconds %= 60
            }
            if (seconds < 10)
                "0${minutes.toInt()} : 0${seconds.toInt()}"
            else
                "0${minutes.toInt()} : ${seconds.toInt()}"
        } else
            emptyString()
    }

    interface PlayListener {
        fun onPause()
        fun onPlay()
    }
}
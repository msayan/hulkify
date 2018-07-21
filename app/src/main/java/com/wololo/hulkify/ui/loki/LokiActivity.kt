package com.wololo.hulkify.ui.loki

import android.animation.ObjectAnimator
import android.graphics.Path
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatSeekBar
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import com.wololo.hulkify.R
import java.util.*


class LokiActivity : AppCompatActivity() {

    private lateinit var lokiView: ImageView
    private lateinit var sounds: ArrayList<Int>
    private lateinit var leftPunch: ImageButton
    private lateinit var rightPunch: ImageButton
    private lateinit var seekBar: AppCompatSeekBar
    private lateinit var mp : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loki)

        val startButton = findViewById<Button>(R.id.start)
        leftPunch = findViewById(R.id.left_fist)
        rightPunch = findViewById(R.id.right_fist)
        lokiView = findViewById(R.id.loki_image)
        seekBar = findViewById(R.id.seekbar_game)

        sounds = arrayListOf(R.raw.punch1, R.raw.punch2, R.raw.punch3)

        mp = MediaPlayer.create(this, R.raw.dp_starwars)
        mp.isLooping = true
        mp.start()

        startButton.setOnClickListener { startButton.visibility = View.GONE; setListenerss() }


        Timer().schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread { decreaseProgress() }
            }
        }, 0, 1000)

    }

    private fun decreaseProgress() {
        if (seekBar.progress >= 3)
            seekBar.progress = seekBar.progress - 3
    }


    private fun increaseProgress() {
        if (seekBar.progress < 95)
            seekBar.progress = seekBar.progress + 5
    }

    private fun setListenerss() {
        leftPunch.visibility = View.VISIBLE
        rightPunch.visibility = View.VISIBLE
        leftPunch.setOnClickListener { punchFromLeft() }
        rightPunch.setOnClickListener { punchFromRight() }
    }

    private fun punchFromLeft() {
        val path = Path()
        path.arcTo(0f, 0f, 200f, 1000f, 270f, -180f, true)
        val animator = ObjectAnimator.ofFloat(leftPunch, View.X, View.Y, path)
        animator.duration = 2000
        animator.start()

        Handler().postDelayed({ lokiView.setImageResource(R.drawable.loki2) }, 300)
        lokiView.setImageResource(R.drawable.loki_rightside)

        increaseProgress()
        playSound()
    }

    private fun punchFromRight() {
        val path = Path()
        path.arcTo(0f, 0f, 1500f, 1000f, 270f, -180f, true)
        val animator = ObjectAnimator.ofFloat(rightPunch, View.X, View.Y, path)
        animator.duration = 2000
        animator.start()

        Handler().postDelayed({ lokiView.setImageResource(R.drawable.loki2) }, 300)
        lokiView.setImageResource(R.drawable.loki_leftside)
        increaseProgress()
        playSound()
    }

    override fun onDestroy() {
        super.onDestroy()
        mp.release()
    }

    private fun playSound() {
        val random = Random().nextInt(3)
        val mp = MediaPlayer.create(this, sounds[random])
        mp.start()
    }
}
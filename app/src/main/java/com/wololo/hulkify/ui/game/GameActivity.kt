package com.wololo.hulkify.ui.game

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.support.design.widget.CoordinatorLayout
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.wololo.hulkify.R
import java.util.*


class GameActivity : AppCompatActivity() {

    lateinit var rootView: FrameLayout
    lateinit var remainingTime: TextView
    lateinit var enemiesDewstroyed: TextView

    lateinit var imageView: ImageView

    lateinit var timer: CountDownTimer

    lateinit var player: MediaPlayer
    lateinit var playerBoom: MediaPlayer

    val HULK_PUNCH_SIZE = 128
    val ENEMY_SIZE = 128

    var counter = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        rootView = findViewById(R.id.game_parent_view)
        remainingTime = findViewById(R.id.remaining_time)
        enemiesDewstroyed = findViewById(R.id.enemies_destroyed)

        player = MediaPlayer.create(this, R.raw.household038)
        playerBoom = MediaPlayer.create(this, R.raw.bomb)


        rootView.setOnTouchListener { v, event ->
            printCrash(event.x, event.y);true
        }

        imageView = ImageView(this)

        timer = object : CountDownTimer(20000, 900) {
            override fun onFinish() = rootView.removeAllViews()

            override fun onTick(millisUntilFinished: Long) {
                remainingTime.text = (millisUntilFinished / 1000).toString()
                generateEnemies(Random().nextInt(900).toFloat(), Random().nextInt(900).toFloat())
            }
        }.start()

    }

    private fun generateEnemies(x: Float, y: Float) {
        rootView.removeView(imageView)
        imageView = ImageView(this)
        imageView.setImageResource(R.drawable.redgirl)
        val layoutParams = CoordinatorLayout.LayoutParams(ENEMY_SIZE, ENEMY_SIZE)
        layoutParams.setMargins(x.toInt(), y.toInt(), 0, 0)
        imageView.layoutParams = layoutParams
        imageView.setOnClickListener({ imageView.setImageResource(R.drawable.boom); playBoomSound(); increaseCount(); })
        rootView.addView(imageView)
    }

    private fun increaseCount() {
        counter++
        enemiesDewstroyed.text = counter.toString()
    }

    fun printCrash(x: Float, y: Float) {

        val imageView = ImageView(this)
        imageView.setImageResource(R.drawable.glass)
        val layoutParams = CoordinatorLayout.LayoutParams(HULK_PUNCH_SIZE, HULK_PUNCH_SIZE)
        layoutParams.setMargins(x.toInt(), y.toInt(), 0, 0)
        imageView.layoutParams = layoutParams
        rootView.addView(imageView)
        playSound()
    }

    fun playSound() {
        if (player.isPlaying)
            player.pause()
        player.start()
    }

    fun playBoomSound() {
        if (playerBoom.isPlaying)
            playerBoom.pause()
        playerBoom.start()
    }

}
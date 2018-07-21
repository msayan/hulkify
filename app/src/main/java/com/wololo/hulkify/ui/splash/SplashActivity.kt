package com.wololo.hulkify.ui.splash

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.annotation.RawRes
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import android.widget.ImageView
import com.wololo.hulkify.R
import com.wololo.hulkify.ui.main.MainActivity
import com.wololo.hulkify.utils.extensions.show


private const val DEFAULT_ANIM_DURATION = 1700L

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val wololoImageView = findViewById<ImageView>(R.id.imageview_wololo)
        val hulkImageView = findViewById<ImageView>(R.id.imageview_hulk)

        startAlphaAnimation(wololoImageView, hulkImageView)

        startMoveAnimation(wololoImageView)

        playSound(R.raw.wololo)
    }

    private fun startMoveAnimation(wololoImageView: ImageView?) {
        val marginAnimator = ValueAnimator.ofInt(0, 170).apply {
            duration = DEFAULT_ANIM_DURATION
            addUpdateListener { animation ->
                val value = animation.animatedValue as Int
                wololoImageView?.layoutParams = (wololoImageView?.layoutParams as FrameLayout.LayoutParams).apply {
                    setMargins(0, 0, 0, value)
                }
                wololoImageView?.requestLayout()
            }
        }
        marginAnimator.start()
    }

    private fun startAlphaAnimation(wololoImageView: ImageView?, hulkImageView: ImageView?) {
        val alphaAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = DEFAULT_ANIM_DURATION
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    playSound(R.raw.household038)
                    hulkImageView?.show()
                    Handler(Looper.getMainLooper()).postDelayed({
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    }, 700)


                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }

            })
            addUpdateListener { animation ->
                val animatedValue = animation.animatedValue as Float
                wololoImageView?.alpha = animatedValue
                wololoImageView?.requestLayout()

            }
        }

        alphaAnimator.start()
    }


    private fun playSound(@RawRes resId: Int) {
        val player = MediaPlayer.create(this, resId)
        player.start()
    }
}
package com.wololo.hulkify.ui.gallery

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.*
import com.squareup.picasso.Picasso
import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseActivity
import com.wololo.hulkify.databinding.ActivityGalleryBinding
import com.wololo.hulkify.utils.CircleTransform
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.SwipeDirection


class GalleryActivity : BaseActivity<GalleryViewModel, ActivityGalleryBinding>() {

    val list = mutableListOf<String>()
    var topItem = 0


    override fun getLayoutRes(): Int {
        return R.layout.activity_gallery
    }

    override fun getViewModelKey(): Class<GalleryViewModel> {
        return GalleryViewModel::class.java
    }

    override fun initViewModel(viewModel: GalleryViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

    }

    private fun init() {
        binding.backButton.setOnClickListener {
            finish()
        }

        list.add("https://wallpapersite.com/images/pages/pic_w/813.jpg")
        list.add("https://i.ytimg.com/vi/OT2b5KzMoC0/hqdefault.jpg")
        list.add("https://vignette.wikia.nocookie.net/shipping/images/b/b9/Marvel_-_Avengers_-_Natasha_Romanoff_%28The_Avengers%29.jpg/revision/latest?cb=20131202081631")
        list.add("https://pre00.deviantart.net/de7c/th/pre/i/2018/018/5/a/natasha_romanoff_by_helen_stifler-dc0dhke.png")
        list.add("https://acollectivemind.files.wordpress.com/2015/04/1scarlett-johansson.jpg")

        val adapter = GalleryAdapter(applicationContext)
        adapter.addAll(list)

        binding.stackView.setAdapter(adapter)

        binding.stackView.setCardEventListener(object : CardStackView.CardEventListener {
            override fun onCardDragging(percentX: Float, percentY: Float) {
            }

            override fun onCardSwiped(direction: SwipeDirection?) {
                binding.favorite.isChecked = true
                matched()

            }

            override fun onCardReversed() {
            }

            override fun onCardMovedToOrigin() {
            }

            override fun onCardClicked(index: Int) {
            }

        })

        binding.favorite.setOnClickListener {
            swipeRight()
        }
    }


    private fun changeChecked() {
        Handler().postDelayed({ binding.favorite.isChecked = false }, 100)
    }

    private fun swipeRight() {

        val target = binding.stackView.topView
        val targetOverlay = binding.stackView.topView.overlayContainer

        val rotation = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("rotation", 10f))
        rotation.duration = 200
        val translateX = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationX", 0f, 2000f))
        val translateY = ObjectAnimator.ofPropertyValuesHolder(
                target, PropertyValuesHolder.ofFloat("translationY", 0f, 500f))
        translateX.startDelay = 100
        translateY.startDelay = 100
        translateX.duration = 500
        translateY.duration = 500
        val cardAnimationSet = AnimatorSet()
        cardAnimationSet.playTogether(rotation, translateX, translateY)

        val overlayAnimator = ObjectAnimator.ofFloat(targetOverlay, "alpha", 0f, 1f)
        overlayAnimator.duration = 200
        val overlayAnimationSet = AnimatorSet()
        overlayAnimationSet.playTogether(overlayAnimator)

        binding.stackView.swipe(SwipeDirection.Right, cardAnimationSet, overlayAnimationSet)
    }

    private fun matched() {
        val animator = inFromRightAnimation()
        animator.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                Thread(Runnable {
                    Thread.sleep(2000)
                    runOnUiThread {
                        topItem++
                        binding.matchContainer.clearAnimation()
                        binding.matchContainer.visibility = View.GONE
                        changeChecked()
                    }
                }).start()
            }

            override fun onAnimationStart(animation: Animation?) {

                val animation = fadeInAnimation()
                fadeInAnimation().setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationRepeat(animation: Animation?) {
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        binding.matchedText.clearAnimation()
                        binding.matchedText.visibility = View.VISIBLE
                    }

                    override fun onAnimationStart(animation: Animation?) {

                    }
                })
                binding.matchedText.startAnimation(animation)
            }

        })

        binding.matchContainer.visibility = View.VISIBLE
        binding.natashaImage.startAnimation(animator)
        binding.hulkImage.startAnimation(inFromLeftAnimation())

        Picasso.get().load("https://cdn7.bigcommerce.com/s-ydriczk/products/87194/images/88282/The_Hulk_Marvel_Avengers_Party_Face_Mask_buy_star_masks_at_starstills__65945.1412245326.450.659.jpg?c=2").transform(CircleTransform()).centerCrop().fit().into(binding.hulkImage)

        Picasso.get().load(list.get(topItem % 5)).transform(CircleTransform()).centerCrop().fit().into(binding.natashaImage)
    }

    private fun fadeInAnimation(): Animation {
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.interpolator = DecelerateInterpolator() //add this
        fadeIn.duration = 1000
        return fadeIn
    }

    private fun inFromRightAnimation(): Animation {

        val inFromRight = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f)
        inFromRight.duration = 1000
        inFromRight.interpolator = AccelerateInterpolator()
        return inFromRight
    }

    private fun inFromLeftAnimation(): Animation {
        val inFromLeft = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f)
        inFromLeft.duration = 1000
        inFromLeft.interpolator = AccelerateInterpolator()
        return inFromLeft
    }

}
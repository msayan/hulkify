package com.wololo.hulkify.core

import android.databinding.BindingAdapter
import android.widget.ImageView

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("app:setDrawable")
    fun setDrawable(view: ImageView, drawable: Int) {
        view.setImageResource(drawable)
    }
}
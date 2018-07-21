package com.wololo.hulkify.core

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("app:setDrawable")
    fun setDrawable(view: ImageView, drawable: Int) {
        view.setImageResource(drawable)
    }

    @JvmStatic
    @BindingAdapter("app:setBackgroundColor")
    fun setDrawable(view: View, color: Int) {
        view.setBackgroundColor(color)
    }
}
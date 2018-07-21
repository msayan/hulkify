package com.wololo.hulkify.utils

import android.graphics.Rect
import android.support.annotation.IntRange
import android.support.v7.widget.RecyclerView
import android.view.View

class MarginItemDecoration(@param:IntRange(from = 0) private val margin: Int, @param:IntRange(from = 0) private val columns: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State?) {

        val position = parent.getChildLayoutPosition(view)
        outRect.run {
            right = margin
            bottom = margin
            if (position < columns) {
                top = margin
            }
            if (position % columns == 0) {
                left = margin
            }
        }

    }
}
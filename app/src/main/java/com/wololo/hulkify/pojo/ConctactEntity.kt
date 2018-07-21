package com.wololo.hulkify.pojo

import android.support.annotation.DrawableRes

data class ConctactEntity(@DrawableRes val drawableResId: Int,
                          val name: String,
                          val phone: String)
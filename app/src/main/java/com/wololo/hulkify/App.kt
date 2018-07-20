package com.wololo.hulkify

import com.wololo.hulkify.di.component.DaggerApplicationComponent
import com.wololo.hulkify.di.module.ApplicationModule

class App : android.app.Application() {

    val component by lazy {
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}


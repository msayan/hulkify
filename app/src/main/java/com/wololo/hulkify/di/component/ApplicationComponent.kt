package com.wololo.hulkify.di.component

import android.content.Context
import android.content.SharedPreferences
import com.wololo.hulkify.App
import com.wololo.hulkify.di.module.ApplicationModule
import com.wololo.hulkify.di.module.DatabaseModule
import com.wololo.hulkify.ui.main.MainActivityViewModel

import dagger.Component
import javax.inject.Singleton


@Singleton


@Component(modules = arrayOf(ApplicationModule::class,DatabaseModule::class))

interface ApplicationComponent {
    fun app(): App


    fun context(): Context

    fun preferences(): SharedPreferences

    fun inject(mainActivityViewModel: MainActivityViewModel)
}

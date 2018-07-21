package com.wololo.hulkify.ui.hulk

import android.app.Application
import android.content.Intent
import com.wololo.hulkify.core.BaseViewModel
import com.wololo.hulkify.ui.gallery.GalleryActivity
import com.wololo.hulkify.ui.loki.LokiActivity
import com.wololo.hulkify.ui.music.MusicActivity

class HulkHomeViewModel(app: Application) : BaseViewModel(app) {

    fun findFriend() {
        openActivity(GalleryActivity::class.java)
    }

    fun beatLoki() {
        openActivity(LokiActivity::class.java)
    }

    fun listenMusic() {
        openActivity(MusicActivity::class.java)
    }

    private fun openActivity(activity: Class<*>) {
        getApplication<Application>().startActivity(Intent(getApplication(), activity))
    }

}
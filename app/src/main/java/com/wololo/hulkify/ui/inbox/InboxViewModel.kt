package com.wololo.hulkify.ui.inbox

import android.app.Application
import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseViewModel
import com.wololo.hulkify.pojo.ChatEntity

class InboxViewModel(app:Application) : BaseViewModel(app) {
    fun getChats(): MutableList<ChatEntity> {
        val list = mutableListOf<ChatEntity>()

        list.add(ChatEntity(image = R.drawable.tony,title = "Bruce JAR modülün aktif", detail = "Başın sıkıştığında kontaklarında göreceğin J.A.R.W.I.S’i ara ahbap!",from = "Tony"))
        list.add(ChatEntity(image = R.drawable.thor,title = "Hey Dostum!", detail = "Yeşil arkadaşıma söyle onu özledim :)",from = "Thor"))
        list.add(ChatEntity(image = R.drawable.jarvis,title = "Lokasyonunu tespit  ettim!", detail = "Aldığım sinyallere göre Kolektif House yakınlarındasın!",from = "J.A.R.V.I.S"))

        return list
    }
}
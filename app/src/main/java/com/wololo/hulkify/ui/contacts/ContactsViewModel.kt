package com.wololo.hulkify.ui.contacts

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseViewModel
import com.wololo.hulkify.pojo.ConctactEntity

class ContactsViewModel(application: Application) : BaseViewModel(application) {

    private val contactList = listOf(ConctactEntity(R.drawable.tony, "Tony", "0 532 321 1000"),
            ConctactEntity(R.drawable.thor, "Thor", "0 544 795 5432"),
            ConctactEntity(R.drawable.natasha, "Natasha", "0 544 795 5432"),
            ConctactEntity(R.drawable.jarvis, "J.A.R.V.I.S", "Gizli Numara"),
            ConctactEntity(R.drawable.spiderman, "Spiderman", "0 533 877 2320"),
            ConctactEntity(R.drawable.warmachine, "War machine", "0 530 092 0012"))

    val contactsLiveData = MutableLiveData<List<ConctactEntity>>()

    init {
        contactsLiveData.value = contactList
    }


    fun filterList(newText: String) {
        val filterList = contactList.filter { it.name.toLowerCase().contains(newText.toLowerCase()) }
        contactsLiveData.value = filterList
    }

}
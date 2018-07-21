package com.wololo.hulkify.ui.main

import android.app.Application
import android.content.Intent
import com.wololo.hulkify.App
import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseViewModel
import com.wololo.hulkify.db.AppDatabase
import com.wololo.hulkify.ui.bruce.HomeActivity
import com.wololo.hulkify.ui.hulk.HulkHomeActivity
import javax.inject.Inject

class MainActivityViewModel(app: Application) : BaseViewModel(app) {
    // private var examples: LiveData<List<Example>>? = null

    @Inject
    lateinit var db: AppDatabase

    init {
        (app as? App)?.component?.inject(this)
    }

    fun getAppName() = getApplication<Application>().resources.getString(R.string.app_name)

    /* Example Usage Of LiveData
    fun getExamples(): LiveData<List<Example>> {
        if (examples == null) {
            examples = MutableLiveData<List<Note>>()
            loadExamples()
        }

        return examples!!;
    }

    private fun loadExamples() {
        examples = db.exampleDao().getExamples()
    }
    */

    fun clickHulk() {
        openActivity(HulkHomeActivity::class.java)
    }

    fun clickBruce() {
        openActivity(HomeActivity::class.java)
    }

    private fun openActivity(activity: Class<*>) {
        getApplication<Application>().startActivity(Intent(getApplication(), activity))
    }
}
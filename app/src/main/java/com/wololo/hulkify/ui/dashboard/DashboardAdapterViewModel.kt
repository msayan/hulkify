package com.wololo.hulkify.ui.dashboard

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.wololo.hulkify.pojo.CalendarEntity

class DashboardAdapterViewModel : ViewModel() {

    var item = ObservableField<CalendarEntity>()

}
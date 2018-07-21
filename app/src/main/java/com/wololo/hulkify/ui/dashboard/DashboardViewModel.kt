package com.wololo.hulkify.ui.dashboard

import android.app.Application
import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseViewModel
import com.wololo.hulkify.pojo.CalendarEntity

class DashboardViewModel(app: Application) : BaseViewModel(app) {

    fun getCalendar(): MutableList<CalendarEntity> {
        val list = mutableListOf<CalendarEntity>()

        list.add(CalendarEntity(time = "09:00", isAm = true, title = "Avengers Aylık Değerlendirme Toplantısı", icon = R.drawable.ic_pin_drop_black_24dp, bgColor = R.color.colorAccent, iconText = "Stark Kulesi"))
        list.add(CalendarEntity(time = "11:30", isAm = true, title = "Su Faturasını unutma!", icon = R.drawable.ic_add_alarm_black_24dp, bgColor = R.color.colorAccent, iconText = "Stark Hatırlatıcı"))
        list.add(CalendarEntity(time = "04:30", isAm = false, title = "Natasha ile randevun var!", icon = R.drawable.ic_date_range_black_24dp, bgColor = R.color.colorAccent, iconText = "Takvim"))


        return list
    }
}
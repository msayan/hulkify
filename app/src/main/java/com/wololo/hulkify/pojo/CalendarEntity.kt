package com.wololo.hulkify.pojo

data class CalendarEntity(
        var time: String,
        var isAm: Boolean,
        var title: String,
        var icon: Int,
        var iconText: String,
        var bgColor: Int
) {

    fun getTimeFormat(): String {
        return if (isAm) "AM" else "PM"
    }
}
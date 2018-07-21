package com.wololo.hulkify.ui.dashboard

import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import com.wololo.hulkify.core.BaseAdapter
import com.wololo.hulkify.pojo.CalendarEntity


class DashboardAdapter() : BaseAdapter<CalendarEntity>(object : DiffUtil.ItemCallback<CalendarEntity>() {
    override fun areItemsTheSame(oldItem: CalendarEntity?, newItem: CalendarEntity?): Boolean {
        return oldItem?.title == newItem?.title
    }

    override fun areContentsTheSame(oldItem: CalendarEntity?, newItem: CalendarEntity?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

}) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return null as ViewDataBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
    }
}
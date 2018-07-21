package com.wololo.hulkify.ui.dashboard

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseAdapter
import com.wololo.hulkify.databinding.ItemCalendarBinding
import com.wololo.hulkify.pojo.CalendarEntity


class DashboardAdapter() : BaseAdapter<CalendarEntity>(object : DiffUtil.ItemCallback<CalendarEntity>() {
    override fun areItemsTheSame(oldItem: CalendarEntity?, newItem: CalendarEntity?): Boolean {
        return oldItem?.title == newItem?.title
    }

    override fun areContentsTheSame(oldItem: CalendarEntity?, newItem: CalendarEntity?): Boolean {
        return oldItem?.title == newItem?.title
    }

}) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val viewModel = DashboardAdapterViewModel()
        val binding = DataBindingUtil.inflate<ItemCalendarBinding>(LayoutInflater.from(parent.context), R.layout.item_calendar, parent, false)
        binding.viewModel = viewModel

        return binding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as ItemCalendarBinding).viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()

    }
}
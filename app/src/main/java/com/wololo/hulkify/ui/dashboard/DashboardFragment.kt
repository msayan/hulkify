package com.wololo.hulkify.ui.dashboard

import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseAdapter
import com.wololo.hulkify.core.BaseFragment
import com.wololo.hulkify.databinding.ActivityDashboardBinding
import com.wololo.hulkify.pojo.CalendarEntity

class DashboardFragment : BaseFragment<DashboardViewModel, ActivityDashboardBinding>(DashboardViewModel::class.java) {
    override fun getLayoutRes(): Int {
        return R.layout.activity_dashboard
    }

    override fun init() {
        super.init()

        binding.calendarRecycler.adapter = DashboardAdapter()
        (binding.calendarRecycler.adapter as BaseAdapter<CalendarEntity>).submitList(viewModel.getCalendar())
    }

}
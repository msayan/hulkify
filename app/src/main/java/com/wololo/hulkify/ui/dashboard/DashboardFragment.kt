package com.wololo.hulkify.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseAdapter
import com.wololo.hulkify.core.BaseFragment
import com.wololo.hulkify.databinding.ActivityDashboardBinding
import com.wololo.hulkify.pojo.CalendarEntity

class DashboardFragment : BaseFragment<DashboardViewModel, ActivityDashboardBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.activity_dashboard
    }

    override fun getViewModelKey(): Class<DashboardViewModel> {
        return DashboardViewModel::class.java
    }

    override fun initViewModel(viewModel: DashboardViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        init()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun init() {
        binding.calendarRecycler.adapter = DashboardAdapter()
        (binding.calendarRecycler.adapter as BaseAdapter<CalendarEntity>).submitList(viewModel.getCalendar())
    }

}
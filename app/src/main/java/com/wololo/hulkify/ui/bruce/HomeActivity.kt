package com.wololo.hulkify.ui.bruce

import android.os.Bundle
import android.support.v4.app.Fragment
import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseActivity
import com.wololo.hulkify.databinding.ActivityHomeBinding
import com.wololo.hulkify.ui.dashboard.DashboardFragment

class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.activity_home
    }

    override fun getViewModelKey(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun initViewModel(viewModel: HomeViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {

        binding.navigation.setOnNavigationItemReselectedListener {
            val fragment: Fragment
            when (it.itemId) {
                R.id.home_fragment -> fragment = DashboardFragment()
                R.id.contacts_fragment -> fragment = DashboardFragment() // TODO : contacts fragment
                else -> fragment = DashboardFragment() // TODO : inbox fragment
            }

            supportFragmentManager.beginTransaction().add(R.id.container, fragment, "").commit()
        }

    }

}
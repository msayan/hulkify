package com.wololo.hulkify.ui.bruce

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseActivity
import com.wololo.hulkify.databinding.ActivityHomeBinding
import com.wololo.hulkify.ui.contacts.ContactsFragment
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
        DataBindingUtil.setContentView<ActivityHomeBinding>(this, getLayoutRes())

        init()
    }

    private fun init() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, DashboardFragment()).commit()
        binding.navigation.setOnNavigationItemSelectedListener {
            val fragment: Fragment = when (it.itemId) {
                R.id.home_fragment -> DashboardFragment()
                R.id.contacts_fragment -> ContactsFragment()
                else -> DashboardFragment() // TODO : inbox fragment
            }

            this@HomeActivity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
            return@setOnNavigationItemSelectedListener true
        }

    }

}
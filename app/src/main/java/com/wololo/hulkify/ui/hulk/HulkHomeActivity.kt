package com.wololo.hulkify.ui.hulk

import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseActivity
import com.wololo.hulkify.databinding.ActivityHulkHomeBinding

class HulkHomeActivity : BaseActivity<HulkHomeViewModel, ActivityHulkHomeBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.activity_hulk_home
    }

    override fun getViewModelKey(): Class<HulkHomeViewModel> {
        return HulkHomeViewModel::class.java
    }

    override fun initViewModel(viewModel: HulkHomeViewModel) {
        binding.viewModel = viewModel
    }
}
package com.wololo.hulkify.ui.inbox

import android.os.Bundle
import android.view.View
import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseFragment
import com.wololo.hulkify.databinding.FragmentInboxBinding

class InboxFragment : BaseFragment<InboxViewModel, FragmentInboxBinding>() {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_inbox
    }

    override fun getViewModelKey(): Class<InboxViewModel> {
        return InboxViewModel::class.java
    }

    override fun initViewModel(viewModel: InboxViewModel) {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.chatRecycler.adapter = InboxAdapter()

        (binding.chatRecycler.adapter as InboxAdapter).submitList(viewModel.getChats())
    }


}
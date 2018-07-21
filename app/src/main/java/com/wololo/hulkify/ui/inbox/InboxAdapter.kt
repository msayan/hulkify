package com.wololo.hulkify.ui.inbox

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseAdapter
import com.wololo.hulkify.databinding.ItemChatBinding
import com.wololo.hulkify.pojo.ChatEntity

class InboxAdapter : BaseAdapter<ChatEntity>(object : DiffUtil.ItemCallback<ChatEntity>() {
    override fun areItemsTheSame(oldItem: ChatEntity?, newItem: ChatEntity?): Boolean {
        return oldItem?.title == newItem?.title
    }

    override fun areContentsTheSame(oldItem: ChatEntity?, newItem: ChatEntity?): Boolean {
        return oldItem?.title == newItem?.title
    }

}) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val viewModel = InboxAdapterViewModel()
        val binding = DataBindingUtil.inflate<ItemChatBinding>(LayoutInflater.from(parent.context), R.layout.item_chat, parent, false)
        binding.viewModel = viewModel

        return binding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as ItemChatBinding).viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()
    }
}
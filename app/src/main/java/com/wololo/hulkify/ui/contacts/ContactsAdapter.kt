package com.wololo.hulkify.ui.contacts

import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseAdapter
import com.wololo.hulkify.databinding.ItemContactsBinding
import com.wololo.hulkify.pojo.ConctactEntity
import com.wololo.hulkify.utils.extensions.bindingInflate

class ContactsAdapter(private val clickListener: (ConctactEntity) -> Unit) : BaseAdapter<ConctactEntity>(object : DiffUtil.ItemCallback<ConctactEntity>() {
    override fun areItemsTheSame(oldItem: ConctactEntity?, newItem: ConctactEntity?) = oldItem?.drawableResId == newItem?.drawableResId

    override fun areContentsTheSame(oldItem: ConctactEntity?, newItem: ConctactEntity?) = oldItem == newItem

}) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding = parent.bindingInflate<ItemContactsBinding>(R.layout.item_contacts)

    override fun bind(binding: ViewDataBinding, position: Int) {
        if (binding is ItemContactsBinding) {
            binding.contact = getItem(position)
            binding.root.setOnClickListener { clickListener.invoke(getItem(position)) }
        }
    }
}
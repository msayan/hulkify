package com.wololo.hulkify.ui.contacts


import android.arch.lifecycle.Observer
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.View
import com.wololo.hulkify.R
import com.wololo.hulkify.core.BaseAdapter
import com.wololo.hulkify.core.BaseFragment
import com.wololo.hulkify.databinding.FragmentContactsBinding
import com.wololo.hulkify.pojo.ConctactEntity


class ContactsFragment : BaseFragment<ContactsViewModel, FragmentContactsBinding>() {
    override fun getLayoutRes() = R.layout.fragment_contacts

    override fun getViewModelKey() = ContactsViewModel::class.java

    override fun initViewModel(viewModel: ContactsViewModel) {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setLifecycleOwner(this)
        binding.recyclerviewConctacts.adapter = ContactsAdapter(object : (ConctactEntity) -> Unit {
            override fun invoke(contact: ConctactEntity) {
                val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${contact.phone}"))
                activity?.startActivity(dialIntent)
            }
        })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.filterList(newText) }
                return true
            }

            override fun onQueryTextSubmit(query: String?) = false

        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeContacts()
    }

    private fun subscribeContacts() {
        viewModel.contactsLiveData.observe(this, Observer {
            (binding.recyclerviewConctacts.adapter as? BaseAdapter<ConctactEntity>)?.submitList(it)
        })
    }
}

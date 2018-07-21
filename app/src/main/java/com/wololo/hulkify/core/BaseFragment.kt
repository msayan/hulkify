package com.wololo.hulkify.core

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wololo.hulkify.utils.extensions.bindingInflate

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment() {

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun getViewModelKey(): Class<VM>

    lateinit var binding: DB

    val viewModel by lazy {
        ViewModelProviders.of(this).get(getViewModelKey())
    }

    /**
     * If you want to inject Dependency Injection
     * on your activity, you can override this.
     */
    open fun onInject() {}

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        onInject()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = container?.bindingInflate<DB>(getLayoutRes())!!
        initViewModel(viewModel)
        return binding.root
    }

    abstract fun initViewModel(viewModel: VM)
}

package com.technicaltest.stubhub.core.ui

import androidx.fragment.app.Fragment

abstract class BaseFragment<T : Any> : Fragment() {

    private var _binding: T? = null
    protected val binding: T
        get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun setBinding(binding: T) {
        _binding = binding
    }

}
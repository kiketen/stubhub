package com.technicaltest.stubhub.login.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.technicaltest.stubhub.core.ui.BaseFragment
import com.technicaltest.stubhub.login.databinding.LoginFragmentBinding

class LoginFragment : BaseFragment<LoginFragmentBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setBinding(LoginFragmentBinding.inflate(inflater, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModelObservers()
    }

    private fun setViewModelObservers() {
        with(viewLifecycleOwner) {

        }
    }

}



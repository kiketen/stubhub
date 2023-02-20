package com.technicaltest.stubhub.login.presentation.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.internal.TextWatcherAdapter
import com.technicaltest.stubhub.core.extensions.observe
import com.technicaltest.stubhub.core.extensions.switchVisibility
import com.technicaltest.stubhub.login.databinding.CharactersFragmentBinding
import com.technicaltest.stubhub.login.presentation.presentation.adapter.CharactersAdapter
import com.technicaltest.stubhub.login.presentation.presentation.adapter.CharactersAdapterListener
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("RestrictedApi")
@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private lateinit var binding: CharactersFragmentBinding
    private val viewModel: CharactersViewModel by viewModels()

    private lateinit var adapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharactersFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModelObservers()
        setLayout()
    }

    private fun setViewModelObservers() {
        observe(viewModel.characters) {
            adapter.update(it.characters)
            adapter.setPaginationInfo(it.total)
        }
        observe(viewModel.loading) {
            binding.progress.switchVisibility(it)
        }
    }

    private fun setLayout() {
        adapter = CharactersAdapter(
            characters = mutableListOf(),
            listener = object : CharactersAdapterListener {
                override fun onCharacterClick(id: String) {
                    viewModel.onCharacterClick(id)
                }

                override fun onLoadPage(itemsCount: Int) {
                    viewModel.onLoadMoreCharacters(itemsCount)
                }

            }
        )
        with(binding) {
            recycler.adapter = adapter
            searchCharacterField.addTextChangedListener(object : TextWatcherAdapter() {
                override fun afterTextChanged(s: Editable) {
                    viewModel.onSearchTextUpdated(s.toString())
                }
            })
        }
    }


}



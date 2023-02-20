package com.technicaltest.stubhub.login.presentation.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.technicaltest.stubhub.core.extensions.observe
import com.technicaltest.stubhub.core.extensions.switchVisibility
import com.technicaltest.stubhub.login.databinding.CharacterFragmentBinding
import com.technicaltest.stubhub.login.presentation.presentation.adapter.CharactersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment : Fragment() {

    private lateinit var binding: CharacterFragmentBinding
    private val viewModel: CharacterViewModel by viewModels()

    private lateinit var adapter: CharactersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModelObservers()
        setLayout()
    }

    private fun setViewModelObservers() {
        observe(viewModel.state) {
            binding.progress.switchVisibility(it.loading)
            adapter.add(it.characters)
        }
    }

    private fun setLayout() {
        adapter = CharactersAdapter(
            context = requireContext(),
            characters = mutableListOf(),
            onCharacterClick = {
                viewModel.onCharacterClick(it)
            }
        )
        with(binding) {
            recycler.adapter = adapter
        }
    }


}



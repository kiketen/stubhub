package com.technicaltest.details.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.technicaltest.details.databinding.CharacterDetailsFragmentBinding
import com.technicaltest.stubhub.core.extensions.observe
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("RestrictedApi")
@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    private lateinit var binding: CharacterDetailsFragmentBinding
    private val viewModel: CharacterDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModelObservers()
    }

    private fun setViewModelObservers() {
        observe(viewModel.characterDetails) {
            binding.characterDetailsName.text = it.name
            Glide.with(this).load(it.image).into(binding.characterDetailsImage)
        }
        observe(viewModel.loadingComics) {
            binding.characterComics.showLoading(it)
        }
        observe(viewModel.loadingSeries) {
            binding.characterSeries.showLoading(it)
        }
        observe(viewModel.loadingEvents) {
            binding.characterEvents.showLoading(it)
        }
        observe(viewModel.comics) {
            binding.characterComics.showApparitions(it)
        }
        observe(viewModel.series) {
            binding.characterSeries.showApparitions(it)
        }
        observe(viewModel.events) {
            binding.characterEvents.showApparitions(it)
        }
    }
}



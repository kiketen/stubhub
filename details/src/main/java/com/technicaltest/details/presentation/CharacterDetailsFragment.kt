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
        setLayout()
    }

    private fun setViewModelObservers() {
        observe(viewModel.characterDetails) {
            binding.characterDetailsName.text = it.name
            Glide.with(this).load(it.image).into(binding.characterDetailsImage)
        }
    }

    private fun setLayout() {
    }


}



package com.technicaltest.details.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.technicaltest.details.databinding.CharacterApparitionItemBinding
import com.technicaltest.details.domain.CharacterApparition
import com.technicaltest.stubhub.core.presentation.BaseAdapter

class CharacterApparitionAdapter(
    private val apparitions: MutableList<CharacterApparition>
) : BaseAdapter<CharacterApparitionAdapter.ViewHolder, CharacterApparition>(apparitions) {

    inner class ViewHolder(binding: CharacterApparitionItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val name = binding.characterName
        val image = binding.characterImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CharacterApparitionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val apparition = apparitions[position]
        with(holder) {
            name.text = apparition.title
            Glide.with(image).load(apparition.image).into(image)
        }
    }
}
package com.technicaltest.stubhub.login.presentation.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.technicaltest.stubhub.core.presentation.BaseAdapter
import com.technicaltest.stubhub.core.presentation.setSensitiveClickListener
import com.technicaltest.stubhub.login.databinding.CharacterItemBinding
import com.technicaltest.stubhub.login.presentation.domain.MarvelCharacter

class CharactersAdapter(
    val context: Context,
    val characters: MutableList<MarvelCharacter>,
    val onCharacterClick: (String) -> Unit
) : BaseAdapter<CharactersAdapter.ViewHolder, MarvelCharacter>(characters) {

    inner class ViewHolder(binding: CharacterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val name = binding.characterName
        val image = binding.characterImage
        val root = binding.characterRoot
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        with(holder) {
            name.text = character.name
            Glide.with(context).load(character.image).into(image)
            root.setSensitiveClickListener {
                onCharacterClick(character.id)
            }
        }
    }

}
package com.technicaltest.stubhub.login.presentation.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.technicaltest.stubhub.core.presentation.PaginationAdapter
import com.technicaltest.stubhub.core.presentation.setSensitiveClickListener
import com.technicaltest.stubhub.login.databinding.CharacterItemBinding
import com.technicaltest.stubhub.login.presentation.domain.MarvelCharacter

class CharactersAdapter(
    private val characters: MutableList<MarvelCharacter>,
    private val listener: CharactersAdapterListener
) : PaginationAdapter<CharactersAdapter.ViewHolder, MarvelCharacter>(characters, onLoadPage = {
    listener.onLoadPage(it)
}) {

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
        super.onBindViewHolder(holder, position)
        val character = characters[position]
        with(holder) {
            name.text = character.name
            Glide.with(image).load(character.image).into(image)
            root.setSensitiveClickListener {
                listener.onCharacterClick(character.id)
            }
        }
    }
}
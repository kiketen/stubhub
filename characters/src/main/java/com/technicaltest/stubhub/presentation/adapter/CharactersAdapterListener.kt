package com.technicaltest.stubhub.presentation.adapter

import com.technicaltest.stubhub.domain.MarvelCharacter


interface CharactersAdapterListener {
    fun onCharacterClick(marvelCharacter: MarvelCharacter)
    fun onLoadPage(itemsCount: Int)
}
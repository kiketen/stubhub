package com.technicaltest.stubhub.presentation.adapter


interface CharactersAdapterListener {
    fun onCharacterClick(id: String)
    fun onLoadPage(itemsCount: Int)
}
package com.technicaltest.stubhub

import com.technicaltest.stubhub.domain.MarvelCharacters
import com.technicaltest.stubhub.presentation.CharactersError
import com.technicaltest.stubhub.presentation.CharactersViewModel
import org.junit.Assert.assertEquals


class CharactersViewModelAsserter(
    private val viewModel: CharactersViewModel
) {
    fun charactersLoaded(marvelCharacters: MarvelCharacters) {
        assertEquals(viewModel.characters.value!!.characters, marvelCharacters.characters)
        assertEquals(viewModel.characters.value!!.total, marvelCharacters.total)
        loadingVisibility(false)
    }

    fun loadingVisibility(isVisible: Boolean) {
        assertEquals(viewModel.loading.value, isVisible)
    }

    fun charactersEmptyError() {
        assert(viewModel.error.value is CharactersError.CharactersNotFound)
    }

    fun unknownError() {
        assert(viewModel.error.value is CharactersError.Unknown)
    }
}
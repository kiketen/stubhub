package com.technicaltest.stubhub.presentation

import com.technicaltest.stubhub.domain.MarvelCharacter


sealed class CharactersNavigation {
    class Details(val marvelCharacter: MarvelCharacter) : CharactersNavigation()
}

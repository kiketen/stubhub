package com.technicaltest.stubhub.login.presentation.presentation

import com.technicaltest.stubhub.login.presentation.domain.MarvelCharacter


data class CharacterState(
    val characters: List<MarvelCharacter> = listOf(),
    val loading: Boolean = false
)

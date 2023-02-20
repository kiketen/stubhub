package com.technicaltest.stubhub.login.presentation.domain


data class MarvelCharacters(
    val total: Int,
    val characters: List<MarvelCharacter>
)
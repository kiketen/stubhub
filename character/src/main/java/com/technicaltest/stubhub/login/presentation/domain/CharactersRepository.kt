package com.technicaltest.stubhub.login.presentation.domain

import io.reactivex.Observable


interface CharactersRepository {
    fun getCharacters(name: String): Observable<List<MarvelCharacter>>
}
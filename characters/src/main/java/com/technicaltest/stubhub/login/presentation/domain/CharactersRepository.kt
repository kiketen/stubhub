package com.technicaltest.stubhub.login.presentation.domain

import io.reactivex.Observable


interface CharactersRepository {
    fun getCharacters(name: String, offset: Int): Observable<MarvelCharacters>
}
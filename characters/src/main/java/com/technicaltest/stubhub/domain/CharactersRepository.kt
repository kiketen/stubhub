package com.technicaltest.stubhub.domain

import io.reactivex.Observable


interface CharactersRepository {
    fun getCharacters(name: String, offset: Int): Observable<MarvelCharacters>
}
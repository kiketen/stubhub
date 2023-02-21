package com.technicaltest.details.domain

import io.reactivex.Observable


interface CharacterDetailsRepository {
    fun getComics(id: String): Observable<List<CharacterApparition>>
    fun getSeries(id: String): Observable<List<CharacterApparition>>
    fun getEvents(id: String): Observable<List<CharacterApparition>>
}
package com.technicaltest.details.data

import com.technicaltest.details.domain.CharacterApparition
import com.technicaltest.details.domain.CharacterDetailsRepository
import io.reactivex.Observable


class CharacterDetailsRepositoryImpl(
    private val characterDetailsApi: CharacterDetailsApi
) : CharacterDetailsRepository {

    override fun getComics(id: String): Observable<List<CharacterApparition>> {
        return characterDetailsApi.getComics(id).map { it.data.results.map { it.toDomain() } }
    }

    override fun getSeries(id: String): Observable<List<CharacterApparition>> {
        return characterDetailsApi.getSeries(id).map { it.data.results.map { it.toDomain() } }
    }

    override fun getEvents(id: String): Observable<List<CharacterApparition>> {
        return characterDetailsApi.getEvents(id).map { it.data.results.map { it.toDomain() } }
    }
}
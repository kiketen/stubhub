package com.technicaltest.stubhub.login.presentation.data

import com.technicaltest.stubhub.login.presentation.domain.CharactersRepository
import com.technicaltest.stubhub.login.presentation.domain.MarvelCharacter
import io.reactivex.Observable


class CharacterRepositoryImpl(
    private val charactersApi: CharactersApi
) : CharactersRepository {

    override fun getCharacters(name: String): Observable<List<MarvelCharacter>> {
        return if (name.isBlank()) {
            charactersApi.getCharacters().map { response -> response.data.results.map { it.toDomain() } }
        } else {
            charactersApi.getCharacters(name).map { response -> response.data.results.map { it.toDomain() } }
        }

    }

}
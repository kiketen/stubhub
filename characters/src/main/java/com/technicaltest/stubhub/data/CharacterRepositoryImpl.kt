package com.technicaltest.stubhub.data

import com.technicaltest.stubhub.domain.CharactersRepository
import com.technicaltest.stubhub.domain.MarvelCharacters
import io.reactivex.Observable


class CharacterRepositoryImpl(
    private val charactersApi: CharactersApi
) : CharactersRepository {

    override fun getCharacters(name: String, offset: Int): Observable<MarvelCharacters> {
        return if (name.isBlank()) {
            charactersApi.getCharacters(offset).map {
                MarvelCharacters(
                    total = it.data.total,
                    characters = it.data.results.map { character -> character.toDomain() }
                )
            }
        } else {
            charactersApi.getCharacters(name, offset).map {
                MarvelCharacters(
                    total = it.data.total,
                    characters = it.data.results.map { character -> character.toDomain() }
                )
            }
        }
    }

}
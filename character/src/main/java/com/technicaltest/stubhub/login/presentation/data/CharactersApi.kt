package com.technicaltest.stubhub.login.presentation.data

import com.technicaltest.stubhub.core.data.ApiResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {
    @GET("/v1/public/characters")
    fun getCharacters(
        @Query("nameStartsWith") name: String
    ): Observable<ApiResponse<CharacterApiModel>>

    @GET("/v1/public/characters")
    fun getCharacters(): Observable<ApiResponse<CharacterApiModel>>
}
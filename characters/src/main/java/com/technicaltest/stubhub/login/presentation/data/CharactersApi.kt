package com.technicaltest.stubhub.login.presentation.data

import com.technicaltest.stubhub.core.data.ApiResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {
    @GET("/v1/public/characters")
    fun getCharacters(
        @Query("nameStartsWith") name: String,
        @Query("offset") offset: Int
    ): Observable<ApiResponse<CharacterApiModel>>

    @GET("/v1/public/characters")
    fun getCharacters(
        @Query("offset") offset: Int
    ): Observable<ApiResponse<CharacterApiModel>>
}
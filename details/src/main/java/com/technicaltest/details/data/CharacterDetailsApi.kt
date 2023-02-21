package com.technicaltest.details.data

import com.technicaltest.stubhub.core.data.ApiResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterDetailsApi {
    @GET("/v1/public/characters/{characterId}/comics")
    fun getComics(@Path("characterId") characterId: String): Observable<ApiResponse<CharacterApparitionApiModel>>

    @GET("/v1/public/characters/{characterId}/comics")
    fun getSeries(@Path("characterId") characterId: String): Observable<ApiResponse<CharacterApparitionApiModel>>

    @GET("/v1/public/characters/{characterId}/comics")
    fun getEvents(@Path("characterId") characterId: String): Observable<ApiResponse<CharacterApparitionApiModel>>
}
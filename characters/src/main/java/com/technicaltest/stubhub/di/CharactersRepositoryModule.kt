package com.technicaltest.stubhub.di

import com.technicaltest.stubhub.data.CharacterRepositoryImpl
import com.technicaltest.stubhub.data.CharactersApi
import com.technicaltest.stubhub.domain.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object CharactersRepositoryModule {

    @Provides
    fun providesCharactersApi(
        retrofit: Retrofit
    ): CharactersApi = retrofit.create(CharactersApi::class.java)

    @Provides
    fun providesCharactersRepository(
        charactersApi: CharactersApi
    ): CharactersRepository = CharacterRepositoryImpl(charactersApi)
}
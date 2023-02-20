package com.technicaltest.stubhub.login.presentation.di

import com.technicaltest.stubhub.login.presentation.data.CharacterRepositoryImpl
import com.technicaltest.stubhub.login.presentation.data.CharactersApi
import com.technicaltest.stubhub.login.presentation.domain.CharactersRepository
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
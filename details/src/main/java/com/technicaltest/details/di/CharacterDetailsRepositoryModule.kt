package com.technicaltest.details.di

import com.technicaltest.details.data.CharacterDetailsApi
import com.technicaltest.details.data.CharacterDetailsRepositoryImpl
import com.technicaltest.details.domain.CharacterDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object CharacterDetailsRepositoryModule {

    @Provides
    fun providesCharacterDetailsApi(
        retrofit: Retrofit
    ): CharacterDetailsApi = retrofit.create(CharacterDetailsApi::class.java)

    @Provides
    fun providesCharactersRepository(
        characterDetailsApi: CharacterDetailsApi
    ): CharacterDetailsRepository = CharacterDetailsRepositoryImpl(characterDetailsApi)
}
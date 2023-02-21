package com.technicaltest.stubhub.core.di

import com.technicaltest.stubhub.core.presentation.DefaultThreadScheduler
import com.technicaltest.stubhub.core.presentation.ThreadScheduler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    fun providesThreadScheduler(): ThreadScheduler {
        return DefaultThreadScheduler()
    }

}
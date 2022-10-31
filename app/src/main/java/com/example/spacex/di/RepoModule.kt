package com.example.spacex.di

import com.example.spacex.network.LaunchRepository
import com.example.spacex.network.LaunchRepositoryImpl
import com.example.spacex.network.RetroAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepoModule {

    @Singleton
    @Provides
    fun providesLaunchRepository(retroAPI:RetroAPIService): LaunchRepository{
        return LaunchRepositoryImpl(retroAPI)
    }
}
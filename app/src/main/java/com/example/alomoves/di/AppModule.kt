package com.example.alomoves.di

import com.example.data.remote.MockApi
import com.example.data.repository.Repository
import com.example.data.repository.RepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(api: MockApi): Repository =
        RepositoryImp(api)

}
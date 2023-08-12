package com.example.alomoves.di

import android.content.Context
import com.example.data.remote.MockApi
import com.example.data.remote.MockApiImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideMockApi(@ApplicationContext context: Context): MockApi =MockApiImp(context = context)

}
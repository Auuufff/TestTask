package com.example.testtask.common.di

import com.example.testtask.data.DataRepositoryImpl
import com.example.testtask.data.LocationRepositoryImpl
import com.example.testtask.domain.repository.DataRepository
import com.example.testtask.domain.repository.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRepository(impl: DataRepositoryImpl): DataRepository

    @Binds
    @Reusable
    fun bindLocationRepository(impl: LocationRepositoryImpl): LocationRepository
}
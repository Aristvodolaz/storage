package com.komus.storage.di

import com.komus.storage.data.remote.ApiService
import com.komus.storage.data.repository.AuthRepository
import com.komus.storage.data.repository.PrunitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(apiService: ApiService): AuthRepository {
        return AuthRepository(apiService)
    }

    @Provides
    @Singleton
    fun providePrunitRepository(apiService: ApiService): PrunitRepository {
        return PrunitRepository(apiService)
    }
}

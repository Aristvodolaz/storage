package com.komus.storage.di

import com.komus.storage.data.repository.ArticleRepository
import com.komus.storage.data.repository.AuthRepository
import com.komus.storage.data.repository.PrunitRepository
import com.komus.storage.data.usecase.ArticleUseCase
import com.komus.storage.data.usecase.AuthenticateUseCase
import com.komus.storage.data.usecase.PrunitUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideAuthenticateUseCase(authRepository: AuthRepository): AuthenticateUseCase {
        return AuthenticateUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun providePrunitUseCase(prunitRepository: PrunitRepository): PrunitUseCase {
        return PrunitUseCase(prunitRepository)
    }

    @Provides
    @Singleton
    fun provideArticleUseCase(articleRepository: ArticleRepository): ArticleUseCase {
        return ArticleUseCase(articleRepository)
    }
}

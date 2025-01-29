package com.komus.storage.di

import android.content.Context
import androidx.room.Room
import com.komus.storage.data.local.AppDatabase
import com.komus.storage.data.local.dao.InformationDao
import com.komus.storage.data.repository.InformationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "storage_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideInformationDao(database: AppDatabase): InformationDao {
        return database.informationDao()
    }

    @Provides
    @Singleton
    fun provideInformationRepository(informationDao: InformationDao): InformationRepository {
        return InformationRepository(informationDao)
    }
}

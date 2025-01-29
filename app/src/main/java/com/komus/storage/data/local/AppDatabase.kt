package com.komus.storage.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.komus.storage.data.local.dao.InformationDao
import com.komus.storage.data.local.model.InformationEntity

@Database(entities = [InformationEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun informationDao(): InformationDao
}

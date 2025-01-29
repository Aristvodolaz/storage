package com.komus.storage.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.komus.storage.data.local.model.InformationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InformationDao {
    @Insert
    suspend fun insertInformation(information: InformationEntity)

    @Query("SELECT * FROM information WHERE id = :id")
    suspend fun getInformationById(id: Int): InformationEntity?

    @Query("SELECT * FROM information")
    fun getAllInformation(): Flow<List<InformationEntity>>

}

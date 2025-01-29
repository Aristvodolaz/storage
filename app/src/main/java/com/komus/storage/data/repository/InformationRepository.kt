package com.komus.storage.data.repository

import com.komus.storage.data.local.dao.InformationDao
import com.komus.storage.data.local.model.InformationEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InformationRepository @Inject constructor(
    private val informationDao: InformationDao
) {
    suspend fun insertInformation(info: InformationEntity) {
        informationDao.insertInformation(info)
    }

    suspend fun getInformationById(id: Int): InformationEntity? {
        return informationDao.getInformationById(id)
    }

    fun getAllInformation(): Flow<List<InformationEntity>> {
        return informationDao.getAllInformation() 
    }
}

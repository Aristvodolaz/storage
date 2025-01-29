package com.komus.storage.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "information")
data class InformationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val article: String,
    val shk: String,
    val productQnt: Int,
    val prunitName: String,
    val prunitId: Int,
    val wrShk: String,
    val idScklad: Int,
    val startExpirationDate: String?,
    val endExpirationDate: String?,
    val executor: String?,
    val placeQnt: Int,
    val dateAdd: String
)

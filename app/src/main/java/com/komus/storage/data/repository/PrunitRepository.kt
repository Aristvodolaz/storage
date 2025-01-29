package com.komus.storage.data.repository

import com.komus.storage.data.model.AuthResponse
import com.komus.storage.data.model.ProductResponse
import com.komus.storage.data.remote.ApiService
import javax.inject.Inject

class PrunitRepository @Inject constructor(
    private val prunitService: ApiService
) {
    suspend fun getPrunitProduct(product: String): ProductResponse {
        return prunitService.getPrunit(product)
    }
}
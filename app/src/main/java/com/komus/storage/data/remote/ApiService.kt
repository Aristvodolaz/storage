package com.komus.storage.data.remote

import com.komus.storage.data.model.AuthResponse
import com.komus.storage.data.model.ProductResponse
import com.komus.storage.utils.Const
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/auth")
    suspend fun getEmployeeDetails(@Query("id") id: String): AuthResponse

    @GET(Const.SEARCH_PRUNIT)
    suspend fun getPrunit(@Query("productId") productId: String): ProductResponse
}
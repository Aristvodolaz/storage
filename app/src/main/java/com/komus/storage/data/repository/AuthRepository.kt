package com.komus.storage.data.repository

import com.komus.storage.data.model.AuthResponse
import com.komus.storage.data.remote.ApiService

import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authService: ApiService
) {
    suspend fun getEmployeeById(id: String): AuthResponse {
        return authService.getEmployeeDetails(id)
    }
}

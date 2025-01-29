package com.komus.storage.data.usecase
import com.komus.storage.data.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.komus.storage.data.domain.Result
import com.komus.storage.data.model.AuthResponse

class AuthenticateUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(barcode: String): Result<AuthResponse.Value> = withContext(Dispatchers.IO) {
        if (barcode.isBlank()) {
            return@withContext Result.Error("Штрих-код не может быть пустым")
        }

        try {
            val response = authRepository.getEmployeeById(barcode)
            val user = response.value?.firstOrNull()

            if (user != null) {
                return@withContext Result.Success(user)
            } else {
                return@withContext Result.Error("Пользователь не найден")
            }
        } catch (e: Exception) {
            return@withContext Result.Error(e.localizedMessage ?: "Неизвестная ошибка")
        }
    }
}


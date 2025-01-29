package com.komus.storage.data.usecase

import com.komus.storage.data.domain.Result
import com.komus.storage.data.model.ProductResponse
import com.komus.storage.data.repository.PrunitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PrunitUseCase @Inject constructor(
    private val prunitRepository: PrunitRepository
) {
    suspend operator fun invoke(articul: String): Result<ProductResponse.ProductData> = withContext(
        Dispatchers.IO) {
        if (articul.isBlank()) {
            return@withContext Result.Error("Артикул не может быть пустым")
        }

        try {
            val response = prunitRepository.getPrunitProduct(articul)
            val value = response.value?.firstOrNull()

            if (value != null) {
                return@withContext Result.Success(value)
            } else {
                return@withContext Result.Error("Данные не найдены")
            }
        } catch (e: Exception) {
            return@withContext Result.Error(e.localizedMessage ?: "Неизвестная ошибка")
        }
    }
}


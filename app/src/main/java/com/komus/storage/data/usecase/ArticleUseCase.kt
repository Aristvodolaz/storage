package com.komus.storage.data.usecase

import com.komus.storage.data.domain.Result
import com.komus.storage.data.model.ArticleResponse
import com.komus.storage.data.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArticleUseCase @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    suspend operator fun invoke(
        shk: String? = null,
        article: String? = null
    ): Result<ArticleResponse.ArticleData> = withContext(Dispatchers.IO) {
        if (shk.isNullOrBlank() && article.isNullOrBlank()) {
            return@withContext Result.Error("Штрих-код или артикул должны быть указаны")
        }

        try {
            val response = when {
                !shk.isNullOrBlank() -> articleRepository.getInfoBySHK(shk)
                !article.isNullOrBlank() -> articleRepository.getInfoByArticle(article)
                else -> return@withContext Result.Error("Данные для поиска не найдены")
            }

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

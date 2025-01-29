package com.komus.storage.data.repository

import com.komus.storage.data.model.ArticleResponse
import com.komus.storage.data.remote.ApiService
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val articleService: ApiService
) {
    suspend fun getInfoBySHK(shk: String): ArticleResponse {
        return articleService.getInfoBySHK(shk)
    }

    suspend fun getInfoByArticle(article: String): ArticleResponse {
        return articleService.getInfoByArticle(article)
    }
}
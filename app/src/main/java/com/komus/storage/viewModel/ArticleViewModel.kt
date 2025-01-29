package com.komus.storage.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.komus.storage.data.domain.Result
import com.komus.storage.data.model.ArticleResponse
import com.komus.storage.data.usecase.ArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val articleUseCase: ArticleUseCase
) : ViewModel() {

    sealed class ArticleState {
        object Idle : ArticleState()
        object Loading : ArticleState()
        data class Success(val data: ArticleResponse.ArticleData) : ArticleState()
        data class Error(val error: String) : ArticleState()
    }

    private val _articleStatus = MutableStateFlow<ArticleState>(ArticleState.Idle)
    val articleStatus: StateFlow<ArticleState> get() = _articleStatus

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> get() = _loading

    fun getInfoByShkOrArticle(shk: String? = null, article: String? = null) {
        viewModelScope.launch {
            _articleStatus.value = ArticleState.Loading
            _loading.postValue(true)

            val result = when {
                !shk.isNullOrBlank() -> articleUseCase(shk = shk)
                !article.isNullOrBlank() -> articleUseCase(article = article)
                else -> Result.Error("Штрих-код или артикул должны быть указаны")
            }

            when (result) {
                is Result.Success -> {
                    val data = result.data
                    _articleStatus.value = ArticleState.Success(data)
                }
                is Result.Error -> {
                    _articleStatus.value = ArticleState.Error(result.message)
                    Log.e("ArticleViewModel", "Error: ${result.message}")
                }
            }

            _loading.postValue(false)
        }
    }
}

package com.komus.storage.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.komus.storage.data.domain.Result
import com.komus.storage.data.model.ProductResponse
import com.komus.storage.data.usecase.PrunitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrunitViewModel  @Inject constructor(
    private val prunitUseCase: PrunitUseCase
) : ViewModel() {

    sealed class PrunitState {
        object Idle : PrunitState()
        object Loading : PrunitState()
        data class Success(val user: ProductResponse.ProductData) : PrunitState()
        data class Error(val error: String) : PrunitState()
    }

    private val _prunitStatus = MutableStateFlow<PrunitState>(PrunitState.Idle)
    val prunitStatus: StateFlow<PrunitState> get() = _prunitStatus

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> get() = _loading

    fun getPrunit(articul: String) {
        viewModelScope.launch {
            _prunitStatus.value = PrunitState.Loading
            _loading.postValue(true)

            when (val result = prunitUseCase(articul)) {
                is Result.Success -> {
                    val user = result.data
                    _prunitStatus.value = PrunitState.Success(user)
                }
                is Result.Error -> {
                    _prunitStatus.value = PrunitState.Error(result.message)
                    Log.e("PrunitViewModel", "Prunit error: ${result.message}")
                }
            }

            _loading.postValue(false)
        }
    }
}

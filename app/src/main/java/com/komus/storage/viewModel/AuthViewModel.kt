package com.komus.storage.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.komus.storage.data.model.AuthResponse
import com.komus.storage.data.usecase.AuthenticateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.komus.storage.data.domain.Result

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authenticateUseCase: AuthenticateUseCase
) : ViewModel() {

    sealed class AuthState {
        object Idle : AuthState()
        object Loading : AuthState()
        data class Success(val user: AuthResponse.Value) : AuthState()
        data class Error(val error: String) : AuthState()
    }

    private val _authStatus = MutableStateFlow<AuthState>(AuthState.Idle)
    val authStatus: StateFlow<AuthState> get() = _authStatus

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> get() = _loading

    fun authenticate(barcode: String) {
        viewModelScope.launch {
            _authStatus.value = AuthState.Loading
            _loading.postValue(true)

            val trimmedBarcode = barcode.substring(1, barcode.length - 1)
            when (val result = authenticateUseCase(trimmedBarcode)) {
                is Result.Success -> {
                    val user = result.data
                    _authStatus.value = AuthState.Success(user)
                }
                is Result.Error -> {
                    _authStatus.value = AuthState.Error(result.message)
                    Log.e("AuthViewModel", "Authentication error: ${result.message}")
                }
            }

            _loading.postValue(false)
        }
    }
}

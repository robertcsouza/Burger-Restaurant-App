package com.example.burgerrestaurantapp.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.burgerrestaurantapp.core.data.domain.CurstomerRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val customerRepository: CurstomerRepository,
    auth: FirebaseAuth
) : ViewModel() {

    private val _uiEvent = MutableStateFlow<AuthUiEvent>(value = AuthUiEvent.Idle)

    val uiEvent: StateFlow<AuthUiEvent> = _uiEvent

    fun startLoading() {
        _uiEvent.value = AuthUiEvent.Loading
    }

    fun consumeEvent() {
        _uiEvent.value = AuthUiEvent.Idle
    }

    fun emitError(message: String) {
        _uiEvent.value = AuthUiEvent.Error(message)
    }

    fun onFirebaseUserSignIn(user: FirebaseUser) {
        _uiEvent.value = AuthUiEvent.Loading

        viewModelScope.launch {
            val result = customerRepository.createCustomer(user)

            if (result.isSuccess) {
                _uiEvent.value = AuthUiEvent.Success
            } else {
                val msg = result.exceptionOrNull()?.message ?: "Could Not Create Customer"

                _uiEvent.value = AuthUiEvent.Error(msg)
            }

        }

    }
}
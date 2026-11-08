package com.example.burgerrestaurantapp.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.burgerrestaurantapp.core.data.domain.CurstomerRepository
import com.example.burgerrestaurantapp.feature.util.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val customerRepository: CurstomerRepository) : ViewModel() {

    fun signOut(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                customerRepository.signOut()
            }

            if (result.isSuccess()) {
                onSuccess()
            } else if (result.isError()) {
                onError(result.getErrorMessage())
            }
        }
    }

}
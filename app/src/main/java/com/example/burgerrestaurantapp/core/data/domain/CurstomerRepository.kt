package com.example.burgerrestaurantapp.core.data.domain

import com.example.burgerrestaurantapp.core.data.models.Customer
import com.example.burgerrestaurantapp.feature.util.RequestState
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface CurstomerRepository {
    fun getCurrentUserId(): String?
    suspend fun createCustomer(
        user: FirebaseUser,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    )

    suspend fun readCustomerFlow(): Flow<RequestState<Customer>>

    suspend fun updateCustomer(
        customer: Customer,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    )

    suspend fun signOut(): RequestState<Unit>
}
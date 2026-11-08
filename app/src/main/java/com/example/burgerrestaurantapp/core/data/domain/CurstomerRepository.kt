package com.example.burgerrestaurantapp.core.data.domain

import com.example.burgerrestaurantapp.feature.util.RequestState
import com.google.firebase.auth.FirebaseUser

interface CurstomerRepository {
    fun getCurrentUserId(): String?
    suspend fun createCustomer(
        user: FirebaseUser,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    )

    suspend fun signOut(): RequestState<Unit>
}
package com.example.burgerrestaurantapp.core.data.domain

import com.google.firebase.auth.FirebaseUser

interface CurstomerRepository {
    fun getCurrentUserId(): String?
    suspend fun createCustomer(user: FirebaseUser): Result<Unit>
}
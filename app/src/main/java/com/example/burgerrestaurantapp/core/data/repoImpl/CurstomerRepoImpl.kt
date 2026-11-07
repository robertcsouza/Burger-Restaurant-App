package com.example.burgerrestaurantapp.core.data.repoImpl

import com.example.burgerrestaurantapp.core.data.domain.CurstomerRepository
import com.example.burgerrestaurantapp.core.data.models.Customer
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class CurstomerRepoImpl : CurstomerRepository {
    override fun getCurrentUserId(): String? = FirebaseAuth.getInstance().currentUser?.uid

    override suspend fun createCustomer(user: FirebaseUser): Result<Unit> = runCatching {
        val customerCollection = Firebase.firestore.collection("customer")
        val docRef = customerCollection.document(user.uid)
        val snapshot = docRef.get().await()

        if (!snapshot.exists()) {
            val customer = Customer(
                id = user.uid,
                firstName = user.displayName?.split(" ")?.firstOrNull() ?: "Unknow",
                lastName = user.displayName?.split(" ")?.lastOrNull() ?: "Unknow",
                email = user.email ?: "Unknow"
            )
            docRef.set(customer).await()
        }
        
    }

}
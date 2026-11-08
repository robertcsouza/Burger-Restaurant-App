package com.example.burgerrestaurantapp.core.di

import com.example.burgerrestaurantapp.R
import com.example.burgerrestaurantapp.core.data.auth.GoogleUiClient
import com.example.burgerrestaurantapp.core.data.domain.CurstomerRepository
import com.example.burgerrestaurantapp.core.data.repoImpl.CurstomerRepoImpl
import com.example.burgerrestaurantapp.feature.auth.AuthViewModel
import com.example.burgerrestaurantapp.feature.home.HomeViewModel
import com.example.burgerrestaurantapp.feature.profile.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<FirebaseAuth> { FirebaseAuth.getInstance() }
    single<CurstomerRepository> { CurstomerRepoImpl() }
    viewModel { AuthViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    single {
        GoogleUiClient(
            context = androidContext(),
            auth = get(),
            serverClient = androidContext().getString(
                R.string.default_web_client_id
            )
        )
    }
}
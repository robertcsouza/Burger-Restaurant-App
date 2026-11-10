package com.example.burgerrestaurantapp.core.di

import com.example.burgerrestaurantapp.R
import com.example.burgerrestaurantapp.core.data.auth.GoogleUiClient
import com.example.burgerrestaurantapp.core.data.domain.CountryRepository
import com.example.burgerrestaurantapp.core.data.domain.CountryRepositoryImpl
import com.example.burgerrestaurantapp.core.data.domain.CurstomerRepository
import com.example.burgerrestaurantapp.core.data.remote.RestCountriesApi
import com.example.burgerrestaurantapp.core.data.repoImpl.CurstomerRepoImpl
import com.example.burgerrestaurantapp.feature.auth.AuthViewModel
import com.example.burgerrestaurantapp.feature.home.HomeViewModel
import com.example.burgerrestaurantapp.feature.profile.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val authenticatedRequest = if (request.url.host == "api.restcountries.com") {
                    request.newBuilder()
                        .header("Authorization", "Bearer API_KEY")
                        .build()
                } else {
                    request
                }
                chain.proceed(authenticatedRequest)
            }
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl("https://api.restcountries.com/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<RestCountriesApi> { get<Retrofit>().create(RestCountriesApi::class.java) }
    single<FirebaseAuth> { FirebaseAuth.getInstance() }
    single<CurstomerRepository> { CurstomerRepoImpl() }
    single<CountryRepository> { CountryRepositoryImpl(get()) }
    viewModel { AuthViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { ProfileViewModel(get(), get()) }
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
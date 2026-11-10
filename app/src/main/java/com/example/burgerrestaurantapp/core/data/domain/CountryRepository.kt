package com.example.burgerrestaurantapp.core.data.domain

import com.example.burgerrestaurantapp.core.data.models.Country
import com.example.burgerrestaurantapp.core.data.models.RestCountriesDto
import com.example.burgerrestaurantapp.core.data.models.toCountryOrNull
import com.example.burgerrestaurantapp.core.data.remote.RestCountriesApi
import com.example.burgerrestaurantapp.feature.util.RequestState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

interface CountryRepository {
    suspend fun fetchCountries(): Flow<RequestState<List<Country>>>
}

class CountryRepositoryImpl(
    private val api: RestCountriesApi
) : CountryRepository {
    override suspend fun fetchCountries(): Flow<RequestState<List<Country>>> =
        flow<RequestState<List<Country>>> {
            emit(RequestState.Loading)
            val countries = withContext(Dispatchers.IO) {
                val allCountries = mutableListOf<RestCountriesDto>()
                var offset = 0
                val pageSize = 100

                do {
                    val response = api.getAll(offset = offset, limit = pageSize)
                    check(response.errors.isNullOrEmpty()) {
                        response.errors?.joinToString { it.message ?: "Unknown API error" }
                            ?: "Country API error"
                    }
                    val page = checkNotNull(response.data?.objects) {
                        "Country API returned no country data."
                    }
                    allCountries.addAll(page)
                    offset += page.size
                } while (page.size == pageSize)

                allCountries.mapNotNull { it.toCountryOrNull() }
                    .distinctBy { it.code }
                    .sortedBy { it.name }
                    .also { check(it.isNotEmpty()) { "No valid countries returned." } }
            }
            emit(RequestState.Success(countries))
        }.catch { error ->
            if (error is CancellationException) throw error
            emit(RequestState.Error("Cannot load countries: ${error.message ?: "Unknown error"}"))
        }
}

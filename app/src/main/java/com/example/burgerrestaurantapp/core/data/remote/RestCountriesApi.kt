package com.example.burgerrestaurantapp.core.data.remote

import com.example.burgerrestaurantapp.core.data.models.CountriesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RestCountriesApi {
    @GET("countries/v5")
    suspend fun getAll(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 100,
        @Query("response_fields") fields: String =
            "names.common,codes.alpha_2,calling_codes,flag.url_png"
    ): CountriesResponseDto
}

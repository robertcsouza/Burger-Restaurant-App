package com.example.burgerrestaurantapp.core.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val code: String,
    val name: String,
    val dialCode: Int,
    val flagUrl: String?
)

data class CountriesResponseDto(
    val data: CountriesDataDto? = null,
    val errors: List<CountryApiErrorDto>? = null
)

data class CountriesDataDto(
    val objects: List<RestCountriesDto>? = null
)

data class CountryApiErrorDto(val message: String? = null)

data class RestCountriesDto(
    val names: CountryNamesDto? = null,
    val codes: CountryCodesDto? = null,
    @SerializedName("calling_codes")
    val callingCodes: List<String>? = null,
    val flag: CountryFlagDto? = null
)

data class CountryNamesDto(val common: String? = null)

data class CountryCodesDto(
    @SerializedName("alpha_2") val alpha2: String? = null
)

data class CountryFlagDto(
    @SerializedName("url_png") val pngUrl: String? = null
)

fun RestCountriesDto.toCountryOrNull(): Country? {
    val displayName = names?.common?.takeIf { it.isNotBlank() } ?: return null
    val code2 = codes?.alpha2?.takeIf { it.isNotBlank() } ?: return null
    val dialCode = callingCodes?.firstOrNull()
        ?.removePrefix("+")?.toIntOrNull()
        ?.takeIf { it > 0 } ?: return null

    return Country(
        name = displayName,
        dialCode = dialCode,
        code = code2,
        flagUrl = flag?.pngUrl?.takeIf { it.isNotBlank() }
    )
}

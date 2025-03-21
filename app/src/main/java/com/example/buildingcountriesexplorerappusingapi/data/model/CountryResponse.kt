package com.example.buildingcountriesexplorerappusingapi.data.model

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    val error: Boolean ,
    val msg: String,
    val data: List<Country>
)

data class Country(
    @SerializedName("country") val country: String,
    @SerializedName("code") val code: String
)




package com.example.buildingcountriesexplorerappusingapi.data.model

data class CityResponse(
    val error: Boolean,
    val msg: String,
    val data: List<String> // just list of string
)
data class CityRequest(
    val country: String,
    val state: String
)

package com.example.buildingcountriesexplorerappusingapi.data.model


data class CityResponse(
    val error: Boolean,
    val msg: String,
    val data: List<String> // just list of string
)


package com.example.buildingcountriesexplorerappusingapi.data.model

data class CityResponse( // handling API response, model the response structure from API when is fetch
    val error: Boolean,
    val msg: String,
    val data: List<String> // list of city names
)
data class CityRequest( // for sending API reques
    val country: String,
    val state: String
)

package com.example.buildingcountriesexplorerappusingapi.data.model

import com.google.gson.annotations.SerializedName

data class CountryResponse( // handling API response, model the response structure from API when is fetch
    val error: Boolean ,
    val msg: String,
    val data: List<Country>
)

data class Country( // API request
     val country: String,
     val code: String
)




package com.example.buildingcountriesexplorerappusingapi.data.apiservice


import com.example.buildingcountriesexplorerappusingapi.data.model.CountryResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService { // define endpoint
    @GET ("countries/population")
    suspend fun getCountries(): Response<CountryResponse>

}
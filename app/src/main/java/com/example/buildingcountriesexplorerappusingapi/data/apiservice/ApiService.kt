package com.example.buildingcountriesexplorerappusingapi.data.apiservice


import com.example.buildingcountriesexplorerappusingapi.data.model.CityRequest
import com.example.buildingcountriesexplorerappusingapi.data.model.CityResponse
import com.example.buildingcountriesexplorerappusingapi.data.model.CountryResponse
import com.example.buildingcountriesexplorerappusingapi.data.model.StateResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface ApiService { // define endpoint
    @GET ("countries/population")
    suspend fun getCountries(): Response<CountryResponse>

    @POST("countries/states")
    suspend fun getStates(@Body requestBody: Map<String, String>): Response<StateResponse>


    @POST("countries/state/cities")
    suspend fun getCities(
        @Body request: CityRequest
    ): CityResponse





}


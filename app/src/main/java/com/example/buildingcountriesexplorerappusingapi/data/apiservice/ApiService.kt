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
    @GET ("countries/population") // send request to endpoints ("countries/population")
    suspend fun getCountries(): Response<CountryResponse> // returns a response object in the CountryResponse model

    @POST("countries/states") // sends a POST request to the endpoint ("countries/states")
    suspend fun getStates(@Body requestBody: Map<String, String>): Response<StateResponse>


    @POST("countries/state/cities")
    suspend fun getCities(
        @Body request: CityRequest
    ): CityResponse


// GET when endpoints does not require complex data in the request body
// POST Used when data filtering or complex conditions are needed

}


package com.example.buildingcountriesexplorerappusingapi.data


import com.example.buildingcountriesexplorerappusingapi.data.apiservice.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "https://countriesnow.space/api/v0.1/"

    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

package com.example.buildingcountriesexplorerappusingapi.data.repositoryimplementation

import android.util.Log
import com.example.buildingcountriesexplorerappusingapi.data.apiservice.ApiService
import com.example.buildingcountriesexplorerappusingapi.data.model.Country
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import kotlinx.coroutines.flow.flow

open class CountryRepository(private val apiService: ApiService) {

    fun getCountries(): Flow<List<Country>> = flow {
        val response = apiService.getCountries()
        if (response.isSuccessful && response.body() != null) {
            emit(response.body()!!.data)
        } else {
            emit(emptyList())  // Handle error gracefully
        }
    }
}

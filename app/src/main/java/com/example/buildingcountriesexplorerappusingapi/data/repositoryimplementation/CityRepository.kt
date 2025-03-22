package com.example.buildingcountriesexplorerappusingapi.data.repositoryimplementation

import com.example.buildingcountriesexplorerappusingapi.data.apiservice.ApiService
import com.example.buildingcountriesexplorerappusingapi.data.model.CityRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CityRepository(private val apiService: ApiService) { // response for fetching data from API service

    fun getCities(country: String, state: String): Flow<List<String>> = flow { // take the function gitCities for given country and state and returns flow
        try {
            val request = CityRequest(country, state) // create request that send country and state
            val response = apiService.getCities(request) // calls the API to fetch data
            if (!response.error) { // data handling
                emit(response.data) // [ .. , .. ,.. ] <- return data
            } else {
                emit(emptyList()) // return empty list
            }
        } catch (e: Exception) {
            emit(emptyList()) // Handle errors
        }
    }
}

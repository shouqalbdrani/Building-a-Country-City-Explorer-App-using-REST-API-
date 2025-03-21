package com.example.buildingcountriesexplorerappusingapi.data.repositoryimplementation

import com.example.buildingcountriesexplorerappusingapi.data.apiservice.ApiService
import com.example.buildingcountriesexplorerappusingapi.data.model.CityRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CityRepository(private val apiService: ApiService) {

    fun getCities(country: String, state: String): Flow<List<String>> = flow {
        try {
            val request = CityRequest(country, state)
            val response = apiService.getCities(request)
            if (!response.error) {
                emit(response.data)
            } else {
                emit(emptyList())
            }
        } catch (e: Exception) {
            emit(emptyList()) // Handle errors
        }
    }
}

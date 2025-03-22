package com.example.buildingcountriesexplorerappusingapi.data.repositoryimplementation


import com.example.buildingcountriesexplorerappusingapi.data.apiservice.ApiService
import com.example.buildingcountriesexplorerappusingapi.data.model.CityRequest
import com.example.buildingcountriesexplorerappusingapi.data.model.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CountryRepository(private val apiService: ApiService) { // response for fetching data from API service

    fun getCountries(): Flow<List<Country>> = flow { //  fetches a list of countries and returns a Flow
        val response = apiService.getCountries() // the response stored in the response
        if (response.isSuccessful && response.body() != null) { // Checks if the API request was successful
            emit(response.body()!!.data) // sends the data as part of the Flow stream
        } else {
            emit(emptyList())  // Handle error
        }
    }
}

package com.example.buildingcountriesexplorerappusingapi.data.repositoryimplementation

import com.example.buildingcountriesexplorerappusingapi.data.apiservice.ApiService
import com.example.buildingcountriesexplorerappusingapi.data.model.State
import retrofit2.HttpException
import java.io.IOException

class StateRepository(private val apiService: ApiService) {

    suspend fun getStates(countryName: String): List<State> {
        val validCountryName = getValidCountryName(countryName)
        return try {
            val response = apiService.getStates(mapOf("country" to validCountryName))
            if (response.isSuccessful) {
                response.body()?.data?.states ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: IOException) {
            emptyList()
        } catch (e: HttpException) {
            emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun getValidCountryName(countryName: String): String {
        return when (countryName) {
            "Arab World" -> "United Arab Emirates"
            else -> countryName
        }
    }
}

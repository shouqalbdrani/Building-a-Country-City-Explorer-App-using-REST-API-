package com.example.buildingcountriesexplorerappusingapi.domain.usecases

import com.example.buildingcountriesexplorerappusingapi.data.model.State
import com.example.buildingcountriesexplorerappusingapi.data.repositoryimplementation.CityRepository
import com.example.buildingcountriesexplorerappusingapi.data.repositoryimplementation.StateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class GetCitiesUseCase(private val cityRepository: CityRepository) {

    operator fun invoke(countryName: String, state: String): Flow<List<String>> {
        return cityRepository.getCities(countryName, state)
            .catch { e ->

                println("Error fetching cities: ${e.message}")
                emit(emptyList())
            }
            .flowOn(Dispatchers.IO)
    }
}



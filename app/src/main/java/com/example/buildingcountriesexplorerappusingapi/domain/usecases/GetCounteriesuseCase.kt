package com.example.buildingcountriesexplorerappusingapi.domain.usecases

import com.example.buildingcountriesexplorerappusingapi.data.model.Country
import com.example.buildingcountriesexplorerappusingapi.data.model.CountryResponse
import com.example.buildingcountriesexplorerappusingapi.data.repositoryimplementation.CountryRepository
import kotlinx.coroutines.flow.Flow

class GetCounteriesuseCase(private val repository : CountryRepository) {
    operator fun invoke(): Flow<List<Country>> {
        return repository.getCountries()
    }
}

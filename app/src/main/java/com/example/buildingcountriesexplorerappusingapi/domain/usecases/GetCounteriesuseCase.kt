package com.example.buildingcountriesexplorerappusingapi.domain.usecases

import com.example.buildingcountriesexplorerappusingapi.data.model.Country
import com.example.buildingcountriesexplorerappusingapi.data.model.CountryResponse
import com.example.buildingcountriesexplorerappusingapi.data.repositoryimplementation.CountryRepository
import kotlinx.coroutines.flow.Flow

// acts as bridge between repository and viewModel
class GetCounteriesuseCase(private val repository : CountryRepository) {  // allows the use case to access data
    operator fun invoke(): Flow<List<Country>> { // to call the class directly , since use the flow state the use case will pass this data into view model
        return repository.getCountries()
    }
}

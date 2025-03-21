package com.example.buildingcountriesexplorerappusingapi.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.buildingcountriesexplorerappusingapi.domain.usecases.GetCitiesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CitiesViewModel(
    private val getCitiesUseCase: GetCitiesUseCase
) : ViewModel() {

    private val _cities = MutableStateFlow<List<String>>(emptyList())
    val cities: StateFlow<List<String>> = _cities.asStateFlow()

    fun fetchCities(countryName: String, stateName: String) {
        val validStateName = getValidStateName(stateName)
        viewModelScope.launch {
            getCitiesUseCase(countryName, validStateName).collect { result ->
                _cities.value = result
            }
        }
    }

    private fun getValidStateName(stateName: String): String {
        return when (stateName) {
            "New York City" -> "New York"
            "CA" -> "California"
            "TX" -> "Texas"
            else -> stateName
        }
    }
}

class CitiesViewModelFactory(
    private val getCitiesUseCase: GetCitiesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CitiesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CitiesViewModel(getCitiesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

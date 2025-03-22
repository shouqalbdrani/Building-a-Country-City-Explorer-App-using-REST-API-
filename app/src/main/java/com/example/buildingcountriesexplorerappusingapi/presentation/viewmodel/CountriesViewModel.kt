package com.example.buildingcountriesexplorerappusingapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.buildingcountriesexplorerappusingapi.data.model.Country
import com.example.buildingcountriesexplorerappusingapi.domain.usecases.GetCounteriesuseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Manage UI state and fetch country data
class CountriesViewModel(private val getCounteriesuseCase: GetCounteriesuseCase) : ViewModel() {

    private val _countries = MutableStateFlow<List<Country>>(emptyList()) // store internal data
    val countries: StateFlow<List<Country>> get() = _countries // represent state in the UI

    fun fetchCountries() { // fetch data
        viewModelScope.launch { // ensures that the coroutine runs with lifecycle of view model
            getCounteriesuseCase().collect { countryList ->// to retrieve list of countries
                _countries.value = countryList // collects the emitted data from the flow and updates _countries.
            }
        }
    }


}
// Create instance from CountriesViewModel
class CountriesViewModelFactory(
    private val getCounteriesuseCase: GetCounteriesuseCase
) : ViewModelProvider.Factory {
// check if the required viewModel is CounriesViewModel, if true create instance
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountriesViewModel::class.java)) {
            return CountriesViewModel(getCounteriesuseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}




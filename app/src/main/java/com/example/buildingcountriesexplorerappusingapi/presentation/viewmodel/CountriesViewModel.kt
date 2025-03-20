package com.example.buildingcountriesexplorerappusingapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buildingcountriesexplorerappusingapi.data.model.Country
import com.example.buildingcountriesexplorerappusingapi.domain.usecases.GetCounteriesuseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CountriesViewModel(private val getCounteriesuseCase: GetCounteriesuseCase) : ViewModel() {

    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> get() = _countries

    fun fetchCountries() {
        viewModelScope.launch {
            getCounteriesuseCase().collect { countryList ->
                _countries.value = countryList
            }
        }
    }
}



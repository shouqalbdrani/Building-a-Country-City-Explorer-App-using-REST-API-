package com.example.buildingcountriesexplorerappusingapi.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.buildingcountriesexplorerappusingapi.domain.usecases.GetCounteriesuseCase

class CountriesViewModelFactory(
    private val getCounteriesuseCase: GetCounteriesuseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountriesViewModel::class.java)) {
            return CountriesViewModel(getCounteriesuseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

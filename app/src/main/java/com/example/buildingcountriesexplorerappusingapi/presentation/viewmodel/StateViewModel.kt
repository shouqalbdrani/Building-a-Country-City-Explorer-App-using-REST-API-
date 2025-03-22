package com.example.buildingcountriesexplorerappusingapi.presentation.viewmodel

import GetStatesUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.buildingcountriesexplorerappusingapi.data.model.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
// Manage UI state and fetch States data
class StateViewModel(
    private val getStatesUseCase: GetStatesUseCase
) : ViewModel() {

    private val _states = MutableStateFlow<List<State>>(emptyList())
    val states: StateFlow<List<State>> = _states

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    fun fetchStates(countryName: String) {
        viewModelScope.launch {
            val validCountryName = getValidCountryName(countryName)
            println("🔍 Requesting states for: '$validCountryName'")  // Added Log
            getStatesUseCase.invoke(validCountryName).collect { result -> // to retrieve list of states
                println("📋 Received states: $result")
                _states.value = result
            }
        }
    }
    private fun getValidCountryName(countryName: String): String { // for handle differnt name in request
        return when (countryName) {
            "Arab World" -> "United Arab Emirates"
            else -> countryName
        }
    }

}
// Create instance from SateViewModel
class StateViewModelFactory(
    private val getStatesUseCase: GetStatesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StateViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StateViewModel(getStatesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

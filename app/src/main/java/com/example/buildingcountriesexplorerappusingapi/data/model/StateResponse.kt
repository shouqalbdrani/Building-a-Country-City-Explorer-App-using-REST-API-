package com.example.buildingcountriesexplorerappusingapi.data.model


data class StateResponse(
    val error: Boolean,
    val msg: String,
    val data: CountryState
)

data class CountryState(
    val name: String, // name of country "data" : [{ "name": "Afghanistan", "states":[{"name":"Badakhshan".....
    val states: List<State>
)


data class State(
    val name: String
   // val state_code: String
)


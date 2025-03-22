package com.example.buildingcountriesexplorerappusingapi.data.model


data class StateResponse( // handling API response, model the response structure from API when is fetch
    val error: Boolean,
    val msg: String,
    val data: CountryState // handle country name and its states
)

data class CountryState(
    val name: String, // name of country "data" : [{ "name": "Afghanistan", "states":[{"name":"Badakhshan".....
    val states: List<State> // list of states
)


data class State(
    val name: String,
   //val state_code: String
)


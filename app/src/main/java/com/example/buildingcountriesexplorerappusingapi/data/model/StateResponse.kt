package com.example.buildingcountriesexplorerappusingapi.data.model

import com.google.gson.annotations.SerializedName


data class StateResponse(
    val error: Boolean ,
    val msg: String,
    val data: List<State>
)

data class State(
    @SerializedName("name") val stateName: String, // maps name in json to stateName
    @SerializedName ("state_code") val stateCode : String // maps state_code in json to be stateCode
)

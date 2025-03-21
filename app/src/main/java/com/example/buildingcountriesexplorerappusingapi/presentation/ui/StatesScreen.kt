package com.example.buildingcountriesexplorerappusingapi.presentation.ui

import GetStatesUseCase
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.buildingcountriesexplorerappusingapi.data.ApiClient
import com.example.buildingcountriesexplorerappusingapi.data.repositoryimplementation.StateRepository
import com.example.buildingcountriesexplorerappusingapi.presentation.viewmodel.StateViewModel
import com.example.buildingcountriesexplorerappusingapi.presentation.viewmodel.StateViewModelFactory

@Composable
fun StatesScreen(
    navController: NavController,
    countryName: String,
    viewModel: StateViewModel = viewModel(
        factory = StateViewModelFactory(
            GetStatesUseCase(
                StateRepository(ApiClient.provideApiService())
            )
        )
    )
) {
    val states by viewModel.states.collectAsState()

    LaunchedEffect(countryName) {
        viewModel.fetchStates(countryName)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ){
        Text(
            text = "States List",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            textAlign = TextAlign.Center
        )
    }

    if (states.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("No states found or data is loading.")
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(35.dp)
        ) {
            items(states) { state ->
                StateItem(stateName = state.name,
                    countryName = countryName,
                    navController = navController)
            }
        }
    }
}


@Composable
fun StateItem(stateName: String ,
              navController: NavController,
              countryName: String,) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { navController.navigate("citiesScreen/${countryName}/${stateName}") }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stateName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

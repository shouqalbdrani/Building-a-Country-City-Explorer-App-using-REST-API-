package com.example.buildingcountriesexplorerappusingapi.presentation.ui

import com.example.buildingcountriesexplorerappusingapi.domain.usecases.GetCitiesUseCase // Correct import
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.buildingcountriesexplorerappusingapi.data.ApiClient
import com.example.buildingcountriesexplorerappusingapi.data.repositoryimplementation.CityRepository
import com.example.buildingcountriesexplorerappusingapi.presentation.viewmodel.CitiesViewModel
import com.example.buildingcountriesexplorerappusingapi.presentation.viewmodel.CitiesViewModelFactory
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CitiesScreen(
    countryName: String,
    stateName: String,
    navController: NavController,
    viewModel: CitiesViewModel = viewModel(
        factory = CitiesViewModelFactory(
            GetCitiesUseCase(
                CityRepository(ApiClient.provideApiService())
            )
        )
    )
) {
    val cities by viewModel.cities.collectAsState()

    LaunchedEffect(stateName) {
        viewModel.fetchCities(countryName, stateName)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ){
        Text(
            text = "Cities List",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            textAlign = TextAlign.Center
        )
    }

    if (cities.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("No cities found or data is loading.")
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(35.dp)
        ) {
            items(cities) { city ->
                CityItem(cityName = city)
            }
        }
    }
}

@Composable
fun CityItem(cityName: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = cityName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

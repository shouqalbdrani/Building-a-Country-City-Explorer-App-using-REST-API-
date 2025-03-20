package com.example.buildingcountriesexplorerappusingapi.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buildingcountriesexplorerappusingapi.presentation.viewmodel.CountriesViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.buildingcountriesexplorerappusingapi.data.ApiClient
import com.example.buildingcountriesexplorerappusingapi.data.repositoryimplementation.CountryRepository
import com.example.buildingcountriesexplorerappusingapi.domain.usecases.GetCounteriesuseCase
import com.example.buildingcountriesexplorerappusingapi.presentation.viewmodel.CountriesViewModelFactory

@Composable
fun CountriesScreen(
    onCountryClick: (String) -> Unit
) {
    val viewModel: CountriesViewModel = viewModel(
        factory = CountriesViewModelFactory(
            GetCounteriesuseCase(
                CountryRepository(ApiClient.provideApiService())
            )
        )
    )

    val countries by viewModel.countries.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCountries()
    }

    if (countries.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("No countries found or data is loading.")
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            items(countries) { country ->
                CountryItem(
                    countryName = "${country.country} (${country.code})",
                    onCountryClick
                )
            }
        }
    }
}



@Composable
fun CountryItem(
    countryName: String,
    onCountryClick: (String) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(
                onClick = { onCountryClick(countryName) }, // Clickable action
                indication = null,
                interactionSource = remember { MutableInteractionSource() } // Required when removing ripple
            )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = countryName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

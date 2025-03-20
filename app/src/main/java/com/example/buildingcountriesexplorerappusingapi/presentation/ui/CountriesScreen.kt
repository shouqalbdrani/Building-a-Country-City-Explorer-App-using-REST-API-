package com.example.buildingcountriesexplorerappusingapi.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CountriesScreen(
    onCountryClick: (String) -> Unit
) {
    val countries = listOf(
        "Saudi Arabia (SA)",
        "United Arab Emirates (AE)",
        "Kuwait (KW)",
        "Qatar (QA)",
        "Oman (OM)",
        "Seria (SY)",
        "Iraq (IQ)",
        "Lebanon (LB)",
        "Egypt (EG)",
        "India (IN)",
        "Pakistan (PK)"


    )

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(countries) { country ->
            CountryItem(countryName = country, onCountryClick)
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
            .clickable { onCountryClick(countryName) } // Clickable action
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = countryName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

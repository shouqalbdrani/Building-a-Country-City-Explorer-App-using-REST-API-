package com.example.buildingcountriesexplorerappusingapi

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.buildingcountriesexplorerappusingapi.presentation.ui.CitiesScreen
import com.example.buildingcountriesexplorerappusingapi.presentation.ui.CountriesScreen
import com.example.buildingcountriesexplorerappusingapi.presentation.ui.StatesScreen
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigator() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "countries"
    ) {
        composable("countries") {
            CountriesScreen { selectedCountry ->
                val encodedCountryName =
                    URLEncoder.encode(selectedCountry, StandardCharsets.UTF_8.toString())

                Toast.makeText(
                    navController.context,
                    "Clicked: $selectedCountry",
                    Toast.LENGTH_SHORT
                ).show()

                navController.navigate("states/$encodedCountryName")
            }
        }

        composable("states/{countryName}") { backStackEntry ->
            val countryName = backStackEntry.arguments
                ?.getString("countryName")
                ?.let { URLDecoder.decode(it, StandardCharsets.UTF_8.toString()) }
                ?: ""

            // Debug Log
            Log.d("StatesScreen", "Country Name Received: $countryName")

            StatesScreen(countryName = countryName, navController = navController)
        }
        composable("citiesScreen/{countryName}/{stateName}") { backStackEntry ->
            val countryName = backStackEntry.arguments?.getString("countryName") ?: ""
            val stateName = backStackEntry.arguments?.getString("stateName") ?: ""
            CitiesScreen(countryName, stateName, navController)
        }

    }
}
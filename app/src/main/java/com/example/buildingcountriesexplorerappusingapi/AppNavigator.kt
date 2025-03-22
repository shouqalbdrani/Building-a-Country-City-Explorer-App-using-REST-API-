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
fun AppNavigator() { // create navController to manage and track navigation
    val navController: NavHostController = rememberNavController()

    NavHost( // define starting pont which is the countries screen
        navController = navController,
        startDestination = "countries"
    ) {
        composable("countries") {
            CountriesScreen { selectedCountry -> // when the screen open receive onCountryClick
                val encodedCountryName =
                    URLEncoder.encode(selectedCountry, StandardCharsets.UTF_8.toString()) // encode to ensure special char like space handled

                Toast.makeText( // showing the selected country
                    navController.context,
                    "Clicked: $selectedCountry",
                    Toast.LENGTH_SHORT
                ).show()

                navController.navigate("states/$encodedCountryName") // navigate to states screen with the country that user choose
            }
        }

        composable("states/{countryName}") { backStackEntry -> // takes the country name from navigation argument
            val countryName = backStackEntry.arguments
                ?.getString("countryName")
                ?.let { URLDecoder.decode(it, StandardCharsets.UTF_8.toString()) } // decodes the encoded character back to original form
                ?: ""

            // Debug Log
            Log.d("StatesScreen", "Country Name Received: $countryName")

            StatesScreen(countryName = countryName, navController = navController)
        }
        composable("citiesScreen/{countryName}/{stateName}") { backStackEntry -> // his screen retrieves both countryName and stateName from navigation argument
            val countryName = backStackEntry.arguments?.getString("countryName") ?: ""
            val stateName = backStackEntry.arguments?.getString("stateName") ?: ""
            CitiesScreen(countryName, stateName, navController) // passed on cities screen
        }

    }
}
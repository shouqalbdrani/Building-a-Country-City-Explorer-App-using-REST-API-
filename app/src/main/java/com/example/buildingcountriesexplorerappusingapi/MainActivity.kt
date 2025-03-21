package com.example.buildingcountriesexplorerappusingapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.buildingcountriesexplorerappusingapi.presentation.ui.DarkModeToggle
import com.example.buildingcountriesexplorerappusingapi.presentation.viewmodel.ThemeViewModel
import com.example.buildingcountriesexplorerappusingapi.ui.theme.BuildingCountriesExplorerAppUsingAPITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            val isDarkMode by themeViewModel.isDarkMode.collectAsState()

            BuildingCountriesExplorerAppUsingAPITheme(darkTheme = isDarkMode) {
                Scaffold(
                    modifier = Modifier.
                    fillMaxSize(),
                    topBar = {
                        DarkModeToggle(themeViewModel)
                    }
                ) { paddingValues ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        AppNavigator()
                    }
                }
            }
        }
    }
}

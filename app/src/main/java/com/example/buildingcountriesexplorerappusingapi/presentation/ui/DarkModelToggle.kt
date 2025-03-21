package com.example.buildingcountriesexplorerappusingapi.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.buildingcountriesexplorerappusingapi.presentation.viewmodel.ThemeViewModel

@Composable
fun DarkModeToggle(themeViewModel: ThemeViewModel = viewModel()) {
    val isDarkMode by themeViewModel.isDarkMode.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = if (isDarkMode) "Dark Mode" else "Light Mode")
        Switch(
            checked = isDarkMode,
            onCheckedChange = { themeViewModel.toggleTheme() }
        )
    }
}

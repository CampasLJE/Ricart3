package com.example.proyecto.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyecto.Navigation.AppScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Nutricion(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nutricion") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF4CAF50)
                )
            )
        },

        bottomBar = {
            NavigationBar {

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(AppScreen.Lobby.route)},
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(AppScreen.Nutricion.route)},
                    icon = { Icon(Icons.Default.Restaurant, contentDescription = null) },
                    label = { Text("Nutrición") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(AppScreen.Hidratacion.route) },
                    icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                    label = { Text("Agua") }
                )

                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.BarChart, contentDescription = null) },
                    label = { Text("Stats") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(AppScreen.Recomendaciones.route) },
                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                    label = { Text("Recomendaciones") }
                )
            }
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
    }
}

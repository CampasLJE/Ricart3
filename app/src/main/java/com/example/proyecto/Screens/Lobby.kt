package com.example.proyecto.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import com.example.proyecto.Navigation.AppScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun Lobby(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Home"
                    )
                },
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
                    selected = true,
                    onClick = { navController.navigate(AppScreen.Nutricion.route)},
                    icon = { Icon(Icons.Default.Restaurant, contentDescription = "Restaurant") },
                    label = { Text("Nutricion") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(AppScreen.Hidratacion.route) },
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Agua") },
                    label = { Text("Agua") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(AppScreen.Estadisticas.route)},
                    icon = {Icon( Icons.Default.BarChart, contentDescription = "BarChart")},
                    label = {Text("Estadisticas")}
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {navController.navigate(AppScreen.Recomendaciones.route) },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                    label = { Text("Recomendaciones") }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Ritmo cardiaco")
                        Text("75 ppm")
                    }
                }
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Pasos")
                        Text("12.500 Steps")
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Caolorias")
                        Text("136 Kcal")
                    }
                }
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Estres")
                        Text("12.500 ")
                    }
                }
            }
        }
    }
}
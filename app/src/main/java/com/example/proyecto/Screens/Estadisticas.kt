package com.example.proyecto.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyecto.Navigation.AppScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Estadisticas(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Estadisticas",
                        fontSize = 30.sp,
                        color = Color.White
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
                    onClick = { navController.navigate(AppScreen.Lobby.route) },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(AppScreen.Nutricion.route) },
                    icon = { Icon(Icons.Default.Restaurant, contentDescription = null) },
                    label = { Text("Nutricion") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(AppScreen.Hidratacion.route) },
                    icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                    label = { Text("Agua") }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate(AppScreen.Estadisticas.route) },
                    icon = { Icon(Icons.Default.BarChart, contentDescription = null) },
                    label = { Text("Estadisticas") }
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
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tarjetas simples para mantener la pantalla funcionando sin graficas externas
            MetricCard("Ritmo cardiaco", "75 ppm")
            MetricCard("Pasos", "12,500")
            MetricCard("Calorias", "136 kcal")
            MetricCard("Estres", "30")
            MetricCard("Hidratacion", "4 / 8 vasos")
        }
    }
}

@Composable
private fun MetricCard(title: String, value: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(value, fontSize = 24.sp)
        }
    }
}

package com.example.proyecto.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyecto.Navigation.AppScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Recomendaciones(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recomendaciones",
                    fontSize = 30.sp,
                    color = Color.White
                )},
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
                    selected = false,
                    onClick = { navController.navigate(AppScreen.Estadisticas.route)},
                    icon = { Icon(Icons.Default.BarChart, contentDescription = null) },
                    label = { Text("Estadisticas") }
                )

                NavigationBarItem(
                    selected = true,
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Te damos nuestras \n" +
                    "recomendaciones? ",
                fontSize = 36.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.White )
            ){
                Text("Calcular")
            }
            Spacer(modifier = Modifier.height(30.dp))

            Text("Tus recomendaciones: ",
                fontSize = 24.sp)

        }
    }
}

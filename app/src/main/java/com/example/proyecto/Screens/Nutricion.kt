package com.example.proyecto.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyecto.Navigation.AppScreen
import com.example.proyecto.network.enviarNutricion
import com.example.proyecto.data.DataHolder
import kotlinx.coroutines.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Nutricion(navController: NavController){

    var alimento by remember { mutableStateOf("") }
    var lista by remember { mutableStateOf(listOf<String>()) }
    var totalCalorias by remember { mutableStateOf(0) }
    var totalProteinas by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nutrición", fontSize = 30.sp, color = Color.White) },
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Registro de alimentos", fontSize = 36.sp)

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = alimento,
                onValueChange = { alimento = it },
                label = { Text("Ej: pollo 200") }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (alimento.isNotEmpty()) {

                        lista = lista + alimento

                        val partes = alimento.split(" ")
                        val nombre = partes[0].lowercase()
                        val gramos = partes.getOrNull(1)?.toIntOrNull() ?: 100

                        val (calPor100, protPor100) = when(nombre){
                            "pollo" -> Pair(165, 31)
                            "arroz" -> Pair(130, 2)
                            "huevo" -> Pair(155, 13)
                            "atun" -> Pair(180, 25)
                            else -> Pair(150, 10)
                        }

                        val calorias = (calPor100 * gramos) / 100
                        val proteinas = (protPor100 * gramos) / 100

                        totalCalorias += calorias
                        totalProteinas += proteinas

                        val agua = 1000

                        CoroutineScope(Dispatchers.IO).launch {

                            val res = enviarNutricion(
                                totalCalorias,
                                2000,
                                totalProteinas,
                                60,
                                agua,
                                2000
                            )

                            DataHolder.recomendacionNutricion = res
                        }

                        alimento = ""
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.White
                )
            ) {
                Text("Agregar")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("Totales:")
            Text("Calorías: $totalCalorias kcal")
            Text("Proteínas: $totalProteinas g")

            Spacer(modifier = Modifier.height(30.dp))

            Text("Alimentos registrados:", fontSize = 18.sp)

            Spacer(modifier = Modifier.height(10.dp))

            lista.forEach {
                Text("• $it")
            }
        }
    }
}
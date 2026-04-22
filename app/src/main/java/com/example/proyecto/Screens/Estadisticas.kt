package com.example.proyecto.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyecto.Navigation.AppScreen
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entryOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Estadisticas(navController: NavController){

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Estadísticas",
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
                    selected = true,
                    onClick = { navController.navigate(AppScreen.Estadisticas.route)},
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
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val graficaRitmo = remember {
                ChartEntryModelProducer(
                    listOf(
                        listOf(
                            entryOf(1f, 60f),
                            entryOf(2f, 75f),
                            entryOf(3f, 70f),
                            entryOf(4f, 85f)
                        )
                    )
                )
            }

            Text("Ritmo cardiaco:",
                fontSize = 24.sp)
            Chart(
                chart = lineChart(),
                chartModelProducer = graficaRitmo,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            val graficaPasos = remember {
                ChartEntryModelProducer(
                    listOf(
                        listOf(
                            entryOf(1f, 3000f),
                            entryOf(2f, 5000f),
                            entryOf(3f, 7000f),
                            entryOf(4f, 9000f)
                        )
                    )
                )
            }

            Text("Pasos:",
                fontSize = 24.sp)
            Chart(
                chart = lineChart(),
                chartModelProducer = graficaPasos,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            val graficaCalorias = remember {
                ChartEntryModelProducer(
                    listOf(
                        listOf(
                            entryOf(1f, 200f),
                            entryOf(2f, 350f),
                            entryOf(3f, 400f),
                            entryOf(4f, 500f)
                        )
                    )
                )
            }

            Text("Calorías: ",
                fontSize = 24.sp)
            Chart(
                chart = lineChart(),
                chartModelProducer = graficaCalorias,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            val graficaEstres = remember {
                ChartEntryModelProducer(
                    listOf(
                        listOf(
                            entryOf(1f, 20f),
                            entryOf(2f, 40f),
                            entryOf(3f, 30f),
                            entryOf(4f, 60f)
                        )
                    )
                )
            }

            Text("Estrés: ",
                fontSize = 24.sp)
            Chart(
                chart = lineChart(),
                chartModelProducer = graficaEstres,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            val graficaAgua= remember {
                ChartEntryModelProducer(
                    listOf(
                        listOf(
                            entryOf(1f, 20f),
                            entryOf(2f, 40f),
                            entryOf(3f, 30f),
                            entryOf(4f, 60f)
                        )
                    )
                )
            }

            Text("Hidratacion: ",
                fontSize = 24.sp)
            Chart(
                chart = lineChart(),
                chartModelProducer = graficaAgua,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
    }
}
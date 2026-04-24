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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyecto.Navigation.AppScreen
import com.example.proyecto.data.DataHolder
import org.json.JSONObject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Recomendaciones(navController: NavController) {

    var mostrar by remember { mutableStateOf(false) }

    val nutricion = remember(DataHolder.recomendacionNutricion) {
        try {
            val json = JSONObject(DataHolder.recomendacionNutricion)
            val array = json.getJSONArray("recomendaciones")

            List(array.length()) { i ->
                array.getString(i)
            }
        } catch (e: Exception) {
            listOf("Sin recomendaciones de nutrición")
        }
    }
    val hidratacion = DataHolder.recomendacionHidratacion

    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Text("Recomendaciones", fontSize = 30.sp, color = Color.White)
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
                    icon = { Icon(Icons.Default.Home, null) },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(AppScreen.Nutricion.route) },
                    icon = { Icon(Icons.Default.Restaurant, null) },
                    label = { Text("Nutrición") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(AppScreen.Hidratacion.route) },
                    icon = { Icon(Icons.Default.Favorite, null) },
                    label = { Text("Agua") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(AppScreen.Estadisticas.route) },
                    icon = { Icon(Icons.Default.BarChart, null) },
                    label = { Text("Stats") }
                )

                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate(AppScreen.Recomendaciones.route) },
                    icon = { Icon(Icons.Default.Person, null) },
                    label = { Text("Tips") }
                )
            }
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                "Tus recomendaciones",
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { mostrar = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                )
            ) {
                Text("Ver recomendaciones")
            }

            Spacer(modifier = Modifier.height(30.dp))

            if (mostrar) {
                Text("Nutrición", fontSize = 22.sp)

                nutricion.forEach {
                    Text("• $it", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text("Hidratación", fontSize = 22.sp)

                Text(
                    text = if (hidratacion.isNotEmpty())
                        hidratacion
                    else
                        "Sin recomendación de hidratación",
                    fontSize = 16.sp
                )
            }
        }
    }
}
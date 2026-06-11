package com.example.proyecto.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyecto.Navigation.AppScreen
import com.example.proyecto.R
import com.example.proyecto.network.enviarBiometricos
import com.example.proyecto.sync.WearSyncBridge
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lobby(navController: NavController) {
    val context = LocalContext.current

    var estado by remember { mutableStateOf("Cargando...") }
    var ppm by remember { mutableStateOf(75) }
    var estres by remember { mutableStateOf(30) }

    // Mandamos el estado actual del home al reloj
    LaunchedEffect(ppm, estres) {
        WearSyncBridge.updateVitals(
            context = context,
            heartRate = ppm,
            stress = estres,
            steps = 12_500,
            caloriesBurned = 136
        )
    }

    // Mantenemos la llamada original a la API
    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val res = enviarBiometricos(ppm, estres)

            withContext(Dispatchers.Main) {
                estado = res
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Pagina principal",
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
                    selected = true,
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
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.home),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text("Tus datos:", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(20.dp))

            Row {
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Ritmo cardiaco")
                        Text("$ppm ppm")
                    }
                }

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Pasos")
                        Text("12,500 Steps")
                    }
                }
            }

            Row {
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Calorias")
                        Text("136 Kcal")
                    }
                }

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Estres")
                        Text("$estres")
                    }
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Estado de salud")
                    Text(
                        text = estado,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

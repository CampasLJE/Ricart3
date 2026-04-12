package com.example.proyecto.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun Login(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(

                title = {
                    Text(
                        ""
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF4CAF50)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Iniciar sesion",
                fontSize = 40.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text("Usuario o Email")
            Spacer(modifier = Modifier.height(5.dp))

            TextField(
                value = "",
                onValueChange = { },
                label = { Text("Ingrese su usuario o email") }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text("Password")
            Spacer(modifier = Modifier.height(5.dp))
            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Ingrese su contraseña") }
            )
            var marcar by remember { mutableStateOf(false) }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = marcar,
                        onCheckedChange = { marcar = it }
                    )
                    Text("Recordar")
                }
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    "Olvidaste tu contraseña?",
                    color = Color.Blue,
                    modifier = Modifier.clickable {
                        navController.navigate(AppScreen.Olvido.route)
                    }
                        .padding(10.dp)
                )
            }
            Button(
                onClick = {
                    navController.navigate(AppScreen.Lobby.route)
                }) {
                Text("Iniciar Sesion")

            }
            Spacer(modifier = Modifier.padding(top = 40.dp))
            Button(
                onClick = {
                    navController.popBackStack()
                }) {
                Text("Regresar")
            }
        }
    }
}
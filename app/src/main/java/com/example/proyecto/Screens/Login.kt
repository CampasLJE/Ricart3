package com.example.proyecto.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyecto.AuthManager
import com.example.proyecto.Navigation.AppScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavController) {

    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val auth = AuthManager(context)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Inicio de sesion",
                        fontSize = 30.sp,
                        color = Color.White
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
                value = user,
                onValueChange = { user = it },
                label = { Text("Ingrese su usuario o email") }
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text("Password")
            Spacer(modifier = Modifier.height(5.dp))
            TextField(
                value = pass,
                onValueChange = { pass = it },
                label = { Text("Ingrese su contraseña") }
            )

            if (error.isNotEmpty()) {
                Text(error, color = Color.Red)
            }

            var marcar by remember { mutableStateOf(false) }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
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
                    modifier = Modifier
                        .clickable {
                            navController.navigate(AppScreen.Olvido.route)
                        }
                        .padding(10.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        scope.launch {

                            // Validación básica
                            if (user.isBlank() || pass.isBlank()) {
                                error = "Campos obligatorios"
                                return@launch
                            }

                            // Limpia espacios innecesarios
                            val cleanUser = user.trim()
                            val cleanPass = pass.trim()

                            val success = auth.login(cleanUser, cleanPass)

                            if (success) {
                                error = ""

                                // Navegación segura (evita regresar al login)
                                navController.navigate(AppScreen.Lobby.route) {
                                    popUpTo(AppScreen.Login.route) { inclusive = true }
                                }

                            } else {
                                error = "Usuario o contraseña incorrectos"
                            }
                        }
                    },
                    modifier= Modifier.padding(10.dp,0.dp,10.dp,0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50),
                        contentColor = Color.White
                    )
                )
                {
                    Text("Iniciar Sesion")
                }
                Button(
                    onClick = {
                        scope.launch {

                            // Validación antes de registrar
                            if (user.isBlank() || pass.isBlank()) {
                                error = "Completa los campos"
                                return@launch
                            }

                            val cleanUser = user.trim()
                            val cleanPass = pass.trim()

                            val success = auth.register(cleanUser, cleanPass)

                            if (success) {
                                error = "Usuario registrado correctamente"
                            } else {
                                error = "Error al registrar"
                            }
                        }
                    },
                    modifier= Modifier.padding(10.dp,0.dp,10.dp,0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50),
                        contentColor = Color.White
                    )
                )
                {
                    Text("Registrar")
                }
            }
            Spacer(modifier = Modifier.padding(top = 40.dp))
            Button(
                onClick = {
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = Color.White
                )
            )
            {
                Text("Regresar")
            }
        }
    }
}
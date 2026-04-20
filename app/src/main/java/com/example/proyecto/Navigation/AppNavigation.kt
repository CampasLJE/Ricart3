package com.example.proyecto.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto.Screens.Estadisticas
import com.example.proyecto.Screens.Hidratacion
import com.example.proyecto.Screens.Inicio
import com.example.proyecto.Screens.Lobby
import com.example.proyecto.Screens.Login
import com.example.proyecto.Screens.Nutricion
import com.example.proyecto.Screens.Olvido
import com.example.proyecto.Screens.Recomendaciones


@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreen.Inicio.route){
        composable(route = AppScreen.Inicio.route){
            Inicio(navController)
        }
        composable(route = AppScreen.Login.route){
            Login(navController)
        }
        composable(route = AppScreen.Olvido.route){
            Olvido(navController)
        }
        composable(route = AppScreen.Lobby.route){
            Lobby(navController)
        }
        composable(route = AppScreen.Nutricion.route) {
            Nutricion(navController)
        }
        composable(route = AppScreen.Hidratacion.route) {
            Hidratacion(navController)
        }
        composable(route = AppScreen.Recomendaciones.route) {
            Recomendaciones(navController)
        }
        composable(route = AppScreen.Estadisticas.route) {
            Estadisticas(navController)
        }
    }
}
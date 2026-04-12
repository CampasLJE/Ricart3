package com.example.proyecto.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto.Screens.Inicio
import com.example.proyecto.Screens.Lobby
import com.example.proyecto.Screens.Login
import com.example.proyecto.Screens.Olvido

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
    }
}
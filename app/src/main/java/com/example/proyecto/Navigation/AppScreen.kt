package com.example.proyecto.Navigation

sealed class AppScreen (val route: String){
    object Inicio: AppScreen( "inicio")
    object Login: AppScreen("login")
    object Olvido: AppScreen("olvido")
    object Lobby: AppScreen("Lobby")
}
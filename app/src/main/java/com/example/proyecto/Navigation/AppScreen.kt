package com.example.proyecto.Navigation

sealed class AppScreen (val route: String){
    object Inicio: AppScreen( "inicio")
    object Login: AppScreen("login")
    object Olvido: AppScreen("olvido")
    object Lobby: AppScreen("lobby")
    object Estadisticas: AppScreen("estadisticas")
    object Recomendaciones: AppScreen("recomendaciones")
    object Hidratacion: AppScreen("hidratacion")
    object Nutricion: AppScreen("nutricion")
}
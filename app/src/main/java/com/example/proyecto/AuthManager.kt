package com.example.proyecto

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore(name = "auth_prefs")

class AuthManager(private val context: Context) {

    companion object {
        val USER = stringPreferencesKey("user")
        val PASS = stringPreferencesKey("pass")
    }

    // Registro con validación y retorno
    suspend fun register(user: String, pass: String): Boolean {

        // Validación básica
        if (user.isBlank() || pass.isBlank()) return false

        context.dataStore.edit {
            it[USER] = user
            it[PASS] = pass
        }

        return true
    }

    // Login seguro
    suspend fun login(user: String, pass: String): Boolean {

        val prefs = context.dataStore.data.first()

        val storedUser = prefs[USER]
        val storedPass = prefs[PASS]

        // Evita crash si no hay datos guardados
        if (storedUser == null || storedPass == null) {
            return false
        }

        return storedUser == user && storedPass == pass
    }
}
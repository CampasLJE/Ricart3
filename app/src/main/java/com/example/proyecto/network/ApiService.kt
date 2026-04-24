package com.example.proyecto.network

import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

fun enviarHidratacion(
    vasos: Int,
    meta: Int,
    onResult: (String) -> Unit
) {
    Thread {
        try {
            val url = URL("http://10.0.2.2:5000/hidratacion")

            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Type", "application/json")
            conn.doOutput = true

            val json = JSONObject()
            json.put("agua_ml", vasos * 250)
            json.put("meta_agua", meta * 250)
            json.put("hora", 12)

            conn.outputStream.write(json.toString().toByteArray())

            val response = conn.inputStream.bufferedReader().readText()

            onResult(response)

        } catch (e: Exception) {
            onResult("Error: ${e.message}")
        }
    }.start()
}
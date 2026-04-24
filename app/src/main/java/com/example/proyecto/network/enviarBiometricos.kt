package com.example.proyecto.network

import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

fun enviarBiometricos(ppm: Int, estres: Int): String {

    return try {
        val url = URL("http://10.0.2.2:5000/biometrico")

        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "POST"
        conn.doOutput = true
        conn.setRequestProperty("Content-Type", "application/json")

        val json = JSONObject()
        json.put("ppm", ppm)
        json.put("estres", estres)
        json.put("hora", 12)

        val writer = OutputStreamWriter(conn.outputStream)
        writer.write(json.toString())
        writer.flush()

        conn.inputStream.bufferedReader().readText()

    } catch (e: Exception) {
        "Error: ${e.message}"
    }
}
package com.example.proyecto.network

import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

fun enviarNutricion(
    calorias: Int,
    metaCalorias: Int,
    proteinas: Int,
    metaProteinas: Int,
    agua: Int,
    metaAgua: Int
): String {

    return try {
        val url = URL("http://10.0.2.2:5000/nutricion")

        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "POST"
        conn.doOutput = true
        conn.setRequestProperty("Content-Type", "application/json")

        val json = JSONObject()
        json.put("calorias", calorias)
        json.put("meta_calorias", metaCalorias)
        json.put("proteinas", proteinas)
        json.put("meta_proteinas", metaProteinas)
        json.put("hora", 12)

        val writer = OutputStreamWriter(conn.outputStream)
        writer.write(json.toString())
        writer.flush()

        conn.inputStream.bufferedReader().readText()

    } catch (e: Exception) {
        "Error: ${e.message}"
    }
}
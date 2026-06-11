package com.example.proyecto.sync

import android.content.Context
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable
import org.json.JSONObject

private const val WEAR_SYNC_PATH = "/ricart3/dashboard"
private const val WEAR_SYNC_KEY = "payload"

data class WearDashboardSnapshot(
    val heartRate: Int = 75,
    val stress: Int = 30,
    val steps: Int = 12500,
    val caloriesBurned: Int = 136,
    val waterCups: Int = 0,
    val waterGoal: Int = 8,
    val waterRecommendation: String = "",
    val caloriesTotal: Int = 0,
    val proteinTotal: Int = 0,
    val nutritionRecommendation: String = "",
)

object WearSyncBridge {
    @Volatile
    private var snapshot = WearDashboardSnapshot()

    fun updateVitals(
        context: Context,
        heartRate: Int,
        stress: Int,
        steps: Int = snapshot.steps,
        caloriesBurned: Int = snapshot.caloriesBurned
    ) {
        // Guardamos los datos del estado general del telefono
        snapshot = snapshot.copy(
            heartRate = heartRate,
            stress = stress,
            steps = steps,
            caloriesBurned = caloriesBurned
        )
        // Enviamos el paquete al reloj
        publish(context)
    }

    fun updateHydration(
        context: Context,
        waterCups: Int,
        waterGoal: Int,
        waterRecommendation: String
    ) {
        // Guardamos el estado de hidratacion antes de sincronizar
        snapshot = snapshot.copy(
            waterCups = waterCups,
            waterGoal = waterGoal,
            waterRecommendation = waterRecommendation
        )
        // Enviamos el paquete al reloj
        publish(context)
    }

    fun updateNutrition(
        context: Context,
        caloriesTotal: Int,
        proteinTotal: Int,
        nutritionRecommendation: String
    ) {
        // Guardamos el estado de nutricion antes de sincronizar
        snapshot = snapshot.copy(
            caloriesTotal = caloriesTotal,
            proteinTotal = proteinTotal,
            nutritionRecommendation = nutritionRecommendation
        )
        // Enviamos el paquete al reloj
        publish(context)
    }

    private fun publish(context: Context) {
        // Convertimos todo el estado a un solo JSON
        val request = PutDataMapRequest.create(WEAR_SYNC_PATH).apply {
            dataMap.putString(WEAR_SYNC_KEY, snapshot.toJson().toString())
        }.asPutDataRequest().setUrgent()

        // Mandamos el dato por Wear OS Data Layer
        Wearable.getDataClient(context.applicationContext).putDataItem(request)
    }
}

private fun WearDashboardSnapshot.toJson(): JSONObject {
    return JSONObject()
        .put("heartRate", heartRate)
        .put("stress", stress)
        .put("steps", steps)
        .put("caloriesBurned", caloriesBurned)
        .put("waterCups", waterCups)
        .put("waterGoal", waterGoal)
        .put("waterRecommendation", waterRecommendation)
        .put("caloriesTotal", caloriesTotal)
        .put("proteinTotal", proteinTotal)
        .put("nutritionRecommendation", nutritionRecommendation)
}

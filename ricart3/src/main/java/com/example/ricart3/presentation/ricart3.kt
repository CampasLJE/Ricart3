package com.example.ricart3.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ricart3.presentation.theme.ProyectoTheme
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataItem
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.Wearable
import org.json.JSONObject

private const val WEAR_SYNC_PATH = "/ricart3/dashboard"
private const val WEAR_SYNC_KEY = "payload"

class MainActivity : ComponentActivity(), DataClient.OnDataChangedListener {
    private var snapshot by mutableStateOf(WearDashboardSnapshot())
    private var statusText by mutableStateOf("Esperando datos del telefono")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadCurrentSnapshot()

        setContent {
            ProyectoTheme {
                WearDashboard(snapshot = snapshot, statusText = statusText)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Escuchamos cambios que mande el telefono
        Wearable.getDataClient(this).addListener(this)
    }

    override fun onPause() {
        // Dejamos de escuchar cuando la pantalla se pausa
        Wearable.getDataClient(this).removeListener(this)
        super.onPause()
    }

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        for (event in dataEvents) {
            if (event.type == com.google.android.gms.wearable.DataEvent.TYPE_CHANGED &&
                event.dataItem.uri.path == WEAR_SYNC_PATH
            ) {
                // Leemos el JSON que envio el telefono
                runOnUiThread {
                    snapshot = event.dataItem.toSnapshot()
                    statusText = "Sincronizado"
                }
            }
        }
    }

    private fun loadCurrentSnapshot() {
        // Cargamos el ultimo valor disponible al abrir el reloj
        Wearable.getDataClient(this).dataItems
            .addOnSuccessListener { buffer ->
                try {
                    for (dataItem in buffer) {
                        if (dataItem.uri.path == WEAR_SYNC_PATH) {
                            snapshot = dataItem.toSnapshot()
                            statusText = "Sincronizado"
                        }
                    }
                } finally {
                    buffer.release()
                }
            }
            .addOnFailureListener {
                statusText = "No se pudo leer el telefono"
            }
    }
}

@Composable
private fun WearDashboard(
    snapshot: WearDashboardSnapshot,
    statusText: String
) {
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = "Ricart3 Watch",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    Text(
                        text = statusText,
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Center
                    )
                }
                item { MetricCard("Ritmo cardiaco", "${snapshot.heartRate} ppm") }
                item { MetricCard("Pasos", snapshot.steps.toString()) }
                item { MetricCard("Calorias", "${snapshot.caloriesBurned} kcal") }
                item { MetricCard("Estres", snapshot.stress.toString()) }
                item { MetricCard("Agua", "${snapshot.waterCups}/${snapshot.waterGoal}") }
                item {
                    Text(
                        text = "Recomendacion agua",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                item {
                    Text(
                        text = if (snapshot.waterRecommendation.isNotBlank()) {
                            snapshot.waterRecommendation
                        } else {
                            "Sin recomendacion aun"
                        },
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    Text(
                        text = "Nutricion",
                        style = MaterialTheme.typography.labelMedium
                    )
                }
                item {
                    Text(
                        text = "Calorias: ${snapshot.caloriesTotal} | Proteinas: ${snapshot.proteinTotal}",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    Text(
                        text = if (snapshot.nutritionRecommendation.isNotBlank()) {
                            snapshot.nutritionRecommendation
                        } else {
                            "Sin recomendacion aun"
                        },
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun MetricCard(title: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.25f),
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = Color.White.copy(alpha = 0.08f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, style = MaterialTheme.typography.labelMedium)
        Text(text = value, style = MaterialTheme.typography.titleMedium)
    }
}

private data class WearDashboardSnapshot(
    val heartRate: Int = 75,
    val stress: Int = 30,
    val steps: Int = 12_500,
    val caloriesBurned: Int = 136,
    val waterCups: Int = 0,
    val waterGoal: Int = 8,
    val waterRecommendation: String = "",
    val caloriesTotal: Int = 0,
    val proteinTotal: Int = 0,
    val nutritionRecommendation: String = ""
)

private fun DataItem.toSnapshot(): WearDashboardSnapshot {
    // Sacamos el payload JSON desde el DataItem
    val payload = DataMapItem.fromDataItem(this).dataMap.getString(WEAR_SYNC_KEY)
        ?: return WearDashboardSnapshot()
    return payload.toSnapshot()
}

private fun String.toSnapshot(): WearDashboardSnapshot {
    // Convertimos el JSON en valores simples para la UI
    return try {
        val json = JSONObject(this)
        WearDashboardSnapshot(
            heartRate = json.optInt("heartRate", 75),
            stress = json.optInt("stress", 30),
            steps = json.optInt("steps", 12_500),
            caloriesBurned = json.optInt("caloriesBurned", 136),
            waterCups = json.optInt("waterCups", 0),
            waterGoal = json.optInt("waterGoal", 8),
            waterRecommendation = json.optString("waterRecommendation", ""),
            caloriesTotal = json.optInt("caloriesTotal", 0),
            proteinTotal = json.optInt("proteinTotal", 0),
            nutritionRecommendation = json.optString("nutritionRecommendation", "")
        )
    } catch (_: Exception) {
        WearDashboardSnapshot()
    }
}

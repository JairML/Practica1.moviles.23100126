package com.example.practica1moviles23100126

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.text.input.KeyboardType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityRegisterScreen(navController: NavHostController) {
    val actividades = listOf("Correr", "Caminar", "Nadar", "Ciclismo", "Yoga")
    val caloriasPorMinuto = mapOf(
        "Correr" to 10,
        "Caminar" to 5,
        "Nadar" to 8,
        "Ciclismo" to 7,
        "Yoga" to 4
    )

    var actividadSeleccionada by remember { mutableStateOf(actividades[0]) }
    var duracion by remember { mutableStateOf("") }
    var intensidad by remember { mutableStateOf("Media") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro de Actividad Física") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Tipo de actividad:")
            // Dropdown para elegir actividad
            var expanded by remember { mutableStateOf(false) }

            Box {
                OutlinedButton(
                    onClick = { expanded = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(actividadSeleccionada)
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    actividades.forEach { actividad ->
                        DropdownMenuItem(
                            text = { Text(actividad) },
                            onClick = {
                                actividadSeleccionada = actividad
                                expanded = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = duracion,
                onValueChange = { duracion = it },
                label = { Text("Duración (minutos)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Text("Intensidad:")
            Row(verticalAlignment = Alignment.CenterVertically) {
                listOf("Baja", "Media", "Alta").forEach {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = intensidad == it,
                            onClick = { intensidad = it }
                        )
                        Text(it)
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    val duracionNum = duracion.toFloatOrNull()
                    if (duracion.isBlank()) {
                        Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                    } else if (duracionNum == null || duracionNum <= 0) {
                        Toast.makeText(context, "La duración debe ser un número positivo", Toast.LENGTH_SHORT).show()
                    } else {
                        val caloriasBase = caloriasPorMinuto[actividadSeleccionada] ?: 0
                        val factorIntensidad = when (intensidad) {
                            "Baja" -> 0.8
                            "Media" -> 1.0
                            else -> 1.2
                        }
                        val totalCalorias = caloriasBase * duracionNum * factorIntensidad
                        Toast.makeText(
                            context,
                            "Calorías quemadas: %.2f kcal".format(totalCalorias),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular calorías quemadas")
            }
        }
    }
}
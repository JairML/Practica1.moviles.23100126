package com.example.practica1moviles23100126

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menú Principal de Navegación") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                onClick = { navController.navigate("agua") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calculadora de Consumo de Agua")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navController.navigate("actividad") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registro de Actividad Física")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navController.navigate("autos") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Catálogo de Autos Deportivos")
            }
        }
    }
}
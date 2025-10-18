package com.example.practica1moviles23100126

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage


// Modelo de datos de auto
data class Auto(
    val marca: String,
    val modelo: String,
    val precio: Double,
    val imagenUrl: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarCatalogScreen(navController: NavHostController) {

    // Lista de autos deportivos
    val autos = listOf(
        Auto(
            "Ferrari",
            "488 GTB",
            250000.0,
            "https://www.hdcarwallpapers.com/walls/2015_ferrari_488_gtb-wide.jpg"
        ),
        Auto(
            "Lamborghini",
            "Huracán EVO",
            280000.0,
            "https://th.bing.com/th/id/R.1a1219992160a20930d92b1c7be7701a?rik=gt9mzzXSYXRYgw&riu=http%3a%2f%2fwww.hdcarwallpapers.com%2fwalls%2fvf_engineering_lamborghini_huracan_performante_2020_4k-HD.jpg&ehk=NC4hE6VHJhBbFQg%2fbHXfe1ezrq4fLdSzm%2b1wJEAnGcM%3d&risl=1&pid=ImgRaw&r=0"
        ),
        Auto(
            "Porsche",
            "911 Turbo S",
            220000.0,
            imagenUrl = "https://www.hdcarwallpapers.com/walls/porsche_911_carrera_4s_2019_4k_11-HD.jpg"
        ),
        Auto(
            "McLaren",
            "720S",
            300000.0,
            "https://cdn.motor1.com/images/mgl/koBAjM/s1/mclaren-750s-coupe-front-3-4.jpg"
        ),
        Auto(
            "Aston Martin",
            "Vantage",
            190000.0,
            "https://media.autoexpress.co.uk/image/private/s--_d4kF648--/v1599227183/autoexpress/2020/09/Aston%20Martin%20Victor-19.jpg"
        )
    )

    val total = autos.sumOf { it.precio }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo de Autos Deportivos") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(autos) { auto ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${auto.marca} ${auto.modelo}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        AsyncImage(
                            model = auto.imagenUrl,
                            contentDescription = auto.modelo,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(180.dp)
                                .fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Precio aproximado: $${"%,.2f".format(auto.precio)}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            // Total al final
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Costo total del catálogo: $${"%,.2f".format(total)}",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}


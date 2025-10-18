plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}


android {
    namespace = "com.example.practica1moviles23100126"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.practica1moviles23100126"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        // FORMA CORRECTA de activar Strong Skipping para optimizar la recomposición
        freeCompilerArgs += listOf(
            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:strongSkipping=true"
        )
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        // Esta versión del compilador es compatible con Kotlin y las librerías de Compose más recientes
        kotlinCompilerExtensionVersion = "1.5.10"
    }

    packaging {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}

dependencies {
    // ---- DEPENDENCIAS PRINCIPALES ----
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.activity:activity-compose:1.9.0")

    // ---- LIFECYCLE (necesario para el estado en Compose) ----
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.3")

    // ---- BOM de COMPOSE (Bill of Materials) ----
    // El BOM nos ayuda a no tener que especificar la versión para cada librería de Compose.
    // Todas usarán la versión definida en el BOM, evitando conflictos.
    val composeBom = platform("androidx.compose:compose-bom:2024.06.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // ---- LIBRERÍAS DE COMPOSE (sin especificar versión, la toman del BOM) ----
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3") // Usará la versión del BOM
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material:material-icons-extended")

    // ---- NAVEGACIÓN EN COMPOSE ----
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // ---- COIL (para cargar imágenes) ----
    implementation("io.coil-kt:coil-compose:2.6.0")

    // ---- DEPENDENCIAS DE DEBUG Y TESTING ----
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

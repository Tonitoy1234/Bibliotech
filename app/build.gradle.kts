plugins {
    // Plugin principal de aplicaciones Android
    alias(libs.plugins.android.application)

    // Plugin oficial para Jetpack Compose
    alias(libs.plugins.kotlin.compose)

    // Plugin utilizado por Room para generar código automáticamente
    alias(libs.plugins.ksp)
}


android {
    namespace = "com.example.bibliotech"

    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.bibliotech"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation("androidx.compose.material:material-icons-extended")
    testImplementation(libs.junit)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
    // ===================================================
// ROOM (Persistencia de datos)
// ===================================================

// Biblioteca principal de Room
    implementation(libs.androidx.room.runtime)

// Extensiones para utilizar corrutinas y Kotlin
    implementation(libs.androidx.room.ktx)

// Generador automático del código de Room
    ksp(libs.androidx.room.compiler)

}
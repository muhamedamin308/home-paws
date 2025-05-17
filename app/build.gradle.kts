plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    kotlin("plugin.serialization") version "2.0.21"
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.homepaws"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.homepaws"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    implementation(libs.play.services.maps)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("com.google.android.material:material:1.3.0")
    // ViewModel and LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.19.+")
    implementation("androidx.core:core-splashscreen:1.0.1")
    // Coroutines Core
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // Coroutines Android (for Android-specific extensions)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Lifecycle ViewModel Scope
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // Lifecycle Runtime Scope (for lifecycleScope)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("io.insert-koin:koin-android:3.5.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.0")
    // Retrofit for network requests
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")

    implementation("io.coil-kt:coil-compose:2.5.0")
    // Room for local database
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    implementation("org.orbit-mvi:orbit-viewmodel:6.1.0")
    implementation("com.google.accompanist:accompanist-permissions:0.33.2-alpha")
    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("me.relex:circleindicator:2.1.6")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    // Paging 3
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")
    // Swipe Refresh Layout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    // ExoPlayer core
    implementation("androidx.media3:media3-exoplayer:1.3.1")
    // ExoPlayer UI
    implementation("androidx.media3:media3-ui:1.3.1")
    // Google Maps
    implementation("com.google.maps.android:android-maps-utils:3.10.0")
    implementation("com.google.maps.android:maps-utils-ktx:5.1.1")
    implementation("com.google.android.gms:play-services-maps:20.0.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")
}

kapt {
    correctErrorTypes = true
}
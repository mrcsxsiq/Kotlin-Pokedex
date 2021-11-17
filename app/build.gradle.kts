plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}
apply(from = "../ktlint.gradle.kts")

android {
    compileSdk = 30
    defaultConfig {
        applicationId = "dev.marcosfarias.pokedex"
        minSdk = 23
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
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
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Architecture
    implementation("androidx.appcompat:appcompat:1.3.1")

    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")

    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.1.0")

    // Material
    implementation("com.google.android.material:material:1.4.0")

    // Third Party
    implementation("com.leinardi.android:speed-dial:3.2.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Persistence
    implementation("androidx.room:room-runtime:2.4.0-alpha03")
    kapt("androidx.room:room-compiler:2.4.0-alpha03")

    // Glide
    kapt("com.github.bumptech.glide:compiler:4.12.0")
    implementation("com.github.bumptech.glide:glide:4.12.0")

    // Koin
    implementation("io.insert-koin:koin-android:3.1.3")
    implementation("io.insert-koin:koin-core:3.1.3")

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("com.android.support.test.espresso:espresso-contrib:3.0.2")
    androidTestImplementation("androidx.navigation:navigation-testing:2.3.5")
    debugImplementation("androidx.fragment:fragment-testing:1.3.6")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("io.mockk:mockk-android:1.12.1")
    androidTestImplementation("io.mockk:mockk-agent-jvm:1.12.0")
    testImplementation("io.mockk:mockk:1.12.0")
    testImplementation("io.mockk:mockk-agent-jvm:1.12.0")
}

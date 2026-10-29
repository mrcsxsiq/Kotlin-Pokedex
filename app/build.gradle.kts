
plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

apply(from = "../ktlint.gradle.kts")

android {
    namespace = "dev.marcosfarias.pokedex"
    compileSdk = 33
    buildToolsVersion = "33.0.0"

    defaultConfig {
        applicationId = "dev.marcosfarias.pokedex"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"
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
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

    // Исключения для конфликтующих файлов ресурсов
    packagingOptions {
        resources {
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
        }
    }
}

dependencies {
    // Локальные JAR-библиотеки
    implementation(
        fileTree(
            mapOf(
                "dir" to "libs",
                "include" to listOf("*.jar")
            )
        )
    )

    // Базовые Android-компоненты
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.2.0")

    // Navigation
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    // Material Design
    implementation("com.google.android.material:material:1.8.0")

    // Speed Dial
    implementation("com.leinardi.android:speed-dial:3.3.0")

    // Retrofit и Gson
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Room
    implementation("androidx.room:room-runtime:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.15.1")
    kapt("com.github.bumptech.glide:compiler:4.15.1")

    // Koin
    implementation("io.insert-koin:koin-android:3.4.3")
    implementation("io.insert-koin:koin-core:3.4.3")

    // =========================
    // Unit-тесты: app/src/test
    // =========================

    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.13.16")

    testImplementation(
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3"
    )

    testImplementation(
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3"
    )

    testImplementation("androidx.arch.core:core-testing:2.2.0")

    // Mock HTTP-сервер для тестирования Retrofit
    testImplementation("com.squareup.okhttp3:mockwebserver:4.11.0")

    // Ktor Client для API-тестов
    testImplementation("io.ktor:ktor-client-core:2.3.12")
    testImplementation("io.ktor:ktor-client-mock:2.3.12")

    // =========================
    // UI-тесты: app/src/androidTest
    // =========================

    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

    // Espresso
    androidTestImplementation(
        "androidx.test.espresso:espresso-core:3.7.0"
    )

    androidTestImplementation(
        "androidx.test.espresso:espresso-contrib:3.7.0"
    )

    // UI Automator
    androidTestImplementation(
        "androidx.test.uiautomator:uiautomator:2.2.0"
    )

    // MockK для Android-тестов
    androidTestImplementation("io.mockk:mockk-android:1.13.16")

    // Тестирование навигации
    androidTestImplementation(
        "androidx.navigation:navigation-testing:2.5.3"
    )

    // Тестирование Fragment
    debugImplementation(
        "androidx.fragment:fragment-testing:1.5.5"
    )
}
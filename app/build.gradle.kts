plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}
// apply(from = "../ktlint.gradle.kts")

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.2")
    defaultConfig {
        applicationId =  "dev.marcosfarias.pokedex"
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.72")

    // Architecture
    implementation("androidx.core:core-ktx:1.3.0")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.navigation:navigation-ui:2.2.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.2.2")
    implementation("androidx.navigation:navigation-fragment:2.2.2")
    implementation("androidx.navigation:navigation-fragment-ktx:2.2.2")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.1.0")

    // Material
    implementation("com.google.android.material:material:1.1.0")

    // Third Party
    implementation("com.leinardi.android:speed-dial:3.1.1")

    // Retrofit
    implementation("com.squareup.okhttp3:okhttp:4.2.2")
    implementation("com.squareup.okhttp3:logging-interceptor:4.0.1")
    implementation("com.squareup.retrofit2:retrofit:2.8.1")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.8.1")
    implementation("com.squareup.retrofit2:converter-gson:2.8.1")
    implementation("com.google.code.gson:gson:2.8.6")

    // Persistence
    implementation("androidx.room:room-runtime:2.2.5")
    kapt("androidx.room:room-compiler:2.2.5")
    implementation("androidx.room:room-ktx:2.2.5")

    // Glide
    kapt("android.arch.lifecycle:compiler:1.1.1")
    kapt("com.github.bumptech.glide:compiler:4.10.0")
    implementation("com.github.bumptech.glide:glide:4.10.0")

    // Koin
    implementation("org.koin:koin-android:2.0.1")
    implementation("org.koin:koin-androidx-scope:2.0.1")
    implementation("org.koin:koin-androidx-viewmodel:2.0.1")

    // PokeApi
    implementation("me.sargunvohra.lib:pokekotlin:2.3.0")

    // Test
    testImplementation("junit:junit:4.13")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}

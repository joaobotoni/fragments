plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.app.fragments"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.app.fragments"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
}

dependencies {
    val room_version = "2.7.1"
    val autoValue = "1.10.4"
    // Room
    implementation("androidx.room:room-runtime:$room_version")

    // Anotação AutoValue
    implementation("com.google.auto.value:auto-value-annotations:1.10.4")
    annotationProcessor("com.google.auto.value:auto-value:$autoValue")

    // Gooogle Layout
    implementation("com.google.android.material:material:1.11.0")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
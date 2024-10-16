plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.gms.google.services)
    alias(libs.plugins.io.realm.kotlin)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kapt)

}

android {
    namespace = "com.example.stepogotchi_main"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.stepogotchi_main"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.junit.junit)
    testImplementation(libs.junit.junit)
    testImplementation(libs.junit.junit)
    testImplementation(libs.junit.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //My
    implementation(libs.androidx.lifecycle.runtime.compose)


    implementation (libs.play.services.auth)
    //Credential Manager
    implementation (libs.androidx.credentials)
    implementation (libs.androidx.credentials.play.services.auth)
    implementation (libs.googleid)


    // Coil
    implementation(libs.coil.compose)
    //Nav
    implementation (libs.androidx.navigation.compose)
    //Splash
    implementation(libs.androidx.core.splashscreen)

    implementation(libs.androidx.foundation)
    //RealmDB
    implementation (libs.library.base)
    implementation (libs.library.sync)
    //icons
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.material)
    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation (libs.androidx.hilt.navigation.compose)
    //AnimatedBottomBar
    implementation(libs.animated.navigation.bar)
    //Serialization

    // Local unit tests
    testImplementation(libs.junit)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.truth)
    testImplementation(libs.mockwebserver)
    testImplementation(libs.mockk)
    debugImplementation(libs.ui.test.manifest)

    // Instrumentation tests
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.core.testing)
    androidTestImplementation(libs.truth)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.core.ktx)
    androidTestImplementation(libs.mockwebserver)
    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.androidx.runner)




}

kapt {
    correctErrorTypes = true
}



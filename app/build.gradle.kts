plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.geeks.youtubeapi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.geeks.youtubeapi"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "BASE_URL",
            "\"https://www.googleapis.com/youtube/v3/\""
        )
        buildConfigField(
            "String",
            "API_KEY",
            "\"AIzaSyBPyV-qBG0BieGCmscoNt36w32TuAIllcY\""
        )
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
    buildFeatures {
        viewBinding = true
        buildConfig = true
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //OkHttp
    implementation(libs.okhttp3.okhttp)

    //interceptor
    implementation(libs.logging.interceptor)

    //Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //ExpandableLayout
    implementation(libs.expandablelayout)

    //Coil
    implementation(libs.coil)

    //Glide
    implementation(libs.glide)
    annotationProcessor(libs.compiler)

    //paging3
    implementation(libs.androidx.paging.runtime)

    //coroutines
    implementation(libs.kotlinx.coroutines.android)

    //viewModel
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.livedata)

    //Koin
    implementation(libs.koin.android)

    //youtube-player
    implementation (libs.core)
}
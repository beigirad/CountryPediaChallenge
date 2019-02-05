import ir.beigiead.dependencies.*

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Versions.compileSdk)

    defaultConfig {
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
        }
    }

    flavorDimensions("default")
    productFlavors {
        create(Flavors.production) {
            flavorDimensions("default")
            buildConfigField("String", "Host", "\"https://restcountries.eu/\"")

        }

        create(Flavors.staging) {
            flavorDimensions("default")
            buildConfigField("String", "Host", "\"https://restcountries.eu/\"")
        }
    }
}

dependencies {
    implementation(Libraries.rxkotlin)
    implementation(Libraries.dagger)
    kapt(Libraries.daggerCompiler)


    implementation(Libraries.retrofit)
    implementation(Libraries.rxjavaAdapter)
    implementation(Libraries.gsonConverter)
    implementation(Libraries.chuckInterceptor)
    implementation(Libraries.loggingInterceptor)

    implementation(Libraries.timber)

    implementation(project(Modules.data))
}
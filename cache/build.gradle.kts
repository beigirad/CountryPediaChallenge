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
}

dependencies {
    implementation(Libraries.rxkotlin)
    implementation(Libraries.dagger)
    kapt(Libraries.daggerCompiler)

    implementation(Libraries.room)
    kapt(Libraries.roomCompiler)
    implementation(Libraries.roomRx)

    implementation(Libraries.timber)

    implementation(project(Modules.data))
}
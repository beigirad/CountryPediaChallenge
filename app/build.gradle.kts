import ir.beigiead.dependencies.*
import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}


android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion(Versions.buildTools)

    defaultConfig {
        applicationId = ApplicationId.id
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = Releases.versionCode
        versionName = Releases.versionName

        vectorDrawables.useSupportLibrary = true
    }

    project.property("keystore.dir")?.also { dir ->
        Properties().also { it ->
            it.load(FileInputStream(file(dir)))

            signingConfigs {

                create("debugKey") {
                    storeFile = file(it["dStoreFile"]!!)
                    storePassword = it["dStorePassword"] as String
                    keyAlias = it["dKeyAlias"] as String
                    keyPassword = it["dKeyPassword"] as String
                }

                create("releaseKey") {
                    storeFile = file(it["rStoreFile"]!!)
                    storePassword = it["rStorePassword"] as String
                    keyAlias = it["rKeyAlias"] as String
                    keyPassword = it["rKeyPassword"] as String
                }
            }
        }
    }

    buildTypes {
        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("releaseKey")
        }

        getByName("debug") {
            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs.getByName("debugKey")
        }
    }

    flavorDimensions("default")
    productFlavors {
        create(Flavors.production) {
            flavorDimensions("default")
        }

        create(Flavors.staging) {
            flavorDimensions("default")
            versionNameSuffix = "S"
        }
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Libraries.kotlin)

    implementation(SupportLibraries.appcompat)
    implementation(SupportLibraries.design)
    implementation(SupportLibraries.constraintlayout)
    implementation(SupportLibraries.recyclerview)


    implementation(Libraries.lifecucleRuntime)
    implementation(Libraries.lifecycleExtensions)
    kapt(Libraries.lifecycleCompiler)

    implementation(Libraries.rxkotlin)
    implementation(Libraries.rxAndroid)

    implementation(Libraries.dagger)
    kapt(Libraries.daggerCompiler)
    implementation(Libraries.daggerAndroid)
    kapt(Libraries.daggerAndroidProcessor)
    implementation(Libraries.daggerAndroidSupport)

    implementation(Libraries.timber)

    implementation(Libraries.glide)
    kapt(Libraries.glideCompiler)
    implementation(Libraries.androidSvg)

    //TODO remove room dependencies
    implementation(Libraries.room)
    kapt(Libraries.roomCompiler)
    implementation(Libraries.roomRx)



    implementation(project(Modules.presentation))
    implementation(project(Modules.domain))
    implementation(project(Modules.data))
    implementation(project(Modules.remote))
    implementation(project(Modules.cache))

}
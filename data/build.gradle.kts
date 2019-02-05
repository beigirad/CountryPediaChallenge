import ir.beigiead.dependencies.*

plugins {
    id("kotlin")
    id("kotlin-kapt")
}

dependencies {
    implementation(Libraries.rxkotlin)
    implementation(Libraries.dagger)
    kapt(Libraries.daggerCompiler)

    implementation(project(Modules.domain))
}
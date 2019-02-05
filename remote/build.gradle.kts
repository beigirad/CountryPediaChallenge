import ir.beigiead.dependencies.*

plugins {
    id("kotlin")
    id("kotlin-kapt")
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


    implementation(project(Modules.data))
}
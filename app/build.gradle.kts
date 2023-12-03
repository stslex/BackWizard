plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinSerialization)
}

group = "com.stslex"
version = "0.0.1"

dependencies {
    implementation(libs.bundles.ktor)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.exposed)
    implementation(libs.h2)
    implementation(libs.postgres)
    implementation(libs.ktor.server.swagger)
    implementation(libs.logback.classic)
    testImplementation(libs.bundles.test)
}

application {
    mainClass.set("com.stslex.ApplicationKt")
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

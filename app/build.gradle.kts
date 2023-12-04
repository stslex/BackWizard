plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinSerialization)
}

group = "com.stslex"
version = "0.0.1"

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:database"))
    implementation(project(":feature:auth"))

    implementation(libs.ktor.server.swagger)
    testImplementation(libs.bundles.test)
}

application {
    mainClass.set("com.stslex.ApplicationKt")
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

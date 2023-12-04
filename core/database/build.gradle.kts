plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

group = "com.stslex.core.database"

dependencies {
    implementation(project(":core:core"))

    api(libs.bundles.exposed)
    api(libs.h2)
    api(libs.postgres)
}
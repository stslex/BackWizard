plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

group = "com.stslex.feature.admin"

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:database"))
}
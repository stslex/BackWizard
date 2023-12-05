plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

group = "com.stslex.feature.user"

dependencies {
    implementation(project(":core:core"))
    implementation(project(":core:database"))
}
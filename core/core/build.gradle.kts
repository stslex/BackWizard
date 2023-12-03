plugins {
    alias(libs.plugins.kotlinJvm)
}

group = "com.stslex.core.core"

dependencies {
    api(libs.logback.classic)
    api(libs.bundles.koin)
    api(libs.bundles.ktor) // TODO possible not needed ad all components
}
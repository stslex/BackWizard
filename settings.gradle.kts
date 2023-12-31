enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "BackWizard"
include(":app")
include(":core:core")
include(":core:database")
include(":feature:auth")
include("feature:user")
include("feature:admin")
findProject(":feature:admin")?.name = "admin"

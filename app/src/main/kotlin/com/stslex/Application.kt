package com.stslex

import com.typesafe.config.ConfigFactory
import freemarker.cache.ClassTemplateLoader
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.freemarker.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

fun main() {
    embeddedServer(
        factory = Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module
    )
        .start(wait = true)
}


private fun Application.module() {
    configureSerialization()
    configureRouting()
    configureTemplating()
}

@OptIn(ExperimentalSerializationApi::class)
fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
                removeIgnoredType(String::class)
            }
        )
    }
}

fun Application.configureTemplating() {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
}

fun Application.configureRouting() {
    routing {
        routineTest()
        routineSwagger()
    }
}

fun Routing.routineTest() {
    get("hello") {
        call.respond("hello")
    }
}

fun Routing.routineSwagger() {
    swaggerUI(
        path = "swagger",
        swaggerFile = "documentation/documentation.yaml"
    )
}

val config: HoconApplicationConfig = HoconApplicationConfig(ConfigFactory.load("application.conf"))
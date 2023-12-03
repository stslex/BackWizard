package com.stslex.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.routingPlugin() {
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
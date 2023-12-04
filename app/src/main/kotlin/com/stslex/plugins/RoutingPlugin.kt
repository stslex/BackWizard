package com.stslex.plugins

import com.stslex.core.database.sources.user.source.UserDatabaseSource
import com.stslex.feature.auth.route.authRoute
import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.routingPlugin() {
    routing {
        routineSwagger()
        authRoute()
        routineTest()
    }
}

fun Routing.routineTest() {
    val userDatabaseSource by inject<UserDatabaseSource>()
    get("hello/{username}") {
        val username = call.parameters["username"].orEmpty()
        val nickname = userDatabaseSource.getUserByUsername(username = username)
            ?.nickname
            ?: "unknown"
        call.respond(nickname)
    }
    get("test") {
        call.respond("test_success")
    }
}

private fun Routing.routineSwagger() {
    swaggerUI(
        path = "swagger",
        swaggerFile = "documentation/documentation.yaml"
    )
}
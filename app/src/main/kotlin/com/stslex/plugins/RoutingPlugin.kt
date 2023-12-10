package com.stslex.plugins

import com.stslex.core.core.AuthConfigType.*
import com.stslex.core.core.Config
import com.stslex.core.database.sources.user.source.UserDatabaseSource
import com.stslex.feature.admin.adminRoute
import com.stslex.feature.auth.route.authRoute
import com.stslex.feature.auth.route.refreshTokenRoute
import com.stslex.feature.user.route.userRoute
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.routingPlugin() {
    routing {
        routineSwagger()
        route(Config.API_ENDPOINT) {
            authenticate(UN_AUTH.configName) {
                authRoute()
            }
            authenticate(JWT_TOKEN_AUTH.configName, UN_AUTH.configName) {
                userRoute()
            }
            authenticate(ADMIN.configName) {
                adminRoute()
            }
            authenticate(JWT_REFRESH_TOKEN_AUTH.configName) {
                refreshTokenRoute()
            }
            routineTest()
        }
    }
}

fun Route.routineTest() {
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
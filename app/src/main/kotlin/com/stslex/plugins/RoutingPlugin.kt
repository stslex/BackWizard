package com.stslex.plugins

import com.stslex.core.core.AuthConfigType.*
import com.stslex.core.database.plugin.DatabasePlugin.dbPostgres
import com.stslex.core.database.sources.user.source.UserDatabaseSource
import com.stslex.core.database.sources.user.table.UserEntitiesTable
import com.stslex.feature.auth.route.authRoute
import com.stslex.feature.user.route.userRoute
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.ktor.ext.inject

fun Application.routingPlugin() {
    routing {
        routineSwagger()
        authenticate(UN_AUTH.configName) {
            authRoute()
        }
        authenticate(JWT_TOKEN_AUTH.configName, UN_AUTH.configName) {
            userRoute()
        }
        authenticate(ADMIN.configName) {
            adminRoute()
        }
        routineTest()
    }
}

private fun Route.adminRoute() {
    post("admin/drop") {
        transaction(dbPostgres) {
            SchemaUtils.drop(UserEntitiesTable)
            SchemaUtils.create(UserEntitiesTable)
        }
        call.respond("drop_success")
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
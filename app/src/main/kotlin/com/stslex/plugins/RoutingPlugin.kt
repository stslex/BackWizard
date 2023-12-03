package com.stslex.plugins

import com.stslex.db.DatabaseFactory
import com.stslex.db.UserEntitiesTable
import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import org.jetbrains.exposed.sql.select

fun Application.routingPlugin() {
    routing {
        routineTest()
        routineSwagger()
    }
}

fun Routing.routineTest() {
    get("hello") {
        val username = call.attributes[AttributeKey<String>("username")]
        val nickname = DatabaseFactory.dbQuery {
            UserEntitiesTable.select {
                UserEntitiesTable.username eq username
            }.map {
                it[UserEntitiesTable.nickname]
            }
        }
        call.respond(nickname)
    }
    get("test") {
        call.respond("test_success")
    }
}

fun Routing.routineSwagger() {
    swaggerUI(
        path = "swagger",
        swaggerFile = "documentation/documentation.yaml"
    )
}
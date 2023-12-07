package com.stslex.feature.admin

import com.stslex.core.database.plugin.DatabasePlugin
import com.stslex.core.database.sources.user.table.UserEntitiesTable
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

private const val ADMIN_ROUTE = "admin"

fun Route.adminRoute() {
    route(ADMIN_ROUTE) {
        post("drop") {
            transaction(DatabasePlugin.dbPostgres) {
                SchemaUtils.drop(UserEntitiesTable)
                SchemaUtils.create(UserEntitiesTable)
            }
            call.respond("drop_success")
        }
    }
}
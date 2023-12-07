package com.stslex.feature.admin

import com.stslex.core.core.Config
import com.stslex.core.database.plugin.DatabasePlugin
import com.stslex.core.database.sources.user.table.UserEntitiesTable
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

private const val ADMIN_ROUTE = "admin"
private const val ADMIN_API_ROUTE = "${Config.API_ENDPOINT}/$ADMIN_ROUTE"

fun Route.adminRoute() {
    post("$ADMIN_API_ROUTE/drop") {
        transaction(DatabasePlugin.dbPostgres) {
            SchemaUtils.drop(UserEntitiesTable)
            SchemaUtils.create(UserEntitiesTable)
        }
        call.respond("drop_success")
    }
}
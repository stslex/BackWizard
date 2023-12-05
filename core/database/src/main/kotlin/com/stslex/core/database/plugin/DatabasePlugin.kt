package com.stslex.core.database.plugin

import com.stslex.core.core.Config
import com.stslex.core.database.sources.user.table.UserEntitiesTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabasePlugin {

    private const val DRIVER_POSTGRES = "org.postgresql.Driver"
    val dbPostgres: Database
        get() = Database.connect(
            driver = DRIVER_POSTGRES,
            url = Config.postgresUrl,
            user = Config.postgresUser,
            password = Config.postgresPassword
        )

    fun configureDatabase() {
        transaction(dbPostgres) {
            SchemaUtils.create(UserEntitiesTable)
        }
    }
}
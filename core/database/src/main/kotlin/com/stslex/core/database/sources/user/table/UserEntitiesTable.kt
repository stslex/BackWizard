package com.stslex.core.database.sources.user.table

import org.jetbrains.exposed.sql.Table

object UserEntitiesTable : Table() {

    val uuid = uuid("uuid").autoGenerate()
    val login = varchar("login", 128)
    val password = varchar("password", 128)
    val username = varchar("username", 128)
    val nickname = varchar("nickname", 128)

    override val primaryKey: PrimaryKey = PrimaryKey(uuid)
}
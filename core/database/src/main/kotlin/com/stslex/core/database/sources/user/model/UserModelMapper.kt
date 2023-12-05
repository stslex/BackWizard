package com.stslex.core.database.sources.user.model

import com.stslex.core.database.sources.user.table.UserEntitiesTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toSourceUser(): UserSourceModel = UserSourceModel(
    uuid = this[UserEntitiesTable.uuid].toString(),
    login = this[UserEntitiesTable.login],
    username = this[UserEntitiesTable.username],
    password = this[UserEntitiesTable.password],
    nickname = this[UserEntitiesTable.nickname],
)
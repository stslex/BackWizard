package com.stslex.core.database.sources.user.model

import com.stslex.core.database.sources.user.table.UserEntitiesTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toSourceUser(): UserSourceModel = UserSourceModel(
    uuid = this[UserEntitiesTable.uuid].toString(),
    username = this[UserEntitiesTable.username],
    password = this[UserEntitiesTable.password],
    nickname = this[UserEntitiesTable.nickname]
)
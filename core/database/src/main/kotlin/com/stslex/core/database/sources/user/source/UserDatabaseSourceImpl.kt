package com.stslex.core.database.sources.user.source

import com.stslex.core.database.base.DatabaseSource
import com.stslex.core.database.sources.user.model.UserSourceModel
import com.stslex.core.database.sources.user.model.toSourceUser
import com.stslex.core.database.sources.user.table.UserEntitiesTable
import org.jetbrains.exposed.sql.select

class UserDatabaseSourceImpl : UserDatabaseSource, DatabaseSource() {

    override suspend fun getUser(
        username: String
    ): UserSourceModel? = dbQuery {
        UserEntitiesTable
            .select { UserEntitiesTable.username eq username }
            .singleOrNull()
            ?.toSourceUser()
    }
}
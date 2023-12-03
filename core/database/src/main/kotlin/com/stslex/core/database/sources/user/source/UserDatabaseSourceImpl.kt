package com.stslex.core.database.sources.user.source

import com.stslex.core.database.base.DatabaseSource
import com.stslex.core.database.base.toUUID
import com.stslex.core.database.sources.user.model.UserSourceModel
import com.stslex.core.database.sources.user.model.toSourceUser
import com.stslex.core.database.sources.user.table.UserEntitiesTable
import org.jetbrains.exposed.sql.select

class UserDatabaseSourceImpl : UserDatabaseSource, DatabaseSource() {

    override suspend fun getUserByUsername(
        username: String
    ): UserSourceModel? = dbQuery {
        UserEntitiesTable
            .select { UserEntitiesTable.username eq username }
            .singleOrNull()
            ?.toSourceUser()
    }

    override suspend fun getUserByUuid(
        uuidString: String
    ): UserSourceModel? = uuidString.toUUID?.let { uuid ->
        dbQuery {
            UserEntitiesTable
                .select { UserEntitiesTable.uuid eq uuid }
                .singleOrNull()
                ?.toSourceUser()
        }
    }
}
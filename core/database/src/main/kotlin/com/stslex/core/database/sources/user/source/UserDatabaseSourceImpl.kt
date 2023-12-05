package com.stslex.core.database.sources.user.source

import com.stslex.core.core.asyncMap
import com.stslex.core.database.base.DatabaseSource
import com.stslex.core.database.base.toUUID
import com.stslex.core.database.sources.user.model.NewUserSourceModel
import com.stslex.core.database.sources.user.model.UserSourceModel
import com.stslex.core.database.sources.user.model.toSourceUser
import com.stslex.core.database.sources.user.table.UserEntitiesTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class UserDatabaseSourceImpl : UserDatabaseSource, DatabaseSource() {

    override suspend fun getUserByLogin(login: String): UserSourceModel? = dbQuery {
        UserEntitiesTable
            .select { UserEntitiesTable.login eq login }
            .singleOrNull()
            ?.toSourceUser()
    }

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

    override suspend fun insertNewUser(
        user: NewUserSourceModel
    ) = dbQuery {
        UserEntitiesTable
            .insert {
                it[login] = user.login
                it[username] = user.username
                it[password] = user.password
                it[nickname] = ""
            }
            .resultedValues
            ?.singleOrNull()
            ?.toSourceUser()
    }

    override suspend fun getUserCount(): Int = dbQuery {
        UserEntitiesTable.selectAll().count().toInt()
    }

    override suspend fun getUsers(
        page: Int,
        pageSize: Int
    ): List<UserSourceModel> = dbQuery {
        UserEntitiesTable
            .selectAll()
            .limit(pageSize, page * pageSize.toLong())
            .asyncMap { it.toSourceUser() }
    }
}
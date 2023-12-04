package com.stslex.core.database.sources.user.source

import com.stslex.core.database.sources.user.model.UserSourceModel

interface UserDatabaseSource {

    suspend fun getUserByUsername(username: String): UserSourceModel?

    suspend fun getUserByUuid(uuidString: String): UserSourceModel?

    suspend fun insertNewUser(username: String, password: String): UserSourceModel?
}

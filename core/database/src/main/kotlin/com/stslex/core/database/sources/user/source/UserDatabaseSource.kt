package com.stslex.core.database.sources.user.source

import com.stslex.core.database.sources.user.model.NewUserSourceModel
import com.stslex.core.database.sources.user.model.UserSourceModel

interface UserDatabaseSource {

    suspend fun getUserByUsername(username: String): UserSourceModel?

    suspend fun getUserByLogin(login: String): UserSourceModel?

    suspend fun getUserByUuid(uuidString: String): UserSourceModel?

    suspend fun insertNewUser(user: NewUserSourceModel): UserSourceModel?

    suspend fun getUserCount(): Int

    suspend fun getUsers(page: Int, pageSize: Int): List<UserSourceModel>
}

package com.stslex.core.database.sources.user.source

import com.stslex.core.database.sources.user.model.UserSourceModel

interface UserDatabaseSource {

    suspend fun getUser(username: String): UserSourceModel?
}

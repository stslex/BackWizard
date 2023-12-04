package com.stslex.feature.auth.data

import com.stslex.core.database.sources.user.source.UserDatabaseSource
import com.stslex.feature.auth.data.model.AuthUserDataModel
import com.stslex.feature.auth.data.model.toData

class AuthRepositoryImpl(
    private val userDataSource: UserDatabaseSource
) : AuthRepository {

    override suspend fun isUserExist(
        username: String
    ): Boolean = userDataSource.getUserByUsername(username) != null

    override suspend fun saveUser(
        username: String,
        password: String
    ): AuthUserDataModel? = userDataSource
        .insertNewUser(
            username = username,
            password = password
        )
        ?.toData()

    override suspend fun getUser(
        username: String
    ): AuthUserDataModel? = userDataSource
        .getUserByUsername(username)
        ?.toData()
}


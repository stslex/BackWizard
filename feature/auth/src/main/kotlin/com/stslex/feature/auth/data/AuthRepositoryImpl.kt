package com.stslex.feature.auth.data

import com.stslex.core.database.sources.user.model.NewUserSourceModel
import com.stslex.core.database.sources.user.source.UserDatabaseSource
import com.stslex.feature.auth.data.model.AuthUserDataModel
import com.stslex.feature.auth.data.model.toData

class AuthRepositoryImpl(
    private val userDataSource: UserDatabaseSource
) : AuthRepository {

    override suspend fun isLoginExist(login: String): Boolean = userDataSource.getUserByLogin(login) != null

    override suspend fun isUsernameExist(username: String): Boolean = userDataSource.getUserByUsername(username) != null

    override suspend fun saveUser(
        login: String,
        password: String,
        username: String
    ): AuthUserDataModel? = userDataSource
        .insertNewUser(
            NewUserSourceModel(
                login = login,
                username = username,
                password = password
            )
        )
        ?.toData()

    override suspend fun getUser(
        login: String
    ): AuthUserDataModel? = userDataSource
        .getUserByUsername(login)
        ?.toData()
}


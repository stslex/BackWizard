package com.stslex.feature.auth.data

import com.stslex.feature.auth.data.model.AuthUserDataModel

interface AuthRepository {

    suspend fun isLoginExist(login: String): Boolean

    suspend fun isUsernameExist(username: String): Boolean

    suspend fun saveUser(
        login: String,
        password: String,
        username: String
    ): AuthUserDataModel?

    suspend fun getUser(login: String): AuthUserDataModel?
}

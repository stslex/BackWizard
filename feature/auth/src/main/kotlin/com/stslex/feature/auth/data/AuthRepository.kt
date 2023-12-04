package com.stslex.feature.auth.data

import com.stslex.feature.auth.data.model.AuthUserDataModel

interface AuthRepository {

    suspend fun isUserExist(username: String): Boolean

    suspend fun saveUser(username: String, password: String): AuthUserDataModel?

    suspend fun getUser(username: String): AuthUserDataModel?
}

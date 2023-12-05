package com.stslex.feature.user.data.repository

import com.stslex.feature.user.data.model.UserDataModel

interface UserRepository {

    suspend fun getUserByUuid(uuid: String): UserDataModel?

    suspend fun getUserByUsername(username: String): UserDataModel?

    suspend fun getUsers(page: Int, pageSize: Int): List<UserDataModel>

    suspend fun getUserCount(): Int
}

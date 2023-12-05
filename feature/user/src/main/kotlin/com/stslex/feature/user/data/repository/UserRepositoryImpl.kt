package com.stslex.feature.user.data.repository

import com.stslex.core.core.asyncMap
import com.stslex.core.database.sources.user.source.UserDatabaseSource
import com.stslex.feature.user.data.model.UserDataModel
import com.stslex.feature.user.data.model.toData

class UserRepositoryImpl(
    private val userSource: UserDatabaseSource
) : UserRepository {

    override suspend fun getUserByUuid(
        uuid: String
    ): UserDataModel? = userSource
        .getUserByUuid(uuid)
        ?.toData()


    override suspend fun getUserByUsername(
        username: String
    ): UserDataModel? = userSource
        .getUserByUsername(username)
        ?.toData()

    override suspend fun getUserCount(): Int = userSource.getUserCount()

    override suspend fun getUsers(
        page: Int,
        pageSize: Int
    ): List<UserDataModel> = userSource
        .getUsers(page, pageSize)
        .asyncMap { it.toData() }
}
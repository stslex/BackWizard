package com.stslex.feature.user.domain

import com.stslex.core.core.ApiResponse
import com.stslex.feature.user.route.model.UserFullResponse
import com.stslex.feature.user.route.model.UserPagingResponse
import com.stslex.feature.user.route.model.UserResponse

interface UserInteractor {

    suspend fun getUserByUuid(uuid: String): ApiResponse<UserResponse>

    suspend fun getUserByUsername(username: String): ApiResponse<UserResponse>

    suspend fun getUserFullInfo(
        uuid: String?,
        username: String?
    ): ApiResponse<UserFullResponse>

    suspend fun getUsers(page: Int, pageSize: Int): ApiResponse<UserPagingResponse>
}

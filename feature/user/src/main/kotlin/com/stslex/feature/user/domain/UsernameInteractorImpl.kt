package com.stslex.feature.user.domain

import com.stslex.core.core.ApiResponse
import com.stslex.core.core.asyncMap
import com.stslex.core.core.error.ApiError
import com.stslex.feature.user.data.repository.UserRepository
import com.stslex.feature.user.route.model.*
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class UsernameInteractorImpl(
    private val repository: UserRepository
) : UserInteractor {

    override suspend fun getUserByUuid(
        uuid: String
    ): ApiResponse<UserResponse> = try {
        repository
            .getUserByUuid(uuid)
            ?.toResponse()
            ?.let { ApiResponse.Success(it) }
            ?: ApiResponse.Error(UserApiError.USER_NOT_FOUND)
    } catch (e: Exception) {
        ApiResponse.Error(ApiError.InternalError(e))
    }

    override suspend fun getUserByUsername(
        username: String
    ): ApiResponse<UserResponse> = try {
        repository
            .getUserByUsername(username)
            ?.toResponse()
            ?.let { ApiResponse.Success(it) }
            ?: ApiResponse.Error(UserApiError.USER_NOT_FOUND)
    } catch (e: Exception) {
        ApiResponse.Error(ApiError.InternalError(e))
    }

    override suspend fun getUserFullInfo(
        uuid: String?,
        username: String?
    ): ApiResponse<UserFullResponse> = if (
        uuid.isNullOrBlank() ||
        username.isNullOrBlank()
    ) {
        ApiResponse.Error(UserApiError.PAYLOAD_ERROR)
    } else {
        try {
            repository
                .getUserByUuid(uuid)
                ?.toFullResponse()
                ?.let { ApiResponse.Success(it) }
                ?: ApiResponse.Error(UserApiError.USER_NOT_FOUND)
        } catch (e: Exception) {
            ApiResponse.Error(ApiError.InternalError(e))
        }
    }

    override suspend fun getUsers(
        page: Int,
        pageSize: Int
    ): ApiResponse<UserPagingResponse> = try {
        coroutineScope {
            val users = async {
                repository.getUsers(page, pageSize).asyncMap { it.toResponse() }
            }
            val total = async { repository.getUserCount() }
            ApiResponse.Success(
                UserPagingResponse(
                    result = users.await(),
                    total = total.await()
                )
            )
        }

    } catch (e: Exception) {
        ApiResponse.Error(ApiError.InternalError(e))
    }
}

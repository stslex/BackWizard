package com.stslex.feature.user.route.model

import com.stslex.core.core.error.ApiError
import io.ktor.http.*

enum class UserApiError(
    override val messageCode: Int,
    override val message: String,
    override val statusCode: HttpStatusCode
) : ApiError {
    USER_NOT_FOUND(
        messageCode = 300,
        message = "User not found",
        statusCode = HttpStatusCode.NotFound
    ),
    PAYLOAD_ERROR(
        messageCode = 301,
        message = "Token payload error",
        statusCode = HttpStatusCode.BadRequest
    ),
}
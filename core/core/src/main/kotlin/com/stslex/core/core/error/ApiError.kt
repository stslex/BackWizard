package com.stslex.core.core.error

import io.ktor.http.*

interface ApiError {
    val messageCode: Int
    val message: String
    val statusCode: HttpStatusCode

    val data: ApiErrorResponse
        get() = ApiErrorResponse(
            messageCode = messageCode,
            message = message
        )
}

enum class ApiCommonError(
    override val message: String,
    override val messageCode: Int,
    override val statusCode: HttpStatusCode
) : ApiError {
    INTERNAL_ERROR(
        statusCode = HttpStatusCode.InternalServerError,
        messageCode = 500,
        message = "Internal error"
    ),
}
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
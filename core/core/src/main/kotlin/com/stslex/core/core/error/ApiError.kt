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

    data class InternalError(
        private val error: Throwable,
        override val statusCode: HttpStatusCode = HttpStatusCode.InternalServerError,
    ) : ApiError {
        override val messageCode = 500
        override val message = error.message ?: "Internal error"
    }
}

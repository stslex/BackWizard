package com.stslex.plugins.auth.model

import com.stslex.core.core.error.ApiError
import io.ktor.http.*

enum class UnauthorizedError(
    override val messageCode: Int,
    override val message: String
) : ApiError {
    TOKEN(
        messageCode = 1,
        message = "Token is Invalid or has been expired"
    ),
    API_KEY(
        messageCode = 2,
        message = "Api Key is Invalid"
    );

    override val statusCode: HttpStatusCode = HttpStatusCode.Unauthorized
}
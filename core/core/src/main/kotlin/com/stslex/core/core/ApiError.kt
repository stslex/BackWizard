package com.stslex.core.core

import io.ktor.http.*

sealed class ApiError(
    val statusCode: HttpStatusCode,
    val messageCode: Int,
    val message: String,
) {

    val data = ApiErrorResponse(
        messageCode = messageCode,
        message = message
    )

    sealed class Unauthorized(
        messageCode: Int,
        message: String,
    ) : ApiError(
        statusCode = HttpStatusCode.Unauthorized,
        messageCode = messageCode,
        message = message
    ) {

        data object Token : Unauthorized(
            messageCode = 1,
            message = "Token is Invalid or has been expired"
        )

        data object ApiKey : Unauthorized(
            messageCode = 2,
            message = "Api Key is Invalid"
        )

        data object DeviceId : Unauthorized(
            messageCode = 3,
            message = "Device Id is Invalid"
        )
    }
}
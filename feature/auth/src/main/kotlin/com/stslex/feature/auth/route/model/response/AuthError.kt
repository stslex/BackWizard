package com.stslex.feature.auth.route.model.response

import com.stslex.core.core.error.ApiError
import io.ktor.http.*

enum class AuthError(
    override val message: String,
    override val messageCode: Int,
    override val statusCode: HttpStatusCode
) : ApiError {
    SAVE_USER(
        statusCode = HttpStatusCode.InternalServerError,
        messageCode = 100,
        message = "Error while saving user, try again later"
    ),
    INVALID_PASSWORD(
        statusCode = HttpStatusCode.PreconditionFailed,
        messageCode = 101,
        message = "Password is not valid"
    ),
    INVALID_PASSWORD_FORMAT(
        statusCode = HttpStatusCode.LengthRequired,
        messageCode = 102,
        message = "Password not in right format"
    ),
    USER_IS_EXIST(
        statusCode = HttpStatusCode.Conflict,
        messageCode = 103,
        message = "User with this username is already exist"
    ),
    USERNAME_IS_EXIST(
        statusCode = HttpStatusCode.Conflict,
        messageCode = 104,
        message = "User with this username is already exist"
    ),
    USER_IS_NOT_EXIST(
        statusCode = HttpStatusCode.NotAcceptable,
        messageCode = 105,
        message = "Couldn't find user with this username"
    ),
    INVALID_USERNAME(
        statusCode = HttpStatusCode.LengthRequired,
        messageCode = 106,
        message = "Username is invalid"
    ),
    INVALID_LOGIN(
        statusCode = HttpStatusCode.LengthRequired,
        messageCode = 106,
        message = "Login is invalid"
    ),
    TOKEN_REFRESH_PAYLOAD(
        statusCode = HttpStatusCode.Unauthorized,
        messageCode = 107,
        message = "Token refresh payload is invalid"
    ),
}
package com.stslex.feature.auth.domain

import com.stslex.core.core.ApiResponse
import com.stslex.feature.auth.data.AuthRepository
import com.stslex.feature.auth.data.model.AuthUserDataModel
import com.stslex.feature.auth.route.model.request.AuthRequest
import com.stslex.feature.auth.route.model.request.RegistrationRequest
import com.stslex.feature.auth.route.model.response.*
import com.stslex.feature.auth.utils.JwtGenerator

class AuthInteractorImpl(
    private val repository: AuthRepository,
    private val jwtGenerator: JwtGenerator
) : AuthInteractor {

    override suspend fun registration(
        request: RegistrationRequest
    ): ApiResponse<RegistrationResponse> {
        when {
            request.login.length !in MIN_LOGIN_LENGTH..MAX_LOGIN_LENGTH -> AuthError.INVALID_LOGIN
            request.password.length !in MIN_PASSWORD_LENGTH..MAX_PASSWORD_LENGTH -> AuthError.INVALID_PASSWORD_FORMAT
            request.username.length !in MIN_USERNAME_LENGTH..MAX_USERNAME_LENGTH -> AuthError.INVALID_USERNAME
            else -> null
        }?.let { return ApiResponse.Error(it) }

        if (repository.isLoginExist(request.login)) {
            return ApiResponse.Error(AuthError.USERNAME_IS_EXIST)
        }
        if (repository.isUsernameExist(request.username)) {
            return ApiResponse.Error(AuthError.USER_IS_EXIST)
        }

        val user = repository
            .saveUser(
                login = request.login,
                username = request.username,
                password = request.password
            )
            ?: return ApiResponse.Error(AuthError.SAVE_USER)

        val response = user.toRegistrationResponse(user.generatedToken)
        return ApiResponse.Success(response)
    }

    override suspend fun auth(request: AuthRequest): ApiResponse<AuthResponse> {
        when {
            request.login.length !in MIN_LOGIN_LENGTH..MAX_LOGIN_LENGTH -> AuthError.INVALID_USERNAME
            request.password.length !in MIN_PASSWORD_LENGTH..MAX_PASSWORD_LENGTH -> AuthError.INVALID_PASSWORD_FORMAT
            else -> null
        }?.let { return ApiResponse.Error(it) }

        val user = repository
            .getUser(request.login)
            ?: return ApiResponse.Error(AuthError.USER_IS_NOT_EXIST)

        if (user.password != request.password) {
            return ApiResponse.Error(AuthError.INVALID_PASSWORD)
        }

        val response = user.toAuthResponse(user.generatedToken)
        return ApiResponse.Success(response)
    }

    private val AuthUserDataModel.generatedToken: String
        get() = jwtGenerator
            .generate(
                uuid = uuid,
                username = username,
            )
            .ifBlank { null }
            ?: throw IllegalStateException("Token is blank")

    companion object {

        private const val MIN_PASSWORD_LENGTH = 8
        private const val MAX_PASSWORD_LENGTH = 16
        private const val MIN_LOGIN_LENGTH = 8
        private const val MAX_LOGIN_LENGTH = 16
        private const val MIN_USERNAME_LENGTH = 8
        private const val MAX_USERNAME_LENGTH = 16
    }
}
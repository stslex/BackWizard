package com.stslex.feature.auth.plugin

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.stslex.core.core.ApiError
import com.stslex.core.core.AuthConfigType
import com.stslex.core.core.Config
import com.stslex.core.core.respondError
import com.stslex.core.database.sources.user.model.UserSourceModel
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*

internal object JwtAuthPlugin {

    private const val ISSUER = "token is invalid"
    private const val PAYLOAD_UUID = "uuid"
    private const val PAYLOAD_USERNAME = "username"

    private val verifierAuth: JWTVerifier = JWT.require(Algorithm.HMAC512(Config.authSecret))
        .withIssuer(ISSUER)
        .build()

    internal fun AuthenticationConfig.configureJwt(
        getUser: suspend (uuid: String) -> UserSourceModel?
    ) {

        jwt(AuthConfigType.JWT_TOKEN_AUTH.configName) {
            verifier(JwtAuthPlugin.verifierAuth)
            realm = AuthConfigType.JWT_TOKEN_AUTH.realm
            validate { credential ->
                val uuid = credential.payload.getClaim(PAYLOAD_UUID).asString() ?: return@validate null
                val username = credential.payload.getClaim(PAYLOAD_USERNAME).asString() ?: return@validate null
                if (uuid.isBlank() || username.isBlank()) return@validate null

                val isUserValid = getUser(username)?.uuid == uuid
                val isApiKeyValid = request.header(AuthPlugin.API_KEY_HEADER_NAME) == Config.apiKey
                if (isUserValid && isApiKeyValid) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
            challenge { _, _ ->
                call.respondError(ApiError.Unauthorized.Token)
            }
        }
    }
}
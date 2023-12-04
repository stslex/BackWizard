package com.stslex.plugins.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.stslex.core.core.AuthConfigType
import com.stslex.core.core.Config
import com.stslex.core.core.respondError
import com.stslex.plugins.auth.AuthPlugin.API_KEY_HEADER_NAME
import com.stslex.plugins.auth.model.UnauthorizedError
import com.stslex.plugins.auth.presenter.AuthPluginPresenter
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*

internal object JwtAuthPlugin {

    private const val ISSUER = "token is invalid"

    private val verifierAuth: JWTVerifier = JWT.require(Algorithm.HMAC512(Config.authSecret))
        .withIssuer(ISSUER)
        .build()

    internal fun AuthenticationConfig.configureJwt(
        authPluginPresenter: AuthPluginPresenter
    ) {
        jwt(AuthConfigType.JWT_TOKEN_AUTH.configName) {
            verifier(verifierAuth)
            realm = AuthConfigType.JWT_TOKEN_AUTH.realm
            validate { credential ->
                authPluginPresenter
                    .checkJwtCredential(credential)
                    .takeIf {
                        request.header(API_KEY_HEADER_NAME) == Config.apiKey
                    }
            }
            challenge { _, _ ->
                call.respondError(UnauthorizedError.TOKEN)
            }
        }
    }
}
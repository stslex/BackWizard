package com.stslex.plugins.auth.plugin

import com.stslex.core.core.AuthConfigType
import com.stslex.core.core.Config
import com.stslex.core.core.JwtConfig
import com.stslex.core.core.respondError
import com.stslex.plugins.auth.API_KEY_HEADER_NAME
import com.stslex.plugins.auth.model.UnauthorizedError
import com.stslex.plugins.auth.presenter.AuthPluginUtil
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*

internal fun AuthenticationConfig.configureJwt(
    authPluginPresenter: AuthPluginUtil
) {
    jwt(AuthConfigType.JWT_TOKEN_AUTH.configName) {
        verifier(JwtConfig.verifierAuth)
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
    jwt(AuthConfigType.JWT_REFRESH_TOKEN_AUTH.configName) {
        verifier(JwtConfig.verifierRefresh)
        realm = AuthConfigType.JWT_REFRESH_TOKEN_AUTH.realm
        validate { credential ->
            authPluginPresenter
                .checkJwtCredential(credential)
                .takeIf {
                    request.header(API_KEY_HEADER_NAME) == Config.apiKey
                }
        }
    }
}
package com.stslex.plugins.auth

import com.stslex.core.core.AuthConfigType
import com.stslex.core.core.Config
import com.stslex.core.core.respondError
import com.stslex.plugins.auth.JwtAuthPlugin.configureJwt
import com.stslex.plugins.auth.model.UnauthorizedError
import com.stslex.plugins.auth.presenter.AuthPluginUtil
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import org.koin.ktor.ext.inject

object AuthPlugin {

    internal const val API_KEY_HEADER_NAME = "x-api-key"

    fun Application.configureAuthPlugin() {
        val authPluginPresenter by inject<AuthPluginUtil>()
        install(Authentication) {
            configureApiKey()
            configureJwt(authPluginPresenter)
        }
    }

    private fun AuthenticationConfig.configureApiKey() {
        apiKey(AuthConfigType.UN_AUTH.configName) {
            headerName = API_KEY_HEADER_NAME
            validate { apiKey ->
                ApiKeyPrincipal(apiKey).takeIf {
                    request.header(API_KEY_HEADER_NAME) == Config.apiKey
                }
            }
            challenge {
                respondError(UnauthorizedError.API_KEY)
            }
        }
    }
}


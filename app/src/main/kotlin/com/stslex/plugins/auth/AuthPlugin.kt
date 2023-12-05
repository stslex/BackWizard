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
            configureAdmin()
        }
    }

    private fun AuthenticationConfig.configureAdmin() {
        basic(AuthConfigType.ADMIN.configName) {
            validate { credential ->
                if (credential.name == "admin" || credential.password == Config.admin) {
                    AdminPrincipal(credential.name, credential.password)
                } else {
                    null
                }
            }
        }
    }

    data class AdminPrincipal(
        val name: String,
        val password: String
    ) : Principal

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


package com.stslex.feature.auth.plugin

import com.stslex.core.core.ApiError
import com.stslex.core.core.AuthConfigType
import com.stslex.core.core.Config
import com.stslex.core.core.respondError
import com.stslex.core.database.sources.user.source.UserDatabaseSource
import com.stslex.feature.auth.plugin.JwtAuthPlugin.configureJwt
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import org.koin.ktor.ext.inject

object AuthPlugin {

    internal const val API_KEY_HEADER_NAME = "x-api-key"

    fun Application.configureAuthPlugin() {
        val userDatabaseSource by inject<UserDatabaseSource>()

        install(Authentication) {
            configureApiKey()
            configureJwt(userDatabaseSource::getUserByUuid)
        }
    }

    private fun AuthenticationConfig.configureApiKey() {
        apiKey(AuthConfigType.DEFAULT.configName) {
            headerName = API_KEY_HEADER_NAME
            validate { apiKey ->
                ApiKeyPrincipal(apiKey).takeIf {
                    request.header(API_KEY_HEADER_NAME) == Config.apiKey
                }
            }
            challenge {
                respondError(ApiError.Unauthorized.ApiKey)
            }
        }
    }
}


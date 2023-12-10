package com.stslex.plugins.auth.plugin

import com.stslex.core.core.AuthConfigType
import com.stslex.core.core.Config
import com.stslex.core.core.providers.apiKey
import com.stslex.core.core.respondError
import com.stslex.plugins.auth.API_KEY_HEADER_NAME
import com.stslex.plugins.auth.model.UnauthorizedError
import io.ktor.server.auth.*
import io.ktor.server.request.*

fun AuthenticationConfig.configureApiKey() {
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
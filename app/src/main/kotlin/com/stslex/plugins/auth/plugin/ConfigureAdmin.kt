package com.stslex.plugins.auth.plugin

import com.stslex.core.core.AuthConfigType
import com.stslex.core.core.Config
import io.ktor.server.auth.*

fun AuthenticationConfig.configureAdmin() {
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
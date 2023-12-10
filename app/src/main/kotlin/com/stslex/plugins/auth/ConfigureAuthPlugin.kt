package com.stslex.plugins.auth

import com.stslex.plugins.auth.plugin.configureAdmin
import com.stslex.plugins.auth.plugin.configureApiKey
import com.stslex.plugins.auth.plugin.configureJwt
import com.stslex.plugins.auth.presenter.AuthPluginUtil
import io.ktor.server.application.*
import io.ktor.server.auth.*
import org.koin.ktor.ext.inject

const val API_KEY_HEADER_NAME = "x-api-key"

fun Application.configureAuthPlugin() {
    val authPluginPresenter by inject<AuthPluginUtil>()
    install(Authentication) {
        configureApiKey()
        configureJwt(authPluginPresenter)
        configureAdmin()
    }
}

package com.stslex.plugins.auth.presenter

import io.ktor.server.auth.jwt.*

interface AuthPluginPresenter {

    suspend fun checkJwtCredential(credential: JWTCredential): JWTPrincipal?
}


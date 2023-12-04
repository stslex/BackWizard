package com.stslex.plugins.auth.presenter

import io.ktor.server.auth.jwt.*

interface AuthPluginUtil {

    suspend fun checkJwtCredential(credential: JWTCredential): JWTPrincipal?
}

package com.stslex.plugins.auth.presenter

import com.stslex.core.database.sources.user.source.UserDatabaseSource
import com.stslex.core.core.JwtConfig.PAYLOAD_USERNAME
import com.stslex.core.core.JwtConfig.PAYLOAD_UUID
import io.ktor.server.auth.jwt.*

class AuthPluginUtilImpl(
    private val userDatabaseSource: UserDatabaseSource
) : AuthPluginUtil {

    override suspend fun checkJwtCredential(credential: JWTCredential): JWTPrincipal? {
        val uuid = credential
            .payload
            .getClaim(PAYLOAD_UUID)
            .asString()
            ?: return null

        val username = credential
            .payload
            .getClaim(PAYLOAD_USERNAME)
            .asString()
            ?: return null

        if (uuid.isBlank() || username.isBlank()) return null

        val user = userDatabaseSource.getUserByUuid(uuid) ?: return null
        return if (user.username == username) {
            JWTPrincipal(credential.payload)
        } else null
    }
}
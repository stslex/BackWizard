package com.stslex.plugins.auth.presenter

import com.stslex.core.core.JwtConfig.PAYLOAD_USERNAME
import com.stslex.core.core.JwtConfig.PAYLOAD_UUID
import com.stslex.core.database.sources.user.source.UserDatabaseSource
import io.ktor.server.auth.jwt.*

class AuthPluginUtilImpl(
    private val userDatabaseSource: UserDatabaseSource
) : AuthPluginUtil {

    override suspend fun checkJwtCredential(credential: JWTCredential): JWTPrincipal? {
        /** Expire time*/
        if (credential.payload.expiresAt.time < System.currentTimeMillis()) return null

        /** UUID not null or blank*/
        val uuid = credential
            .payload
            .getClaim(PAYLOAD_UUID)
            .asString()
            .takeIf { it.isNotBlank() }
            ?: return null

        /** Username not null or blank*/
        val username = credential
            .payload
            .getClaim(PAYLOAD_USERNAME)
            .asString()
            ?.takeIf { it.isNotBlank() }
            ?: return null

        val user = userDatabaseSource.getUserByUuid(uuid)

        /** User is valid and exist in DataBase */
        return if (user?.username == username) {
            JWTPrincipal(credential.payload)
        } else {
            null
        }
    }
}
package com.stslex.plugins.auth.presenter

import com.stslex.core.database.sources.user.source.UserDatabaseSource
import io.ktor.server.auth.jwt.*

class AuthPluginPresenterImpl(
    private val userDatabaseSource: UserDatabaseSource
) : AuthPluginPresenter {

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

    companion object {
        private const val PAYLOAD_UUID = "uuid"
        private const val PAYLOAD_USERNAME = "username"
    }
}
package com.stslex.feature.auth.utils

import com.auth0.jwt.JWT
import com.stslex.core.core.JwtConfig
import com.stslex.core.core.JwtConfig.PAYLOAD_USERNAME
import com.stslex.core.core.JwtConfig.PAYLOAD_UUID
import com.stslex.core.core.JwtConfig.algorithm
import java.util.*

class JwtGeneratorImpl : JwtGenerator {

    private val expiration: Date
        get() = Date(System.currentTimeMillis() + VALIDITY_IN_MS)

    override fun generate(
        uuid: String,
        username: String
    ): String = JWT.create()
        .withSubject(JWT_SUBJECT)
        .withIssuer(JwtConfig.ISSUER)
        .withJWTId(uuid)
        .withClaim(PAYLOAD_UUID, uuid)
        .withClaim(PAYLOAD_USERNAME, username)
        .withExpiresAt(expiration)
        .sign(algorithm)

    companion object {
        private const val JWT_SUBJECT = "Authentication"
        private const val VALIDITY_IN_MS = 36_000_00 * 24
    }
}
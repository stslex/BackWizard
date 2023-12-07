package com.stslex.feature.auth.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.interfaces.DecodedJWT
import com.stslex.core.core.JwtConfig.PAYLOAD_USERNAME
import com.stslex.core.core.JwtConfig.PAYLOAD_UUID
import java.util.*

class JwtGeneratorImpl : JwtGenerator {

    override fun generateToken(
        uuid: String,
        username: String,
        type: JwtType
    ): String = JWT.create()
        .withSubject(type.subject)
        .withIssuer(type.issuer)
        .withJWTId(uuid)
        .withClaim(PAYLOAD_UUID, uuid)
        .withClaim(PAYLOAD_USERNAME, username)
        .withExpiresAt(Date(System.currentTimeMillis() + type.validity))
        .sign(type.algorithm)

    override fun getJwt(token: String): DecodedJWT = JWT.decode(token)
}
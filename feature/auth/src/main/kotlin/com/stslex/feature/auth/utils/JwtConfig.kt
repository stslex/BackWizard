package com.stslex.feature.auth.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.stslex.core.core.Config

object JwtConfig {

    const val ISSUER = "token is invalid"
    const val PAYLOAD_UUID = "payload_uuid"
    const val PAYLOAD_USERNAME = "payload_username"

    val algorithm = Algorithm.HMAC512(Config.authSecret)
    val verifierAuth: JWTVerifier = JWT.require(algorithm)
        .withIssuer(ISSUER)
        .build()
}
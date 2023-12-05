package com.stslex.core.core

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.util.pipeline.*

object JwtConfig {

    const val ISSUER = "token is invalid"
    const val PAYLOAD_UUID = "payload_uuid"
    const val PAYLOAD_USERNAME = "payload_username"

    val algorithm = Algorithm.HMAC512(Config.authSecret)
    val verifierAuth: JWTVerifier = JWT.require(algorithm)
        .withIssuer(ISSUER)
        .build()

    val PipelineContext<Unit, ApplicationCall>.payload_uuid: String?
        get() = call.authentication.principal<JWTPrincipal>()
            ?.payload
            ?.getClaim(PAYLOAD_UUID)
            ?.asString()

    val PipelineContext<Unit, ApplicationCall>.payload_username: String?
        get() = call.authentication.principal<JWTPrincipal>()
            ?.payload
            ?.getClaim(PAYLOAD_USERNAME)
            ?.asString()
}
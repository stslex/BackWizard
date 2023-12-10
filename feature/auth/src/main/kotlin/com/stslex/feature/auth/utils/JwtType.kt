package com.stslex.feature.auth.utils

import com.auth0.jwt.algorithms.Algorithm
import com.stslex.core.core.JwtConfig

private const val ONE_DAY = 36_000_00L * 24

enum class JwtType(
    val subject: String,
    val validity: Long,
    val issuer: String,
    val algorithm: Algorithm
) {
    ACCESS(
        subject = "Authentication",
        validity = ONE_DAY,
        issuer = JwtConfig.ISSUER_AUTH_TOKEN,
        algorithm = JwtConfig.algorithm
    ),
    REFRESH(
        subject = "Refresh",
        validity = ONE_DAY * 10,
        issuer = JwtConfig.ISSUER_REFRESH_TOKEN,
        algorithm = JwtConfig.refreshAlgorithm
    ),
}
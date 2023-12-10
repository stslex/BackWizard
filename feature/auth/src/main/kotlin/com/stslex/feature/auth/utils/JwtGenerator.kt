package com.stslex.feature.auth.utils

import com.auth0.jwt.interfaces.DecodedJWT

interface JwtGenerator {

    fun generateToken(
        uuid: String,
        username: String,
        type: JwtType
    ): String

    fun getJwt(token: String): DecodedJWT
}

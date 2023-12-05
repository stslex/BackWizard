package com.stslex.core.core

enum class AuthConfigType(
    val configName: String,
    val realm: String
) {
    UN_AUTH(
        configName = "default.auth",
        realm = "Access to auth"
    ),
    JWT_TOKEN_AUTH(
        configName = "jwt.token.auth",
        realm = "Access to the '/' path"
    ),
    ADMIN(
        configName = "admin",
        realm = "Access to the '/admin' path"
    )
}
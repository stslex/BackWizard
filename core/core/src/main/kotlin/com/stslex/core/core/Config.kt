package com.stslex.core.core

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*

object Config : HoconApplicationConfig(ConfigFactory.load("application.conf")) {

    private const val POSTGRES_URL = "postgres.url"
    private const val POSTGRES_USER = "postgres.user"
    private const val POSTGRES_PASSWORD = "postgres.password"
    private const val AUTH_SECRET = "jwt.auth.secret"
    private const val API_KEY = "apiKey"

    val postgresUrl by lazy { property(POSTGRES_URL).getString() }
    val postgresUser by lazy { property(POSTGRES_USER).getString() }
    val postgresPassword by lazy { property(POSTGRES_PASSWORD).getString() }
    val apiKey by lazy { property(API_KEY).getString() }
    val authSecret by lazy { property(AUTH_SECRET).getString() }
}

package com.stslex.core.core

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*

object Config : HoconApplicationConfig(ConfigFactory.load("application.conf")) {

    private const val POSTGRES_URL = "postgres.url"
    private const val POSTGRES_USER = "postgres.user"
    private const val POSTGRES_PASSWORD = "postgres.password"

    val postgresUrl: String by lazy { property(POSTGRES_URL).getString() }
    val postgresUser: String by lazy { property(POSTGRES_USER).getString() }
    val postgresPassword: String by lazy { property(POSTGRES_PASSWORD).getString() }
}

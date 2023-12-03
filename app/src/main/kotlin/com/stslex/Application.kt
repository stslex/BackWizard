package com.stslex

import com.stslex.db.DatabaseFactory
import com.stslex.plugins.routingPlugin
import com.stslex.plugins.serializationPlugin
import com.stslex.plugins.templatingPlugin
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(
        factory = Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module
    )
        .start(wait = true)
}

private fun Application.module() {
    DatabaseFactory.initDatabase()
    serializationPlugin()
    routingPlugin()
    templatingPlugin()
}

val config: HoconApplicationConfig = HoconApplicationConfig(ConfigFactory.load("application.conf"))
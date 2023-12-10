package com.stslex

import com.stslex.core.database.plugin.DatabasePlugin.configureDatabase
import com.stslex.plugins.auth.configureAuthPlugin
import com.stslex.plugins.configureDI
import com.stslex.plugins.routingPlugin
import com.stslex.plugins.serializationPlugin
import com.stslex.plugins.templatingPlugin
import io.ktor.server.application.*
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
    configureDI()
    configureDatabase()
    configureAuthPlugin()
    serializationPlugin()
    routingPlugin()
    templatingPlugin()
}

package com.stslex.plugins

import com.stslex.core.database.di.databaseModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureDI() {
    install(Koin) {
        slf4jLogger()
        modules(
            databaseModule
        )
    }
}
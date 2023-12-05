package com.stslex.plugins

import com.stslex.core.database.di.databaseModule
import com.stslex.feature.auth.di.authModule
import com.stslex.feature.user.di.userModule
import com.stslex.plugins.auth.di.authPluginModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureDI() {
    install(Koin) {
        slf4jLogger()
        modules(
            databaseModule,
            authPluginModule,
            authModule,
            userModule
        )
    }
}
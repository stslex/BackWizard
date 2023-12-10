package com.stslex.plugins.auth.plugin

import io.ktor.server.auth.*

data class ApiKeyPrincipal(
    val apiKey: String
) : Principal
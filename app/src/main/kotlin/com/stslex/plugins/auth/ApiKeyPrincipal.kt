package com.stslex.plugins.auth

import io.ktor.server.auth.*

data class ApiKeyPrincipal(
    val apiKey: String
) : Principal
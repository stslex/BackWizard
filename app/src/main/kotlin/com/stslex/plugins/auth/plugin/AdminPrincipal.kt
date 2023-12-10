package com.stslex.plugins.auth.plugin

import io.ktor.server.auth.*

data class AdminPrincipal(
    val name: String,
    val password: String
) : Principal

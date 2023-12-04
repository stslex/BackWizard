package com.stslex.feature.auth.utils

interface JwtGenerator {

    fun generate(uuid: String, username: String): String
}

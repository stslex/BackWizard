package com.stslex.core.database.sources.user.model

data class NewUserSourceModel(
    val login: String,
    val password: String,
    val username: String
)
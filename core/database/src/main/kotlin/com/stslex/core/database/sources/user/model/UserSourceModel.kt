package com.stslex.core.database.sources.user.model

data class UserSourceModel(
    val uuid: String,
    val login: String,
    val username: String,
    val password: String,
    val nickname: String
)

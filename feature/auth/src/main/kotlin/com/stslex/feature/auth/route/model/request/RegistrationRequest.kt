package com.stslex.feature.auth.route.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRequest(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)
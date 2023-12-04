package com.stslex.feature.auth.route.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegistrationResponse(
    @SerialName("token")
    val token: String,
    @SerialName("uuid")
    val uuid: String,
    @SerialName("username")
    val username: String,
)
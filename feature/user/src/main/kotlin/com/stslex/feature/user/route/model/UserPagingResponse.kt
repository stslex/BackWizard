package com.stslex.feature.user.route.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserPagingResponse(
    @SerialName("result")
    val result: List<UserResponse>,
    @SerialName("total")
    val total: Int,
)

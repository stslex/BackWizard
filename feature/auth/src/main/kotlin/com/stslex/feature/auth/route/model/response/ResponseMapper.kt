package com.stslex.feature.auth.route.model.response

import com.stslex.feature.auth.data.model.AuthUserDataModel

fun AuthUserDataModel.toAuthResponse(
    accessToken: String,
    refreshToken: String
): AuthResponse = AuthResponse(
    token = accessToken,
    refreshToken = refreshToken,
    uuid = uuid,
    username = username
)

fun AuthUserDataModel.toRegistrationResponse(
    accessToken: String,
    refreshToken: String
): RegistrationResponse = RegistrationResponse(
    token = accessToken,
    refreshToken = refreshToken,
    uuid = uuid,
    username = username
)
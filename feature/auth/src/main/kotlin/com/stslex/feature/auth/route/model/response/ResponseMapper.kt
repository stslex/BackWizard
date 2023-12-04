package com.stslex.feature.auth.route.model.response

import com.stslex.feature.auth.data.model.AuthUserDataModel

fun AuthUserDataModel.toAuthResponse(
    token: String
): AuthResponse = AuthResponse(
    token = token,
    uuid = uuid,
    username = username
)

fun AuthUserDataModel.toRegistrationResponse(
    token: String
): RegistrationResponse = RegistrationResponse(
    token = token,
    uuid = uuid,
    username = username
)
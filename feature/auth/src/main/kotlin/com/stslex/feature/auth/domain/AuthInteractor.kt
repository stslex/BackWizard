package com.stslex.feature.auth.domain

import com.stslex.core.core.ApiResponse
import com.stslex.feature.auth.route.model.request.AuthRequest
import com.stslex.feature.auth.route.model.request.RegistrationRequest
import com.stslex.feature.auth.route.model.response.AuthResponse
import com.stslex.feature.auth.route.model.response.RegistrationResponse

interface AuthInteractor {

    suspend fun registration(request: RegistrationRequest): ApiResponse<RegistrationResponse>

    suspend fun auth(request: AuthRequest): ApiResponse<AuthResponse>
}

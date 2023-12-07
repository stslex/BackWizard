package com.stslex.feature.auth.route

import com.stslex.core.core.JwtConfig.payload_uuid
import com.stslex.core.core.processCall
import com.stslex.core.core.respondCreated
import com.stslex.core.core.respondError
import com.stslex.core.core.respondOK
import com.stslex.feature.auth.domain.AuthInteractor
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

private const val AUTH_HOST = "auth"

fun Route.authRoute() {
    val interactor by inject<AuthInteractor>()
    route(AUTH_HOST) {
        post("registration") {
            processCall(
                action = {
                    interactor.registration(call.receive())
                },
                onError = call::respondError,
                onSuccess = call::respondCreated
            )
        }
        post("login") {
            processCall(
                action = {
                    interactor.auth(call.receive())
                },
                onError = call::respondError,
                onSuccess = call::respondOK
            )
        }
    }
}

fun Route.refreshTokenRoute() {
    val interactor by inject<AuthInteractor>()
    get("refresh") {
        payload_uuid
        processCall(
            action = {
                interactor.refreshToken(call.receive())
            },
            onError = call::respondError,
            onSuccess = call::respondOK
        )
    }
}

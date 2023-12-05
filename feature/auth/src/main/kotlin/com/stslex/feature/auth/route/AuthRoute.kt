package com.stslex.feature.auth.route

import com.stslex.core.core.*
import com.stslex.feature.auth.domain.AuthInteractor
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

private const val AUTH_HOST = "${Config.API_ENDPOINT}/auth"

fun Route.authRoute() {
    val interactor by inject<AuthInteractor>()
    post("$AUTH_HOST/registration") {
        processCall(
            action = {
                interactor.registration(call.receive())
            },
            onError = call::respondError,
            onSuccess = call::respondCreated
        )
    }
    post("$AUTH_HOST/login") {
        processCall(
            action = {
                interactor.auth(call.receive())
            },
            onError = call::respondError,
            onSuccess = call::respondOK
        )
    }
}
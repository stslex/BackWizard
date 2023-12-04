package com.stslex.feature.auth.route

import com.stslex.core.core.*
import com.stslex.core.core.AuthConfigType.UN_AUTH
import com.stslex.feature.auth.domain.AuthInteractor
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

private const val AUTH_HOST = "${Config.API_ENDPOINT}/auth"

fun Route.authRoute() {
    val interactor by inject<AuthInteractor>()
    authenticate(UN_AUTH.configName) {
        post("$AUTH_HOST/registration") {
            interactor.registration(call.receive())
                .onSuccess(call::respondCreated)
                .onError(call::respondError)
        }
        post("$AUTH_HOST/login") {
            interactor.auth(call.receive())
                .onSuccess(call::respondCreated)
                .onError(call::respondError)
        }
    }
}
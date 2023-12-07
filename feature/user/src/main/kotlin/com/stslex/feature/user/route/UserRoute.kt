package com.stslex.feature.user.route

import com.stslex.core.core.JwtConfig.payload_username
import com.stslex.core.core.JwtConfig.payload_uuid
import com.stslex.core.core.onError
import com.stslex.core.core.onSuccess
import com.stslex.core.core.respondError
import com.stslex.core.core.respondOK
import com.stslex.feature.user.domain.UserInteractor
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.util.*
import org.koin.ktor.ext.inject

private const val USER_ENDPOINT = "user"

private const val PAGE_NUMBER_ATTRIBUTE = "page_number"
private const val PAGE_SIZE_ATTRIBUTE = "page_size"

fun Route.userRoute() {
    val interactor by inject<UserInteractor>()
    route(USER_ENDPOINT) {
        get("{uuid}") {
            interactor.getUserByUuid(call.parameters["uuid"].orEmpty())
                .onSuccess(call::respondOK)
                .onError(call::respondError)
        }
        get("{username}") {
            interactor.getUserByUsername(call.parameters["username"].orEmpty())
                .onSuccess(call::respondOK)
                .onError(call::respondError)
        }
        get {
            val uuid = payload_uuid
            val username = payload_username
            interactor.getUserFullInfo(uuid, username)
                .onSuccess(call::respondOK)
                .onError(call::respondError)
        }
        get("list") {
            interactor.getUsers(
                page = call.attributes.getOrNull(AttributeKey(PAGE_NUMBER_ATTRIBUTE)) ?: 0,
                pageSize = call.attributes.getOrNull(AttributeKey(PAGE_SIZE_ATTRIBUTE)) ?: 10
            )
                .onSuccess(call::respondOK)
                .onError(call::respondError)
        }
    }
}

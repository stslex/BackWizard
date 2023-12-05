package com.stslex.feature.user.route

import com.stslex.core.core.*
import com.stslex.core.core.JwtConfig.payload_username
import com.stslex.core.core.JwtConfig.payload_uuid
import com.stslex.feature.user.domain.UserInteractor
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.util.*
import org.koin.ktor.ext.inject

private const val USER_ENDPOINT = "${Config.API_ENDPOINT}/user"

private const val PAGE_NUMBER_ATTRIBUTE = "page_number"
private const val PAGE_SIZE_ATTRIBUTE = "page_size"

fun Route.userRoute() {
    val interactor by inject<UserInteractor>()

    get("$USER_ENDPOINT/{uuid}") {
        interactor.getUserByUuid(call.parameters["uuid"].orEmpty())
            .onSuccess(call::respondOK)
            .onError(call::respondError)
    }
    get("$USER_ENDPOINT/{username}") {
        interactor.getUserByUsername(call.parameters["username"].orEmpty())
            .onSuccess(call::respondOK)
            .onError(call::respondError)
    }
    get(USER_ENDPOINT) {
        val uuid = payload_uuid
        val username = payload_username
        interactor.getUserFullInfo(uuid, username)
            .onSuccess(call::respondOK)
            .onError(call::respondError)
    }
    get("$USER_ENDPOINT/list") {
        interactor.getUsers(
            page = call.attributes.getOrNull(AttributeKey(PAGE_NUMBER_ATTRIBUTE)) ?: 0,
            pageSize = call.attributes.getOrNull(AttributeKey(PAGE_SIZE_ATTRIBUTE)) ?: 10
        )
            .onSuccess(call::respondOK)
            .onError(call::respondError)
    }
}

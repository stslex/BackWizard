package com.stslex.feature.user.route.model

import com.stslex.feature.user.data.model.UserDataModel

fun UserDataModel.toResponse() = UserResponse(
    uuid = uuid,
    username = username,
    nickname = nickname,
)

fun UserDataModel.toFullResponse() = UserFullResponse(
    uuid = uuid,
    username = username,
    nickname = nickname,
)

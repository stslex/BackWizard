package com.stslex.feature.user.data.model

import com.stslex.core.database.sources.user.model.UserSourceModel

fun UserSourceModel.toData() = UserDataModel(
    uuid = uuid,
    username = username,
    nickname = nickname,
)
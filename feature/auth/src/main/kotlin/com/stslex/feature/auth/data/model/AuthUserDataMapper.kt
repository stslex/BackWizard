package com.stslex.feature.auth.data.model

import com.stslex.core.database.sources.user.model.UserSourceModel

fun UserSourceModel.toData() = AuthUserDataModel(
    uuid = uuid,
    username = username,
    password = password
)
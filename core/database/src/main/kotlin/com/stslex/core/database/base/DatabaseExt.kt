package com.stslex.core.database.base

import java.util.*

val String.toUUID: UUID?
    get() = try {
        UUID.fromString(this)
    } catch (e: IllegalArgumentException) {
        null
    }

package com.stslex.core.database.base

import java.util.*

val String.toUUIDorNull: UUID?
    get() = try {
        UUID.fromString(this)
    } catch (e: IllegalArgumentException) {
        null
    }

val String.toUUID: UUID
    get() = try {
        UUID.fromString(this)
    } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException("String $this is not UUID")
    }

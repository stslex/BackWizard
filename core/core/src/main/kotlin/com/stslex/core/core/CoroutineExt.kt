package com.stslex.core.core

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

suspend fun <T, R> Iterable<T>.asyncMap(
    transform: suspend (T) -> R
): List<R> = coroutineScope {
    map { async { transform(it) } }.awaitAll()
}

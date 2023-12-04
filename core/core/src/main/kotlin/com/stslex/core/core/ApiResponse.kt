package com.stslex.core.core

import com.stslex.core.core.error.ApiCommonError
import com.stslex.core.core.error.ApiError

sealed interface ApiResponse<out T> {

    data class Success<out T>(
        val data: T
    ) : ApiResponse<T>

    data class Error<out T>(
        val apiError: ApiError
    ) : ApiResponse<T>

}

suspend inline fun <T> ApiResponse<T>.onSuccess(
    crossinline action: suspend (T) -> Unit
): ApiResponse<T> = apply {
    if (this is ApiResponse.Success<T>) {
        action(data)
    }
}

suspend inline fun <T> ApiResponse<T>.onError(
    crossinline action: suspend (ApiError) -> Unit
): ApiResponse<T> = apply {
    if (this is ApiResponse.Error<T>) {
        action(apiError)
    }
}

suspend inline fun <T> processCall(
    crossinline action: suspend () -> ApiResponse<T>,
    crossinline onError: suspend (ApiError) -> Unit,
    crossinline onSuccess: suspend (T) -> Unit
) {
    runCatching { action() }
        .onFailure { onError(ApiCommonError.INTERNAL_ERROR) }
        .onSuccess { response ->
            response
                .onSuccess(onSuccess)
                .onError(onError)
        }
}

package com.example.domain

sealed class Result<out E, out S> {

    data class Success<out S>(val success: S) : Result<Nothing, S>()
    data class Error<out L>(val error: L) : Result<L, Nothing>()

    val isSuccess get() = this is Success<S>
    val isError get() = this is Error<E>

    fun <R> success(b: R) = Success(b)

    fun <L> error(a: L) = Error(a)

    fun fold(
        fnL: (E) -> Any,
        fnR: (S) -> Any
    ): Any =
        when (this) {
            is Error -> fnL(error)
            is Success -> fnR(success)
        }
}

fun <E, S> Result<E, S>.getOrNull(): S? =
    when (this) {
        is Result.Success -> success
        else -> null
    }
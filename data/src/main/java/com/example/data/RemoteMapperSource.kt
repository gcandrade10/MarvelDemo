package com.example.data

import android.util.Log
import com.example.domain.Failure
import com.example.domain.Result
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response


interface RemoteMapperSource {

    suspend fun <E, M> request(
        call: suspend () -> Response<E>,
        handleSuccess: (E, Int) -> M,
        handleError: (Int, ServerErrorEntity?) -> Failure = { _, _ -> Failure() }
    ): Result<Failure, M> =
        try {
            val response = call()
            if (response.code().isHttpError()) {
                val responseBody: E = response.body()!!
                Result.Success(handleSuccess(responseBody, response.code()))
            } else {
                Result.Error(handleError(response.code(), response.toServerErrorEntity()))
            }
        } catch (exception: Exception) {
            toFailure(handleError)
        }

    suspend fun <E, M> requestNullable(
        call: suspend () -> Response<E?>,
        handleSuccess: (E?, Int) -> M,
        handleError: (Int, ServerErrorEntity?) -> Failure = { _, _ -> Failure() }
    ): Result<Failure, M> =
        try {
            val response = call()
            if (response.code().isHttpError()) {
                val responseBody: E? = response.body()
                Result.Success(handleSuccess(responseBody, response.code()))
            } else {
                Result.Error(handleError(response.code(), response.toServerErrorEntity()))
            }
        } catch (exception: Exception) {
            toFailure(handleError)
        }

    private fun Int.isHttpError() = this < ERROR_HTTP

    companion object {
        private const val ERROR_HTTP = 400
    }

}

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

internal fun <E> Response<E>.toServerErrorEntity(): ServerErrorEntity? =
    try {
        moshi.adapter(ServerErrorEntity::class.java).fromJson(errorBody()!!.source())
    } catch (exception: Exception) {
        null
    }

internal fun toFailure(handleError: (Int, ServerErrorEntity?) -> Failure): Result.Error<Failure> {
    return Result.Error(
        Failure()
    )
}
package com.example.domain

import com.example.domain.repository.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

interface UseCase<in Params, out Type> where Type : Any {

    suspend operator fun invoke(
        params: Params,
        context: CoroutineContext = Dispatchers.IO
    ): Result<Failure, Type> =
        withContext(context) {
            invoke(params, context)
        }

    object None
}

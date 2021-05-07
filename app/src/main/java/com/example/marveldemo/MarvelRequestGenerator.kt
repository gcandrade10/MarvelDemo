package com.example.marveldemo

import com.example.data.CharactersApi
import com.example.marveldemo.MarvelRequestGenerator.MD5_ALGORITHM
import com.example.marveldemo.MarvelRequestGenerator.PAD_CHAR
import com.example.marveldemo.MarvelRequestGenerator.RADIX_VALUE
import com.example.marveldemo.MarvelRequestGenerator.STRING_LENGTH
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.math.BigInteger
import java.security.MessageDigest

object MarvelRequestGenerator {

    const val MD5_ALGORITHM = "MD5"
    const val RADIX_VALUE = 16
    const val STRING_LENGTH = 16
    const val PAD_CHAR = '0'

    private val HASH_ARG = "hash"
    private val PUBLIC_API_KEY_ARG = "apikey"
    private val TS = "ts"
    private val TS_VALUE = "1"
    private val HASH_VALUE: String =
        (TS_VALUE + BuildConfig.PRIVATE_API_KEY_VALUE + BuildConfig.PUBLIC_API_KEY_VALUE).md5()

    fun getClient(): CharactersApi {
        val builder = OkHttpClient.Builder().addInterceptor { chain ->
            val defaultRequest = chain.request()

            val defaultHttpUrl = defaultRequest.url
            val httpUrl = defaultHttpUrl.newBuilder()
                .addQueryParameter(PUBLIC_API_KEY_ARG, BuildConfig.PUBLIC_API_KEY_VALUE)
                .addQueryParameter(HASH_ARG, HASH_VALUE)
                .addQueryParameter(TS, TS_VALUE)
                .build()

            val requestBuilder = defaultRequest.newBuilder().url(httpUrl)

            chain.proceed(requestBuilder.build())
        }
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(OkHttpProfilerInterceptor())
        }
        val client = builder.build()
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://gateway.marvel.com/")
            .client(client).build().create(CharactersApi::class.java)
    }
}


private fun String.md5(): String {
    val md = MessageDigest.getInstance(MD5_ALGORITHM)
    return BigInteger(1, md.digest(toByteArray())).toString(RADIX_VALUE)
        .padStart(STRING_LENGTH, PAD_CHAR)
}

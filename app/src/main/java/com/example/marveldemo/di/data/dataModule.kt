package com.example.marveldemo.di.data

import com.example.data.CharactersApi
import com.example.data.repository.CharactersRepositoryImpl
import com.example.data.source.CharactersRemoteSource
import com.example.data.source.CharactersRemoteSourceImpl
import com.example.data.source.remote.mapper.CharactersRemoteMapper
import com.example.data.source.remote.mapper.CharactersRemoteMapperImpl
import com.example.domain.repository.CharactersRepository
import com.example.marveldemo.BuildConfig
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

internal val dataModule = module {
    single<CharactersApi> {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(OkHttpProfilerInterceptor())
        }
        val client = builder.build()
        Retrofit.Builder().client(client).build().create(CharactersApi::class.java)
    }
    single<CharactersRemoteMapper> { CharactersRemoteMapperImpl() }

    single<CharactersRemoteSource> {
        CharactersRemoteSourceImpl(api = get(), mapper = get())
    }

    single<CharactersRepository> {
        CharactersRepositoryImpl(
            charactersRemoteSource = get()
        )
    }
}
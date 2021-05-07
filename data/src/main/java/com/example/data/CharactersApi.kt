package com.example.data

import com.example.data.entity.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersApi {
    @GET("/v1/public/characters")
    suspend fun getCharacters(): Response<CharacterResponse>

    @GET("/v1/public/characters/{id}")
    suspend fun getCharacterDetail(@Path("id") id: Int): Response<CharacterResponse>
}

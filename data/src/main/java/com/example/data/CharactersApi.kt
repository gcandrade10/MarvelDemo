package com.example.data

import com.example.data.entity.CharacterResponse
import com.example.domain.models.CharactersModel
import retrofit2.Response
import retrofit2.http.GET

interface CharactersApi {
    @GET("/characters")
    suspend fun getCharacters(): Response<CharacterResponse>

   @GET("/characters/{id}")
    suspend fun getCharacterDetail(id:Int): Response<CharacterResponse>
}

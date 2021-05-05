package com.example.domain.repository

import com.example.domain.Failure
import com.example.domain.Result
import com.example.domain.models.CharacterDetailModel
import com.example.domain.models.CharactersModel

interface CharactersRepository {
    suspend fun getCharacters(): Result<Failure, CharactersModel>
    suspend fun getCharacterDetail(id: Int): Result<Failure, CharacterDetailModel>
}



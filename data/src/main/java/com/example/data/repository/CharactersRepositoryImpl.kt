package com.example.data.repository

import com.example.data.source.CharactersRemoteSource
import com.example.domain.Failure
import com.example.domain.Result
import com.example.domain.getOrNull
import com.example.domain.models.CharacterDetailModel
import com.example.domain.models.CharactersModel
import com.example.domain.repository.CharactersRepository

class CharactersRepositoryImpl(
    private val charactersRemoteSource: CharactersRemoteSource

) : CharactersRepository {
    override suspend fun getCharacters(): Result<Failure, CharactersModel> {
        return charactersRemoteSource.getCharacters().getOrNull()?.let {
            Result.Success(it)
        } ?: Result.Error(Failure())
    }

    override suspend fun getCharacterDetail(id: Int): Result<Failure, CharacterDetailModel> {
        return charactersRemoteSource.getCharacterDetail(id).getOrNull()?.let {
            Result.Success(it)
        } ?: Result.Error(Failure())
    }
}
package com.example.data.source

import com.example.data.CharactersApi
import com.example.data.RemoteMapperSource
import com.example.data.source.remote.mapper.CharactersRemoteMapper
import com.example.domain.Failure
import com.example.domain.Result
import com.example.domain.models.CharacterDetailModel
import com.example.domain.models.CharactersModel

interface CharactersRemoteSource : RemoteMapperSource {
    suspend fun getCharacters(): Result<Failure, CharactersModel>
    suspend fun getCharacterDetail(id: Int): Result<Failure, CharacterDetailModel>
}

class CharactersRemoteSourceImpl(
    private val api: CharactersApi,
    private val mapper: CharactersRemoteMapper
) : CharactersRemoteSource {
    override suspend fun getCharacters(): Result<Failure, CharactersModel> = request({
        api.getCharacters()
    }, { entity, _ ->
        mapper.toModel(entity)
    })

    override suspend fun getCharacterDetail(id: Int): Result<Failure, CharacterDetailModel> =
        request({
            api.getCharacterDetail(id)
        }, { entity, _ ->
            mapper.toDetailModel(entity)
        })
}

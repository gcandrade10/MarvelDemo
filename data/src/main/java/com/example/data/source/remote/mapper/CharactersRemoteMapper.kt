package com.example.data.source.remote.mapper

import com.example.data.entity.CharacterEntity
import com.example.data.entity.CharacterResponse
import com.example.domain.models.CharacterDetailModel
import com.example.domain.models.CharacterModel
import com.example.domain.models.CharactersModel

interface CharactersRemoteMapper {
    fun toModel(entity: CharacterResponse): CharactersModel
    fun toDetailModel(entity: CharacterResponse): CharacterDetailModel
}

class CharactersRemoteMapperImpl : CharactersRemoteMapper {
    override fun toModel(entity: CharacterResponse) = CharactersModel(
        offset = entity.data?.offset ?: 0,
        hasMore = entity.data?.offset ?: -1 < entity.data?.total ?: 0,
        list = entity.data?.list?.map { item ->
            CharacterModel(
                id = item.id ?: 0,
                name = item.name ?: "",
                image = parseImage(item)
            )
        } ?: emptyList()
    )

    override fun toDetailModel(entity: CharacterResponse): CharacterDetailModel {
        val first = entity.data?.list?.firstOrNull()
        return CharacterDetailModel(
            id = first?.id ?: 0,
            name = first?.name ?: "",
            image = parseImage(first),
            description = first?.description ?: ""
        )
    }

    private fun parseImage(item: CharacterEntity?): String {
        val image = (item?.thumbnail?.path ?: "") + "." + item?.thumbnail?.extension
        return image.replace("http", "https")
    }
}

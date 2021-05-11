package com.example.data.source.remote.mapper

import com.example.data.entity.CharacterEntity
import com.example.data.entity.CharacterResponse
import com.example.data.entity.DataResponse
import com.example.data.entity.ThumbnailEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CharactersRemoteMapperImplTest {

    private lateinit var charactersRemoteMapper: CharactersRemoteMapper

    private val charactersEntityMockEmpty = CharacterResponse(DataResponse(0, 0, emptyList()))
    private val charactersEntityMock = CharacterResponse(
        DataResponse(
            1, 1, listOf(
                CharacterEntity(1, "name", "description", ThumbnailEntity("path", "extension"))
            )
        )
    )

    @BeforeEach
    fun setUp() {
        charactersRemoteMapper = CharactersRemoteMapperImpl()
    }

    @Test
    fun `toModel should create a new CharactersModel entity empty`() {

        val model = charactersRemoteMapper.toModel(charactersEntityMockEmpty)

        assertEquals(charactersEntityMockEmpty.data?.list?.size, model.list.size)
        assertEquals(charactersEntityMockEmpty.data?.offset, model.offset)
        assertEquals(
            charactersEntityMockEmpty.data?.offset ?: -1 < charactersEntityMockEmpty.data?.total ?: 0,
            model.hasMore
        )

    }

    @Test
    fun `toModel should create a new CharactersModel entity`() {

        val model = charactersRemoteMapper.toModel(charactersEntityMock)

        assertEquals(charactersEntityMock.data?.list?.size, model.list.size)
        assertEquals(charactersEntityMock.data?.offset, model.offset)
        assertEquals(
            charactersEntityMock.data?.offset ?: -1 < charactersEntityMockEmpty.data?.total ?: 0,
            model.hasMore
        )
        val first = charactersEntityMock.data?.list?.first()
        assertEquals(first?.id, model.list.first().id)
        assertEquals(first?.name, model.list.first().name)
        assertEquals(
            "${first?.thumbnail?.path}.${first?.thumbnail?.extension}",
            model.list.first().image
        )

    }

    @Test
    fun `toModel should create a new CharacterDetailModel entity`() {

        val model = charactersRemoteMapper.toDetailModel(charactersEntityMock)

        val first = charactersEntityMock.data?.list?.first()
        assertEquals(first?.id, model.id)
        assertEquals(first?.name, model.name)
        assertEquals("${first?.thumbnail?.path}.${first?.thumbnail?.extension}", model.image)

    }


}
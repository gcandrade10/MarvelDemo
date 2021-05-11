package com.example.data.source

import com.example.data.CharactersApi
import com.example.data.entity.CharacterEntity
import com.example.data.entity.CharacterResponse
import com.example.data.entity.DataResponse
import com.example.data.entity.ThumbnailEntity
import com.example.data.source.remote.mapper.CharactersRemoteMapper
import com.example.data.source.remote.mapper.CharactersRemoteMapperImpl
import com.example.domain.getOrNull
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
internal class CharactersRemoteSourceImplTest {

    @Mock
    private lateinit var api: CharactersApi

    private lateinit var mapper: CharactersRemoteMapper

    private lateinit var charactersRemoteSource: CharactersRemoteSource

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
        MockitoAnnotations.openMocks(this)
        mapper = CharactersRemoteMapperImpl()
        charactersRemoteSource = CharactersRemoteSourceImpl(api, mapper)
    }

    @Test
    fun `getCharacters with success api should call api successfully`() {
        runBlockingTest {
            whenever(api.getCharacters()).thenReturn(Response.success(charactersEntityMockEmpty))

            val result = charactersRemoteSource.getCharacters()
            val model = result.getOrNull() ?: throw IllegalStateException("fail")

            assert(result.isSuccess)
            verify(api).getCharacters()
            assertEquals(charactersEntityMockEmpty.data?.offset, model.offset)
            assertEquals(
                charactersEntityMockEmpty.data?.offset ?: -1 < charactersEntityMockEmpty.data?.total ?: 0,
                model.hasMore
            )
            assertEquals(charactersEntityMockEmpty.data?.list?.size, model.list.size)
        }
    }

    @Test
    fun `getCharacters with failing api should call api and fail`() {
        runBlockingTest {
            whenever(api.getCharacters()).thenReturn(
                Response.error(500, "".toResponseBody())
            )

            val result = charactersRemoteSource.getCharacters()

            assert(result.isError)
            verify(api).getCharacters()
        }
    }

    @Test
    fun `getCharacterDetail with success api should call api successfully`() {
        runBlockingTest {
            whenever(api.getCharacterDetail(1)).thenReturn(
                Response.success(
                    charactersEntityMock
                )
            )

            val result = charactersRemoteSource.getCharacterDetail(1)
            val model = result.getOrNull() ?: throw IllegalStateException("fail")

            assert(result.isSuccess)
            verify(api).getCharacterDetail(1)
            val first = charactersEntityMock.data?.list?.first()
            assertEquals(first?.id, model.id)
            assertEquals(first?.name, model.name)
            assertEquals(first?.description, model.description)
            assertEquals("${first?.thumbnail?.path}.${first?.thumbnail?.extension}", model.image)
        }
    }

    @Test
    fun `getCharacterDetail with failing api should call api and fail`() {
        runBlockingTest {
            whenever(api.getCharacterDetail(1)).thenReturn(
                Response.error(500, "".toResponseBody())
            )

            val result = charactersRemoteSource.getCharacterDetail(1)

            assert(result.isError)
            verify(api).getCharacterDetail(1)
        }
    }

}
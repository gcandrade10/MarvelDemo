package com.example.data.repository

import com.example.data.source.CharactersRemoteSource
import com.example.domain.Failure
import com.example.domain.Result
import com.example.domain.getOrNull
import com.example.domain.models.CharacterDetailModel
import com.example.domain.models.CharacterModel
import com.example.domain.models.CharactersModel
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CharactersRepositoryImplTest {

    private lateinit var repository: CharactersRepositoryImpl

    @Mock
    private lateinit var charactersRemoteSource: CharactersRemoteSource

    private val charactersModelMock = CharactersModel(
        listOf(CharacterModel(1, "name", "image"))
    )

    private val characterDetailModelMock = CharacterDetailModel(1, "name", "image", "description")

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = CharactersRepositoryImpl(charactersRemoteSource)
    }

    @Test
    fun `getCharacters should return the menu characters with success`() {
        runBlocking {
            whenever(charactersRemoteSource.getCharacters()).thenReturn(
                Result.Success(
                    charactersModelMock
                )
            )

            val characters = repository.getCharacters()

            assertTrue(characters.isSuccess)
            assertEquals(charactersModelMock, characters.getOrNull())
            verify(charactersRemoteSource).getCharacters()
        }
    }

    @Test
    fun `getCharacters should return the menu characters with error`() {
        runBlocking {
            whenever(charactersRemoteSource.getCharacters()).thenReturn(Result.Error(Failure()))

            val characters = repository.getCharacters()

            assertTrue(characters.isError)
        }
    }

    @Test
    fun `getCharacterDetail should return the menu characters with success`() {
        runBlocking {
            whenever(charactersRemoteSource.getCharacterDetail(1)).thenReturn(
                Result.Success(characterDetailModelMock)
            )

            val characterDetail = repository.getCharacterDetail(1)

            assertTrue(characterDetail.isSuccess)
            assertEquals(characterDetailModelMock, characterDetail.getOrNull())
            verify(charactersRemoteSource).getCharacterDetail(1)
        }
    }

    @Test
    fun `getCharacterDetail should return the menu characters with error`() {
        runBlocking {
            whenever(charactersRemoteSource.getCharacterDetail(1)).thenReturn(Result.Error(Failure()))

            val characters = repository.getCharacterDetail(1)

            assertTrue(characters.isError)
        }
    }

}
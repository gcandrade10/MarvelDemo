package com.example.domain.usecase

import com.example.domain.Failure
import com.example.domain.Result
import com.example.domain.UseCase
import com.example.domain.getOrNull
import com.example.domain.models.CharactersModel
import com.example.domain.repository.CharactersRepository
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
internal class GetCharactersUseCaseTest {
    @Mock
    private lateinit var charactersRepository: CharactersRepository

    private lateinit var useCase: GetCharactersUseCase

    private val charactersModelMock = CharactersModel(emptyList(), false, 0)

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetCharactersUseCaseImpl(charactersRepository)
    }

    @Test
    fun `getOptionList with valid data should return success`() {
        runBlockingTest {
            whenever(charactersRepository.getCharacters()).thenReturn(
                Result.Success(
                    charactersModelMock
                )
            )

            val result = useCase(UseCase.None)

            assertTrue(result.isSuccess)
            assertEquals(charactersModelMock, result.getOrNull())
            verify(charactersRepository).getCharacters()
        }
    }

    @Test
    fun `getOptionList with valid data should return error`() {
        runBlockingTest {
            whenever(charactersRepository.getCharacters()).thenReturn(
                Result.Error(Failure())
            )

            val result = useCase(UseCase.None)

            assertTrue(result.isError)
            verify(charactersRepository).getCharacters()
        }
    }
}
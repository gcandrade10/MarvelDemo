package com.example.domain.usecase

import com.example.domain.Failure
import com.example.domain.Result
import com.example.domain.getOrNull
import com.example.domain.models.CharacterDetailModel
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
internal class GetCharacterDetailUseCaseTest {
    @Mock
    private lateinit var charactersRepository: CharactersRepository

    private lateinit var useCase: GetCharacterDetailUseCase

    private val characterDetailModelMock = CharacterDetailModel(1, "name", "image", "description")

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetCharacterDetailUseCaseImpl(charactersRepository)
    }

    @Test
    fun `getOptionList with valid data should return success`() {
        runBlockingTest {
            whenever(charactersRepository.getCharacterDetail(1)).thenReturn(
                Result.Success(
                    characterDetailModelMock
                )
            )

            val result = useCase(GetCharacterDetailUseCase.Params(1))

            assertTrue(result.isSuccess)
            assertEquals(characterDetailModelMock, result.getOrNull())
            verify(charactersRepository).getCharacterDetail(1)
        }
    }

    @Test
    fun `getOptionList with valid data should return error`() {
        runBlockingTest {
            whenever(charactersRepository.getCharacterDetail(1)).thenReturn(
                Result.Error(Failure())
            )

            val result = useCase(GetCharacterDetailUseCase.Params(1))

            assertTrue(result.isError)
            verify(charactersRepository).getCharacterDetail(1)
        }
    }
}
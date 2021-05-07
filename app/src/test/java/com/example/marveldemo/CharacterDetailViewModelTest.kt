package com.example.marveldemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.Failure
import com.example.domain.Result
import com.example.domain.models.CharacterDetailModel
import com.example.domain.usecase.GetCharacterDetailUseCase
import com.nhaarman.mockitokotlin2.whenever
import getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class CharacterDetailViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewmodel: CharacterDetailViewModel

    @Mock
    private lateinit var getCharacterDetailUseCase: GetCharacterDetailUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewmodel = CharacterDetailViewModel(getCharacterDetailUseCase)
    }

    @Test
    fun `getCharacter success should update mainLiveData`() {
        val character = CharacterDetailModel(
            name = "name",
            description = "description",
            id = 0,
            image = "image"
        )
        runBlocking {
            whenever(getCharacterDetailUseCase.invoke(GetCharacterDetailUseCase.Params(0))).thenReturn(
                Result.Success(character)
            )

            viewmodel.getCharacter(0)

            val value = viewmodel.mainLiveData.getOrAwaitValue()
            Assert.assertEquals(character.id, value.id)
        }
    }

    @Test
    fun `getCharacter failed should update errorLiveData`() {
        runBlocking {
            whenever(getCharacterDetailUseCase.invoke(GetCharacterDetailUseCase.Params(0))).thenReturn(
                Result.Error(Failure())
            )

            viewmodel.getCharacter(0)

            val value = viewmodel.errorLiveData.getOrAwaitValue()
            Assert.assertEquals(Unit, value)
        }
    }

}
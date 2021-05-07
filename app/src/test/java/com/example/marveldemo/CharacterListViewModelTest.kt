package com.example.marveldemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.Failure
import com.example.domain.Result
import com.example.domain.UseCase
import com.example.domain.models.CharacterModel
import com.example.domain.models.CharactersModel
import com.example.domain.usecase.GetCharactersUseCase
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
class CharacterListViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewmodel: CharacterListViewModel

    @Mock
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `getCharacter success should update mainLiveData`() {
        runBlocking {
            whenever(getCharactersUseCase.invoke(UseCase.None)).thenReturn(
                Result.Success(CharactersModel(list = emptyList<CharacterModel>()))
            )

            viewmodel = CharacterListViewModel(getCharactersUseCase)

            val value = viewmodel.mainLiveData.getOrAwaitValue()
            Assert.assertEquals(0, value.size)
        }
    }

    @Test
    fun `getCharacter failed should update errorLiveData`() {
        runBlocking {
            whenever(getCharactersUseCase.invoke(UseCase.None)).thenReturn(
                Result.Error(Failure())
            )

            viewmodel = CharacterListViewModel(getCharactersUseCase)

            val value = viewmodel.errorLiveData.getOrAwaitValue()
            Assert.assertEquals(Unit, value)
        }
    }
}
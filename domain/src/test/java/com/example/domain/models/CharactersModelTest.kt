package com.example.domain.models

import org.junit.Assert
import org.junit.Before
import org.junit.Test


class CharactersModelTest() {

    private val expectedCharactersModel =
        CharactersModel(emptyList(), false, 0)

    private lateinit var characterDetailModel: CharactersModel

    @Before
    fun setUp() {
        characterDetailModel = with(expectedCharactersModel) {
            CharactersModel(list, hasMore, offset)
        }
    }

    @Test
    fun `CharacterDetailModel is correctly generated`() {
        Assert.assertEquals(expectedCharactersModel.list, characterDetailModel.list)
        Assert.assertEquals(expectedCharactersModel.hasMore, characterDetailModel.hasMore)
        Assert.assertEquals(expectedCharactersModel.offset, characterDetailModel.offset)
    }

}
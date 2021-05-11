package com.example.domain.models

import org.junit.Assert
import org.junit.Before
import org.junit.Test


class CharacterModelTest() {

    private val expectedCharacterModel =
        CharacterModel(1, "name", "image")

    private lateinit var characterDetailModel: CharacterDetailModel

    @Before
    fun setUp() {
        characterDetailModel = with(expectedCharacterModel) {
            CharacterDetailModel(id, name, image)
        }
    }

    @Test
    fun `CharacterDetailModel is correctly generated`() {
        Assert.assertEquals(expectedCharacterModel.id, characterDetailModel.id)
        Assert.assertEquals(expectedCharacterModel.name, characterDetailModel.name)
        Assert.assertEquals(expectedCharacterModel.image, characterDetailModel.image)
    }

}
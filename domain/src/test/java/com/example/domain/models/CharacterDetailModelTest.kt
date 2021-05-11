package com.example.domain.models

import org.junit.Assert
import org.junit.Before
import org.junit.Test


class CharacterDetailModelTest() {

    private val expectedCharacterDetailModel =
        CharacterDetailModel(1, "name", "image", "description")

    private lateinit var characterDetailModel: CharacterDetailModel

    @Before
    fun setUp() {
        characterDetailModel = with(expectedCharacterDetailModel) {
            CharacterDetailModel(id, name, image, description)
        }
    }

    @Test
    fun `CharacterDetailModel is correctly generated`() {
        Assert.assertEquals(expectedCharacterDetailModel.id, characterDetailModel.id)
        Assert.assertEquals(expectedCharacterDetailModel.name, characterDetailModel.name)
        Assert.assertEquals(expectedCharacterDetailModel.image, characterDetailModel.image)
        Assert.assertEquals(
            expectedCharacterDetailModel.description,
            characterDetailModel.description
        )
    }

}
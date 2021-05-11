package com.example.data.entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

internal class CharacterEntityTest {

    private lateinit var entity: CharacterEntity

    @Mock
    private lateinit var thumbnail: ThumbnailEntity

    private var characterEntityMock = CharacterEntity(1, "name", "description", null)

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        entity = CharacterEntity(1, "name", "description", thumbnail)
    }

    @Test
    fun `get thumbnail success`() {
        assertTrue(entity.thumbnail is ThumbnailEntity)
    }

    @Test
    fun `get data  success`() {
        assertEquals(characterEntityMock.id, entity.id)
        assertEquals(characterEntityMock.name, entity.name)
        assertEquals(characterEntityMock.description, entity.description)
    }


}
package com.example.data.entity

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ThumbnailEntityTest{

    private lateinit var entity: ThumbnailEntity

    private var thumbnailEntityMock = ThumbnailEntity("path","extension")

    @BeforeEach
    fun setUp() {
        entity = ThumbnailEntity(thumbnailEntityMock.path, thumbnailEntityMock.extension)
    }

    @Test
    fun `get data success`() {
        assertEquals(thumbnailEntityMock, entity)
    }
}
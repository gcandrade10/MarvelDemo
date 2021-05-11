package com.example.data.entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CharacterResponseTest {

    private lateinit var entity: DataResponse

    private var dataResponseMock = DataResponse(0, 0, emptyList())

    @BeforeEach
    fun setUp() {
        entity = DataResponse(offset = 0, total = 0, list = emptyList())
    }

    @Test
    fun `get data success`() {
        assertEquals(dataResponseMock, entity)
    }
}
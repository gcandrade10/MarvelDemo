package com.example.data.entity

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

internal class DataResponseTest {
    @Mock
    private lateinit var data: DataResponse

    private lateinit var entity: CharacterResponse

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        entity = CharacterResponse(data)
    }

    @Test
    fun `get data success`() {
        assertTrue(entity.data is DataResponse)
    }


}
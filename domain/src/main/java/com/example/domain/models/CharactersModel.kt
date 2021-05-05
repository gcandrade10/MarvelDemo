package com.example.domain.models

data class CharactersModel(
    val list: List<CharacterModel> = emptyList(),
    val hasMore: Boolean = false,
    val offset: Int = -1
)
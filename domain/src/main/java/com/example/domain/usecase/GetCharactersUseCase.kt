package com.example.domain.usecase

import com.example.domain.UseCase
import com.example.domain.models.CharactersModel
import com.example.domain.repository.CharactersRepository
import kotlin.coroutines.CoroutineContext

interface GetCharactersUseCase : UseCase<UseCase.None, CharactersModel>

class GetCharactersUseCaseImpl(
    private val charactersRepository: CharactersRepository
) : GetCharactersUseCase {
    override suspend fun invoke(
        params: UseCase.None,
        context: CoroutineContext
    ) = charactersRepository.getCharacters()
}
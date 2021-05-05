package com.example.domain.usecase

import com.example.domain.UseCase
import com.example.domain.models.CharacterDetailModel
import com.example.domain.repository.CharactersRepository
import kotlin.coroutines.CoroutineContext

interface GetCharacterDetailUseCase :
    UseCase<GetCharacterDetailUseCase.Params, CharacterDetailModel> {
    data class Params(val id: Int)
}

class GetCharacterDetailUseCaseImpl(
    private val charactersRepository: CharactersRepository
) : GetCharacterDetailUseCase {
    override suspend fun invoke(
        params: GetCharacterDetailUseCase.Params,
        context: CoroutineContext
    ) = charactersRepository.getCharacterDetail(params.id)
}
# MarvelDemo
This a demo using [Marvel API](https://developer.marvel.com/docs)
We are using Clean Architecture, Couroutines, ViewBinding, Navigation, Koin and Retrofit

![marveldemo](.\marveldemo.gif)

## Domain

This is the core, it must not depend on our data or presentation layers.

### Models

```kotlin
data class CharacterModel (
    val id: Int = -1,
    val name: String = "",
    val image: String = ""
)
```

```kotlin
data class CharactersModel (
    val list: List<CharacterModel> = emptyList(),
    val hasMore: Boolean = false,
    val offset: Int = -1
)
```

```kotlin
data class CharacterDetailModel (
    val id: Int = -1,
    val name: String = "",
    val image: String = "",
    val description : String = ""
)
```

### Interfaces

```kotlin
interface CharactersRepository {
    suspend fun getCharacters(): Result<Failure, CharactersModel>
    suspend fun getCharacterDetail(id: Int): Result<Failure, CharacterDetailModel>
}
```

### Use Cases

```kotlin
interface GetCharactersUseCase(context: CoroutineContext)
```

```kotlin
class GetCharactersUseCaseImpl(
    private val charactersRepository: CharactersRepository
) : GetCharactersUseCase {
    override suspend fun invoke(
        context: CoroutineContext
    ) = charactersRepository.getCharacters()
}
```

```kotlin
interface GetCharacterDetailUseCase : UseCase<Params, None> {
    data class Params(val id: Int)
}
```

```kotlin
class GetCharacterDetailUseCaseImpl(
    private val charactersRepository: CharactersRepository
) : UpdateGlobalPositionPreferencesUseCase {
    override suspend fun invoke(
        params: GetCharacterDetailUseCase.Params,
        context: CoroutineContext
    ) = charactersRepository.getCharacterDetail(params.id)
}
```
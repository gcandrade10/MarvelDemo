package com.example.marveldemo

import android.app.Application
import com.example.domain.usecase.GetCharacterDetailUseCase
import com.example.domain.usecase.GetCharacterDetailUseCaseImpl
import com.example.domain.usecase.GetCharactersUseCase
import com.example.domain.usecase.GetCharactersUseCaseImpl
import com.example.marveldemo.di.data.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class MarvelApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.ERROR)
            androidContext(this@MarvelApp)
            modules(dataModule, repositoryModule)
        }
    }
}

val repositoryModule = module {
    viewModel { CharacterDetailViewModel(get()) }
    viewModel { CharacterListViewModel(get()) }
    single<GetCharactersUseCase> {
        GetCharactersUseCaseImpl(get())
    }
    single<GetCharacterDetailUseCase> {
        GetCharacterDetailUseCaseImpl(get())
    }
}
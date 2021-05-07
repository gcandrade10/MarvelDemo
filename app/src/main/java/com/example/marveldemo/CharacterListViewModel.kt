package com.example.marveldemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.UseCase
import com.example.domain.models.CharacterModel
import com.example.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {
    val mainLiveData = MutableLiveData<List<CharacterModel>>()
    val errorLiveData = MutableLiveData<Unit>()

    init {
        viewModelScope.launch {
            getCharactersUseCase(UseCase.None).fold(
                {
                    errorLiveData.postValue(Unit)
                },
                {
                    mainLiveData.postValue(it.list)
                }
            )
        }
    }
}
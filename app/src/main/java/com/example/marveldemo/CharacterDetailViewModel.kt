package com.example.marveldemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.CharacterDetailModel
import com.example.domain.usecase.GetCharacterDetailUseCase
import kotlinx.coroutines.launch

class CharacterDetailViewModel(val getCharacterDetailUseCase: GetCharacterDetailUseCase) :
    ViewModel() {

    val mainLiveData = MutableLiveData<CharacterDetailModel>()
    val errorLiveData = MutableLiveData<Unit>()

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            getCharacterDetailUseCase(GetCharacterDetailUseCase.Params(id)).fold({
                errorLiveData.postValue(Unit)
            }, {
                mainLiveData.postValue(it)
            })
        }

    }
}

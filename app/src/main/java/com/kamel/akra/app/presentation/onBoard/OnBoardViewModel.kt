package com.kamel.akra.app.presentation.onBoard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardViewModel @Inject constructor(): ViewModel() {
    private var currentItemIndex: Int = 0

    private val _currentIntroItem = MutableLiveData<Int>()
    val currentIntroItem: LiveData<Int>
        get() = _currentIntroItem

    private val _skipIntro = MutableLiveData<Boolean>()
    val skipIntro: LiveData<Boolean>
        get() = _skipIntro

    init {
        _currentIntroItem.value = currentItemIndex
    }

    fun setCurrentIntroItem(position: Int){
        _currentIntroItem.value = position
        currentItemIndex = position
    }

    fun onSkipClicked(){
        _skipIntro.value = true
    }
    fun onIntrosSkipped() {
        _skipIntro.value = false
    }

    fun onNextClicked(){
        if(currentItemIndex < 2) {
            currentItemIndex += 1
            _currentIntroItem.value = currentItemIndex
        }else if(currentItemIndex == 2)
            onSkipClicked()
    }
}
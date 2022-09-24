package com.kamel.akra.app.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel(){

    private val _goToScreen = MutableLiveData<Int>()
    val goToScreen: LiveData<Int>
        get() = _goToScreen

    fun setGoToScreen(screen: Int) {
        _goToScreen.value = screen
    }
}
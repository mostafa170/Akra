package com.kamel.akra.app.presentation.azkar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kamel.akra.domain.entities.AzkarNavigationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AzkarViewModel @Inject constructor(): ViewModel(){

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error
    fun onErrorMessageShown() {
        _error.value = null
    }

    private val _back = MutableLiveData<Boolean>()
    val back: LiveData<Boolean>
        get() = _back
    fun onBackClicked() {
        _back.value = true
    }
    fun onBackNavigated() {
        _back.value = false
    }

    private val _goToScreen = MutableLiveData<Int?>()
    val goToScreen: LiveData<Int?>
        get() = _goToScreen
    fun restScreen(){
        _goToScreen.value = null
    }
    fun setGoToScreen(screen: Int) {
        _goToScreen.value = screen
    }
}
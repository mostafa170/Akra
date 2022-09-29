package com.kamel.akra.app.presentation.qibla

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class QiblaViewModel @Inject constructor(): ViewModel() {

    private val _back = MutableLiveData<Boolean>()
    val back: LiveData<Boolean>
        get() = _back
    fun onBackClicked() {
        _back.value = true
    }
    fun onBackNavigated() {
        _back.value = false
    }

    private val _currentQiblaDirection = MutableLiveData<Float>()
    val currentQiblaDirection: LiveData<Float>
        get() = _currentQiblaDirection

    fun setQiblaDirection(qiblaDirection: Float) {
        _currentQiblaDirection.value = qiblaDirection
    }

}
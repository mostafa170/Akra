package com.kamel.akra.app.presentation.main

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val application: Application,
                                        private val resources: Resources): ViewModel(){

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error
    fun onErrorMessageShown() {
        _error.value = null
    }

    private val _unauthenticated = MutableLiveData<Boolean>()
    val unauthenticated: LiveData<Boolean>
        get() = _unauthenticated

    private val _sendLocationSuccess = MutableLiveData<Boolean>()
    val sendLocationSuccess: LiveData<Boolean>
        get() = _sendLocationSuccess


}
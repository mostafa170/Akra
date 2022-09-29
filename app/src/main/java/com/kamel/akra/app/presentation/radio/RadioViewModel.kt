package com.kamel.akra.app.presentation.radio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kamel.akra.domain.entities.RadioStation
import com.kamel.akra.domain.usecases.radio.GetRadioChannelsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RadioViewModel @Inject constructor(private val getRadioChannelsUseCase: GetRadioChannelsUseCase): ViewModel(){

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

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

    private val _next = MutableLiveData<Boolean>()
    val next: LiveData<Boolean>
        get() = _next
    fun onNextClicked() {
        _next.value = true
    }
    fun onNextDone() {
        _next.value = false
    }

    private val _previous = MutableLiveData<Boolean>()
    val previous: LiveData<Boolean>
        get() = _previous
    fun onPreviousClicked() {
        _previous.value = true
    }
    fun onPreviousDone() {
        _previous.value = false
    }

    private val _play = MutableLiveData<Boolean>()
    val play: LiveData<Boolean>
        get() = _play
    fun onPlaySound() {
        _play.value = true
    }
    fun onStopSound() {
        _play.value = false
    }


    private val _soundUrl = MutableLiveData<String>()
    val soundUrl: LiveData<String>
        get() = _soundUrl

    private val _radioChannels = MutableLiveData<List<RadioStation>>()
    val radioChannels: LiveData<List<RadioStation>>
        get() = _radioChannels

    fun playRadioAudio(URL: String){
        if (_play.value == true)
            _soundUrl.value = URL
    }
    init {
        onStopSound()
        getRadioChannelsApi()
    }

    private fun getRadioChannelsApi(){
        viewModelScope.launch {
            _loading.postValue(true)
            getRadioChannelsUseCase.invoke().fold({
                _error.postValue(it.toErrorString())
            },{
                _radioChannels.postValue(it)
            })
            _loading.postValue(false)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
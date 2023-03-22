package com.kamel.akra.app.presentation.tilawa

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kamel.akra.domain.entities.QuranAudio
import com.kamel.akra.domain.usecases.quran.GetQuranListenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TilawaViewModel @Inject constructor(private val getQuranListenUseCase: GetQuranListenUseCase): ViewModel(){

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _back = MutableLiveData<Boolean>()
    val back: LiveData<Boolean>
        get() = _back
    fun onBackClicked(){
        _back.value = true
    }
    fun onBackNavigated() {
        _back.value = false
    }

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error
    fun onErrorMessageShown() {
        _error.value = null
    }

    private val _quranAudio = MutableLiveData<List<QuranAudio>>()
    val quranAudio: LiveData<List<QuranAudio>>
        get() = _quranAudio

    init {
        getListForQuranListen()
    }

    private fun getListForQuranListen(){
        viewModelScope.launch {
            _loading.postValue(true)
            getQuranListenUseCase.invoke(1).fold({
                _error.postValue(it.toErrorString())
            },{
                _quranAudio.postValue(it)
            })
            _loading.postValue(false)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
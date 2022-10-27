package com.kamel.akra.app.presentation.azkar.myAzkar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kamel.akra.domain.entities.Zekr
import com.kamel.akra.domain.usecases.azkar.AddZekrUseCase
import com.kamel.akra.domain.usecases.azkar.GetLocalAzkarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyAzkarViewModel @Inject constructor(private val getLocalAzkarUseCase: GetLocalAzkarUseCase,
private val addZekrUseCase: AddZekrUseCase): ViewModel(){

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)


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

    private val _addZekr = MutableLiveData<Boolean>()
    val addZekr: LiveData<Boolean>
        get() = _addZekr
    fun onAddZekrClicked() {
        _addZekr.value = true
    }
    fun onAddZekrDone() {
        _addZekr.value = false
    }

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _localAzkar = MutableLiveData<List<Zekr>>()
    val localAzkar: LiveData<List<Zekr>>
        get() = _localAzkar


    init {
        getAzkarLocal()
    }


    fun getAzkarLocal(){
        viewModelScope.launch {
            _loading.postValue(true)
            getLocalAzkarUseCase.invoke().fold({
                _error.postValue(it.toErrorString())
            },{
                _localAzkar.postValue(it)
                Log.e("TAG", "getAzkarLocal: $it" )
            })
            _loading.postValue(false)
        }
    }

    fun addZekrLocal(zekr: Zekr){
        viewModelScope.launch {
            addZekrUseCase.invoke(zekr)
        }
        getAzkarLocal()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
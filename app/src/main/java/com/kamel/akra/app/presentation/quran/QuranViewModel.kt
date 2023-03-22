package com.kamel.akra.app.presentation.quran

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kamel.akra.domain.entities.Quran
import com.kamel.akra.domain.usecases.quran.GetNameOfSurahUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuranViewModel @Inject constructor(private val getNameOfSurahUseCase: GetNameOfSurahUseCase): ViewModel(){

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

    private val _surahsOfQuran = MutableLiveData<List<Quran>>()
    val surahsOfQuran: LiveData<List<Quran>>
        get() = _surahsOfQuran

    init {
        getListOfSurah()
    }

    private fun getListOfSurah(){
        viewModelScope.launch {
            _loading.postValue(true)
            getNameOfSurahUseCase.invoke().fold({
                _error.postValue(it.toErrorString())
            },{
                _surahsOfQuran.postValue(it)
            })
            _loading.postValue(false)
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
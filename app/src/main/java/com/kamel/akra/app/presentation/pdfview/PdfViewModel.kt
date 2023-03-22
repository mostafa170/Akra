package com.kamel.akra.app.presentation.pdfview

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kamel.akra.domain.entities.Quran
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

@HiltViewModel
class PdfViewModel @Inject constructor(private val resources: Resources): ViewModel() {

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

    private val _currentSurah = MutableLiveData<Quran>()
    val currentSurah: LiveData<Quran>
        get() = _currentSurah
    fun setSurah(quran: Quran){
        _currentSurah.value = quran
    }



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
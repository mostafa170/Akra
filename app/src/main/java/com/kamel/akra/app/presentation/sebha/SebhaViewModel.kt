package com.kamel.akra.app.presentation.sebha

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SebhaViewModel @Inject constructor(): ViewModel(){

    private val _back = MutableLiveData<Boolean>()
    val back: LiveData<Boolean>
        get() = _back
    fun onBackClicked() {
        _back.value = true
    }
    fun onBackNavigated() {
        _back.value = false
    }

    private val _selectSebha = MutableLiveData<Boolean>()
    val selectSebhak: LiveData<Boolean>
        get() = _selectSebha
    fun onSelectionSebhaClicked() {
        _selectSebha.value = true
    }
    fun onSelectionSebhaDone() {
        _selectSebha.value = false
    }

    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int>
        get() = _count

    private val _countSumAll = MutableLiveData<Int>()
    val countSumAll: LiveData<Int>
        get() = _countSumAll

    private val _sebhaName = MutableLiveData<String>()
    val sebhaName: LiveData<String>
        get() = _sebhaName

    init {
        _count.value = 0
        _countSumAll.value = 0
    }

    fun plusSebhaCount(){
        _count.value?.let { num ->
            _count.value = num + 1
        }
         _countSumAll.value?.let { num ->
             _countSumAll.value = num + 1
        }
    }
    fun restSebha(isRest: Boolean){
        if (isRest){
            _count.value = 0
            _countSumAll.value = 0
        }else
            _count.value = 0
    }

}
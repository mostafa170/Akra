package com.kamel.akra.app.presentation.azkar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kamel.akra.domain.entities.AzkarCategory
import com.kamel.akra.domain.entities.Zekr
import com.kamel.akra.domain.usecases.azkar.GetAzkarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AzkarViewModel @Inject constructor(private val getAzkarUseCase: GetAzkarUseCase): ViewModel(){

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

    private val _goToScreen = MutableLiveData<Int?>()
    val goToScreen: LiveData<Int?>
        get() = _goToScreen
    fun restScreen(){
        _goToScreen.value = null
    }
    fun setGoToScreen(screen: Int) {
        _goToScreen.value = screen
    }


    private val _azkarCategory = MutableLiveData<AzkarCategory>()
    val azkarCategory: LiveData<AzkarCategory>
        get() = _azkarCategory

    fun getAzkarFromAsset(){
        viewModelScope.launch {
            _loading.postValue(true)
            getAzkarUseCase.invoke().fold({
                _error.postValue(it.toErrorString())
            },{
                _azkarCategory.postValue(it)
                Log.e("TAG", "getAzkarFromAsset: $it" )
            })
            _loading.postValue(false)
        }
    }


}
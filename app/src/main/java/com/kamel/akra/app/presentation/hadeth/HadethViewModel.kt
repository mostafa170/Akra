package com.kamel.akra.app.presentation.hadeth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kamel.akra.domain.entities.HadethCategories
import com.kamel.akra.domain.usecases.hadeth.GetHadethCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HadethViewModel  @Inject constructor(private val getHadethCategoryUseCase: GetHadethCategoryUseCase): ViewModel(){

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

    private val _hadethCategories = MutableLiveData<List<HadethCategories>>()
    val hadethCategories: LiveData<List<HadethCategories>>
        get() = _hadethCategories

    fun getHadethCategoriesApi(){
        viewModelScope.launch {
            _loading.postValue(true)
            getHadethCategoryUseCase.invoke().fold({
                _error.postValue(it.toErrorString())
            },{
                _hadethCategories.postValue(it)
            })
            _loading.postValue(false)
        }
    }
}
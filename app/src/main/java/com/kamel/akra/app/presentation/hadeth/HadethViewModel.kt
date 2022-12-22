package com.kamel.akra.app.presentation.hadeth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kamel.akra.domain.entities.HadethCategories
import com.kamel.akra.domain.entities.HadethDetails
import com.kamel.akra.domain.entities.HadethListById
import com.kamel.akra.domain.usecases.hadeth.GetHadethCategoryUseCase
import com.kamel.akra.domain.usecases.hadeth.GetHadethDetailsUseCase
import com.kamel.akra.domain.usecases.hadeth.GetHadethListByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HadethViewModel  @Inject constructor(private val getHadethCategoryUseCase: GetHadethCategoryUseCase,
private val getHadethListByIdUseCase: GetHadethListByIdUseCase,
private val getHadethDetailsUseCase: GetHadethDetailsUseCase): ViewModel(){

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var currentPage = 1
    var totalPages = 1
    var isLastPage = false
    var isLoading = false


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

    private val _hadethListById = MutableLiveData<List<HadethCategories>>()
    val hadethListById: LiveData<List<HadethCategories>>
        get() = _hadethListById

    private val _hadethDetails = MutableLiveData<HadethDetails>()
    val hadethDetails: LiveData<HadethDetails>
        get() = _hadethDetails


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

    fun getHadethListByIdApi(category_id: Int){
        viewModelScope.launch {
            _loading.postValue(true)
            getHadethListByIdUseCase.invoke(category_id, currentPage).fold({
                _error.postValue(it.toErrorString())
            },{
                _hadethListById.postValue(((_hadethListById.value ?: emptyList()) union it.hadethList).toList())
                totalPages = it.lastPage
                isLoading = false
                if (currentPage ==it.lastPage)
                    isLastPage = true

                Log.e("TAG", "hadethListById: ${it.currentPage} ${it.lastPage}" )
            })
            _loading.postValue(false)
        }
    }

    fun getHadethDetailsApi(hadeth_id: Int){
        viewModelScope.launch {
            _loading.postValue(true)
            getHadethDetailsUseCase.invoke(hadeth_id).fold({
                _error.postValue(it.toErrorString())
            },{
                _hadethDetails.postValue(it)
            })
            _loading.postValue(false)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
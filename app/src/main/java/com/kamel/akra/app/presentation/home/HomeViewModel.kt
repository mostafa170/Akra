package com.kamel.akra.app.presentation.home

import android.content.res.Resources
import android.location.Location
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kamel.akra.app.utilsView.MyDate
import com.kamel.akra.app.utilsView.MyDateJava
import com.kamel.akra.data.utils.toPrayerName
import com.kamel.akra.data.utils.toPrayerTime
import com.kamel.akra.domain.entities.Prayer
import com.kamel.akra.domain.usecases.prayers.DownloadPrayerUseCase
import com.kamel.akra.domain.usecases.prayers.GetUpcomingPrayerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val resources: Resources,
                                        private val downloadPrayerUseCase: DownloadPrayerUseCase,
                                        private val getUpcomingPrayerUseCase: GetUpcomingPrayerUseCase,): ViewModel(){

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

    private val _goToScreen = MutableLiveData<Int?>()
    val goToScreen: LiveData<Int?>
        get() = _goToScreen
    fun restScreen(){
        _goToScreen.value = null
    }
    fun setGoToScreen(screen: Int) {
        _goToScreen.value = screen
    }

    private lateinit var timer: CountDownTimer

    val _upcomingPrayer = MutableLiveData<Prayer>()
    val upcomingPrayer: LiveData<Prayer>
        get() = _upcomingPrayer


    private val _shouldGetNextUpcomingPrayer = MutableLiveData<Boolean>()
    val shouldGetNextUpcomingPrayer: LiveData<Boolean>
        get() = _shouldGetNextUpcomingPrayer

    private val _upcomingPrayerTimerText = MutableLiveData<String>()
    val upcomingPrayerTimerText: LiveData<String>
        get() = _upcomingPrayerTimerText

    private val _upcomingPrayerTime = MutableLiveData<String>()
    val upcomingPrayerTime: LiveData<String>
        get() = _upcomingPrayerTime

    private val _upcomingPrayerName = MutableLiveData<String>()
    val upcomingPrayerName: LiveData<String>
        get() = _upcomingPrayerName


    init {
        geNextUpcomingPrayerLocal()
    }


    fun downloadPrayers(location: Location, adjustment : Int){
        val calendar = Calendar.getInstance()
        val body = mapOf("latitude" to "${location.latitude}", "longitude" to "${location.longitude}",
            "method" to "5", "month" to "${calendar.get(Calendar.MONTH) + 1}", "adjustment" to "$adjustment",
            "year" to "${calendar.get(Calendar.YEAR)}")
        viewModelScope.launch {
            _loading.value = true
            downloadPrayerUseCase.invoke(body).fold(
                {
                    _error.value = it.toErrorString()
                },{
                    Log.e("TAG", "downloadPrayers: $it" )
                    geNextUpcomingPrayerLocal()
                })
            _loading.value = false
        }
    }


    fun geNextUpcomingPrayerLocal(){
        viewModelScope.launch {
            getUpcomingPrayerUseCase.invoke().fold(
                {
                    _error.value = it.toErrorString()
                },{
                    _upcomingPrayer.value = it
                })
        }
    }

    fun setUpUpcomingPrayer(prayer: Prayer) {
        _upcomingPrayerTime.value =  prayer.dateTime.toPrayerTime()

        val timeRemaining = prayer.dateTime - System.currentTimeMillis()
        timer = object: CountDownTimer(timeRemaining, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _upcomingPrayerTimerText.value = MyDateJava.millisecondsToCountDownString(millisUntilFinished)
            }
            override fun onFinish() {
                _shouldGetNextUpcomingPrayer.value = true
                cancel()
            }
        }
        timer.start()

        _upcomingPrayerName.value = prayer.id.toPrayerName(resources)
    }

    fun loadedNextUpcomingPrayer() {
        _shouldGetNextUpcomingPrayer.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
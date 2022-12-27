package com.kamel.akra.app.presentation.prayer

import android.content.res.Resources
import android.location.Location
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kamel.akra.app.utilsView.MyDate
import com.kamel.akra.app.utilsView.MyDateJava
import com.kamel.akra.data.utils.toHijriMonthName
import com.kamel.akra.data.utils.toPrayerName
import com.kamel.akra.data.utils.toPrayerTime
import com.kamel.akra.domain.entities.Prayer
import com.kamel.akra.domain.usecases.prayers.DownloadPrayerUseCase
import com.kamel.akra.domain.usecases.prayers.GetDayPrayersUseCase
import com.kamel.akra.domain.usecases.prayers.GetUpcomingPrayerUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.*


@HiltViewModel
class PrayersViewModel @Inject constructor(private val resources: Resources ,
                                           private val getUpcomingPrayerUseCase: GetUpcomingPrayerUseCase,
private val downloadPrayerUseCase: DownloadPrayerUseCase,
private val getDayPrayersUseCase: GetDayPrayersUseCase): ViewModel(){

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

    private lateinit var timer: CountDownTimer

    val _upcomingPrayer = MutableLiveData<Prayer>()
    val upcomingPrayer: LiveData<Prayer>
        get() = _upcomingPrayer


    private val _shouldGetNextUpcomingPrayer = MutableLiveData<Boolean>()
    val shouldGetNextUpcomingPrayer: LiveData<Boolean>
        get() = _shouldGetNextUpcomingPrayer

    private val _dayPrayers = MutableLiveData<List<Prayer>>()
    val dayPrayers: LiveData<List<Prayer>>
        get() = _dayPrayers

    private val _upcomingPrayerTimerText = MutableLiveData<String>()
    val upcomingPrayerTimerText: LiveData<String>
        get() = _upcomingPrayerTimerText

    private val _upcomingPrayerTime = MutableLiveData<String>()
    val upcomingPrayerTime: LiveData<String>
        get() = _upcomingPrayerTime

    private val _upcomingPrayerName = MutableLiveData<String>()
    val upcomingPrayerName: LiveData<String>
        get() = _upcomingPrayerName

    private val _dayDate = MutableLiveData<String>()
    val dayDate: LiveData<String>
        get() = _dayDate

    private val _dayHijriDate = MutableLiveData<String>()
    val dayHijriDate: LiveData<String>
        get() = _dayHijriDate

    private val calendar = Calendar.getInstance()
    private val _dayOfMonthNumber = MutableLiveData<Int>()
    val dayOfMonthNumber: LiveData<Int>
        get() = _dayOfMonthNumber

    init {
        _dayOfMonthNumber.value = calendar.get(Calendar.DAY_OF_MONTH)
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
                })
            _loading.value = false
        }
    }


    fun getDayPrayers(dayNumber: Int){
        _loading.value = true
        viewModelScope.launch {
            getDayPrayersUseCase.invoke(dayNumber).fold({
                    _error.value = it.toErrorString()
            },{
                if(it.isNotEmpty()) {
                    _dayPrayers.value = it
                    _dayDate.value = MyDate.convertDateToDateString(
                        Date(it[0].dateTime),
                        "yyyy MMMM dd",
                        Locale.getDefault()
                    )
                    _dayHijriDate.value = it[0].dateHijri.replaceRange(
                        2, 6, it[0].dateHijri.split("-")[1].toHijriMonthName(
                            "ar"
                        )
                    )
                }
                geNextUpcomingPrayerLocal()
            })
            _loading.value = false
        }
    }

    fun setUpUpcomingPrayer(prayer: Prayer) {
        _upcomingPrayerTime.value = prayer.dateTime.toPrayerTime()
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
        Log.e("TAG", "setUpUpcomingPrayer: ${_upcomingPrayerTimerText.value}" )
    }


    fun loadedNextUpcomingPrayer() {
        _shouldGetNextUpcomingPrayer.value = false
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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
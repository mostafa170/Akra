package com.kamel.akra.app.presentation.prayer

import android.content.res.Resources
import android.location.Location
import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kamel.akra.app.utilsView.MyDate
import com.kamel.akra.domain.entities.Prayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.*


@HiltViewModel
class PrayersViewModel @Inject constructor(private val resources: Resources): ViewModel(){

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private lateinit var timer: CountDownTimer

//    private val database = getDatabase(application)
//    private val prayersRepository = PrayersRepository(database)

    //val upcomingPrayer = prayersRepository.upcomingPrayer

    private val _back = MutableLiveData<Boolean>()
    val back: LiveData<Boolean>
        get() = _back
    fun onBackClicked(){
        _back.value = true
    }
    fun onBackNavigated() {
        _back.value = false
    }

    private val _hijriAdjustmentClicked = MutableLiveData<Boolean>()
    val hijriAdjustmentClicked: LiveData<Boolean>
        get() = _hijriAdjustmentClicked

    private val _closeHijriAdjustmentDialog = MutableLiveData<Boolean>()
    val closeHijriAdjustmentDialog: LiveData<Boolean>
        get() = _closeHijriAdjustmentDialog

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

    private val _showDatePickerDialog = MutableLiveData<Boolean>()
    val showDatePickerDialog: LiveData<Boolean>
        get() = _showDatePickerDialog
    fun onOpenDatePickerClicked() {
        _showDatePickerDialog.value = true
    }
    fun onDatePickerShown(){
        _showDatePickerDialog.value = false
    }

    private val calendar = Calendar.getInstance()
    private var currentDayNumber: Int = calendar.get(Calendar.DAY_OF_MONTH)
    private var maxDayNumber: Int = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    private val _dayOfMonthNumber = MutableLiveData<Int>()
    val dayOfMonthNumber: LiveData<Int>
        get() = _dayOfMonthNumber


    init {
        _dayOfMonthNumber.value = calendar.get(Calendar.DAY_OF_MONTH)
    }

    fun onHijriAdjustmentClicked(){
        _hijriAdjustmentClicked.value = true
    }
    fun onHijriAdjustmentClickedDone() {
        _hijriAdjustmentClicked.value = false
    }

    fun onCloseHijriAdjustmentClicked(){
        _closeHijriAdjustmentDialog.value = true
    }
    fun onCloseHijriAdjustmentClickedDone(){
        _closeHijriAdjustmentDialog.value = false
    }

    fun downloadPrayers(location: Location, adjustment : Int){
        viewModelScope.launch {
            val calendar = Calendar.getInstance()
            val body = mapOf("latitude" to "${location.latitude}", "longitude" to "${location.longitude}",
                "method" to "5", "month" to "${calendar.get(Calendar.MONTH) + 1}", "adjustment" to "$adjustment",
                "year" to "${calendar.get(Calendar.YEAR)}")
            //prayersRepository.downloadPrayers(body)
        }
    }

    //fun getDayPrayers(dayNumber: Int): LiveData<List<Prayer>> = prayersRepository.getDayPrayers(dayNumber)

    fun onGetNextDayPrayersClicked(){
        if(currentDayNumber < maxDayNumber){
            currentDayNumber += 1
            _dayOfMonthNumber.value = currentDayNumber
        }
    }
    fun onGetPreviousPrayersDayClicked(){
        if(currentDayNumber > 1){
            currentDayNumber -= 1
            _dayOfMonthNumber.value = currentDayNumber
        }
    }

    fun setDayPrayers(prayers: List<Prayer>){
        if(prayers.isNotEmpty()) {
            _dayPrayers.value = prayers
            _dayDate.value = MyDate.convertDateToDateString(
                Date(prayers[0].dateTime),
                "dd MMMM yyyy",
                Locale.getDefault()
            )

//            _dayHijriDate.value = prayers[0].dateHijri.replaceRange(
//                2, 6, prayers[0].dateHijri.split("-")[1].toHijriMonthName(
//                    "ar"
//                )
//            )
        }
    }

    fun setUpUpcomingPrayer(prayer: Prayer) {
        //_upcomingPrayerTime.value = prayer.dateTime.toPrayerTime()
        val timeRemaining = prayer.dateTime - System.currentTimeMillis()
        timer = object: CountDownTimer(timeRemaining, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _upcomingPrayerTimerText.value = MyDate.millisecondsToCountDownString(
                    millisUntilFinished
                )
            }
            override fun onFinish() {
                _shouldGetNextUpcomingPrayer.value = true
                cancel()
            }
        }
        timer.start()
        //_upcomingPrayerName.value = prayer.id.toPrayerName(resources)
    }

    //fun getNextUpcomingPrayer(): LiveData<Prayer> = prayersRepository.getNextUpcomingPrayer()

    fun loadedNextUpcomingPrayer() {
        _shouldGetNextUpcomingPrayer.value = false
    }

    fun setDayOfMonthNumber(dayOfMonth: Int) {
        currentDayNumber = dayOfMonth
        _dayOfMonthNumber.value = dayOfMonth
    }


}
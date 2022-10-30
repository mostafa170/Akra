package com.kamel.akra.data.datasources

import android.app.Application
import arrow.core.Either
import com.kamel.akra.app.utilsView.MyDateJava
import com.kamel.akra.data.models.PrayersData
import com.kamel.akra.data.network.NetworkPrayers
import com.kamel.akra.data.room.getDatabase
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.data.utils.SomethingWentWrongFailure
import com.kamel.akra.domain.entities.Prayer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class PrayerDataSourcesImpl @Inject constructor(private val dispatcher: CoroutineDispatcher,private val application: Application) : PrayerDataSources {
    override suspend fun getNextUpcomingPrayer(): Either<AppFailure, Prayer> =
        withContext(dispatcher) {
            val database = getDatabase(application)
            val prayer = database.prayersDao.getUpcomingPrayer()
            prayer.let {
                return@withContext Either.Right(it)
            }
        }

    override suspend fun downloadPrayers(body: Map<String, String>): Either<AppFailure, Boolean> =
        withContext(Dispatchers.IO) {
            try {
                val prayersSuccessModel = NetworkPrayers.apiCalls.downloadMonthPrayersAsync(body).await()
                if (prayersSuccessModel.code == 200) {
                    val database = getDatabase(application)
                    database.prayersDao.insertPrayer(*createPrayersList(prayersSuccessModel.data))
                    return@withContext Either.Right(true)
                } else
                    return@withContext Either.Right(false)
            } catch (ex: Exception) {
                return@withContext Either.Left(SomethingWentWrongFailure(ex.message ?: "Something went wrong!"))
            }
        }

    override suspend fun getDayPrayers(dayNumber: Int): Either<AppFailure, List<Prayer>> =
        withContext(dispatcher) {
            val database = getDatabase(application)
            val list: List<Prayer> = database.prayersDao.getDayPrayers(dayNumber.toString())
            list.let {
                return@withContext Either.Right(list)
            }

        }

}

    private fun createPrayersList(data: List<PrayersData>): Array<Prayer> {
        var id = 0
        var firstPrayerTime = 0L
        val list = mutableListOf<Prayer>()
        data.forEach {
            for (i in 1..6){
                id += 1
                val string = it.date.gregorian.date + " " + when {
                    id % 6 == 0 -> it.timings.Isha
                    id % 6 == 1 -> it.timings.Fajr
                    id % 6 == 2 -> it.timings.Sunrise
                    id % 6 == 3 -> it.timings.Dhuhr
                    id % 6 == 4 -> it.timings.Asr
                    id % 6 == 5 -> it.timings.Maghrib
                    else -> ""
                }.replace("(", "").replace(")", "")
                val currentPrayerTime = MyDateJava.getDate(string.substring(0, 16),"dd-MM-yyyy HH:mm", Locale.ENGLISH).time
                list.add(Prayer(id, it.date.hijri.date, currentPrayerTime, if(id == 1) 0 else currentPrayerTime - firstPrayerTime))
                firstPrayerTime = currentPrayerTime
            }
        }
        return list.toTypedArray()
    }
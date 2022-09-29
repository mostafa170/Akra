package com.kamel.akra.data.repositories

import androidx.lifecycle.LiveData
import arrow.core.Either
import com.kamel.akra.data.datasources.PrayerDataSources
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.domain.entities.Prayer
import com.kamel.akra.domain.repositories.PrayersRepository
import javax.inject.Inject

class PrayersRepositoryImpl @Inject constructor(private val prayerDataSources: PrayerDataSources): PrayersRepository {
    override suspend fun getNextUpcomingPrayer(): Either<AppFailure, LiveData<Prayer>> =
        prayerDataSources.getNextUpcomingPrayer()

    override suspend fun downloadPrayers(body: Map<String, String>): Either<AppFailure, Boolean> =
        prayerDataSources.downloadPrayers(body)

    override suspend fun getDayPrayers(dayNumber: Int): Either<AppFailure, LiveData<List<Prayer>>> =
        prayerDataSources.getDayPrayers(dayNumber)
}
package com.kamel.akra.data.datasources

import androidx.lifecycle.LiveData
import arrow.core.Either
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.domain.entities.Prayer
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class PrayerDataSourcesImpl @Inject constructor(private val dispatcher: CoroutineDispatcher) : PrayerDataSources{
    override suspend fun getNextUpcomingPrayer(): Either<AppFailure, LiveData<Prayer>> {
        TODO("Not yet implemented")
    }

    override suspend fun downloadPrayers(body: Map<String, String>): Either<AppFailure, Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun getDayPrayers(dayNumber: Int): Either<AppFailure, LiveData<List<Prayer>>> {
        TODO("Not yet implemented")
    }
}
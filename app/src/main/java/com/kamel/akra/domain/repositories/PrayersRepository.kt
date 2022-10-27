package com.kamel.akra.domain.repositories

import androidx.lifecycle.LiveData
import arrow.core.Either
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.domain.entities.Prayer

interface PrayersRepository {
    suspend fun getNextUpcomingPrayer(): Either<AppFailure, LiveData<Prayer>>
    suspend fun downloadPrayers(body: Map<String, String>) : Either<AppFailure, Boolean>
    suspend fun getDayPrayers(dayNumber: Int): Either<AppFailure, LiveData<List<Prayer>>>


}
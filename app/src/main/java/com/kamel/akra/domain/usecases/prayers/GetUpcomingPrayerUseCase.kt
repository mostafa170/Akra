package com.kamel.akra.domain.usecases.prayers

import com.kamel.akra.domain.repositories.PrayersRepository
import javax.inject.Inject

class GetUpcomingPrayerUseCase @Inject constructor (private val prayersRepository: PrayersRepository){
    suspend operator fun invoke() = prayersRepository.getNextUpcomingPrayer()
}
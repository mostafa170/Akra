package com.kamel.akra.domain.usecases.prayers

import com.kamel.akra.domain.repositories.PrayersRepository
import javax.inject.Inject

class GetDayPrayersUseCase @Inject constructor (private val prayersRepository: PrayersRepository){
    suspend operator fun invoke(dayNumber: Int) = prayersRepository.getDayPrayers(dayNumber)
}
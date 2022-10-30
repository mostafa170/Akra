package com.kamel.akra.domain.usecases.prayers

import com.kamel.akra.domain.repositories.PrayersRepository
import javax.inject.Inject

class DownloadPrayerUseCase @Inject constructor (private val prayersRepository: PrayersRepository){
    suspend operator fun invoke(body: Map<String, String>) = prayersRepository.downloadPrayers(body)
}
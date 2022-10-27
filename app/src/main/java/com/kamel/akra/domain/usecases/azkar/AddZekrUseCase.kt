package com.kamel.akra.domain.usecases.azkar

import com.kamel.akra.domain.entities.Zekr
import com.kamel.akra.domain.repositories.AzkarRepository
import javax.inject.Inject

class AddZekrUseCase @Inject constructor (private val azkarRepository: AzkarRepository){
    suspend operator fun invoke(zekr: Zekr) = azkarRepository.addLocalAzkar(zekr)
}
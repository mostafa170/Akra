package com.kamel.akra.domain.usecases.azkar

import com.kamel.akra.domain.repositories.AzkarRepository
import javax.inject.Inject

class GetAzkarUseCase @Inject constructor (private val azkarRepository: AzkarRepository){
    suspend operator fun invoke() = azkarRepository.getAzkar()
}
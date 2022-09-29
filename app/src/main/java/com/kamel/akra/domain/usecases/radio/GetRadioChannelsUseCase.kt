package com.kamel.akra.domain.usecases.radio

import com.kamel.akra.domain.repositories.RadioRepository
import javax.inject.Inject

class GetRadioChannelsUseCase @Inject constructor(private val radioRepository: RadioRepository){
    suspend operator fun invoke() = radioRepository.getRadioChannels()
}
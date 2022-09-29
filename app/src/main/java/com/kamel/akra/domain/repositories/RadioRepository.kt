package com.kamel.akra.domain.repositories

import arrow.core.Either
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.domain.entities.RadioStation

interface RadioRepository {
    suspend fun getRadioChannels(): Either<AppFailure, List<RadioStation>>
}
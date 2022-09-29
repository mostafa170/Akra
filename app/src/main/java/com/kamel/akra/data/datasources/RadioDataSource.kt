package com.kamel.akra.data.datasources

import arrow.core.Either
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.domain.entities.RadioStation

interface RadioDataSource {
    suspend fun getRadioChannels(): Either<AppFailure, List<RadioStation>>
}
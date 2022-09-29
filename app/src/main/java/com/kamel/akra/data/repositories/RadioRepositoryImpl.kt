package com.kamel.akra.data.repositories

import arrow.core.Either
import com.kamel.akra.data.datasources.RadioDataSource
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.domain.entities.RadioStation
import com.kamel.akra.domain.repositories.RadioRepository
import javax.inject.Inject

class RadioRepositoryImpl @Inject constructor (private val radioDataSource: RadioDataSource): RadioRepository{
    override suspend fun getRadioChannels(): Either<AppFailure, List<RadioStation>> =
        radioDataSource.getRadioChannels()
}
package com.kamel.akra.data.repositories

import arrow.core.Either
import com.kamel.akra.data.datasources.AzkarDataSources
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.domain.entities.AzkarCategory
import com.kamel.akra.domain.repositories.AzkarRepository
import javax.inject.Inject

class AzkarRepositoryImpl @Inject constructor(private val azkarDataSources: AzkarDataSources): AzkarRepository{
    override suspend fun getAzkar(): Either<AppFailure, AzkarCategory> =
        azkarDataSources.getAzkar()
}
package com.kamel.akra.data.datasources

import arrow.core.Either
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.domain.entities.AzkarCategory

interface AzkarDataSources {
    suspend fun getAzkar(): Either<AppFailure, AzkarCategory>
}
package com.kamel.akra.domain.repositories

import arrow.core.Either
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.domain.entities.AzkarCategory

interface AzkarRepository {
    suspend fun getAzkar(): Either<AppFailure, AzkarCategory>
}
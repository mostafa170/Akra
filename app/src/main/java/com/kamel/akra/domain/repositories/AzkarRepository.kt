package com.kamel.akra.domain.repositories

import arrow.core.Either
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.domain.entities.AzkarCategory
import com.kamel.akra.domain.entities.Zekr

interface AzkarRepository {
    suspend fun getAzkar(): Either<AppFailure, AzkarCategory>
    suspend fun getLocalAzkar(): Either<AppFailure, List<Zekr>>

}
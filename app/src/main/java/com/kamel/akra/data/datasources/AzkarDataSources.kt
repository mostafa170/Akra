package com.kamel.akra.data.datasources

import arrow.core.Either
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.domain.entities.AzkarCategory
import com.kamel.akra.domain.entities.Zekr

interface AzkarDataSources {
    suspend fun getAzkar(): Either<AppFailure, AzkarCategory>
    suspend fun getLocalAzkar(): Either<AppFailure, List<Zekr>>
    suspend fun addLocalAzkar(zekr: Zekr)
}
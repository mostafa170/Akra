package com.kamel.akra.domain.repositories

import arrow.core.Either
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.domain.entities.HadethCategories

interface HadethRepository {
    suspend fun getHadethCategories(): Either<AppFailure, List<HadethCategories>>

}
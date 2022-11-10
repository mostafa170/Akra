package com.kamel.akra.data.datasources

import arrow.core.Either
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.domain.entities.HadethCategories

interface HadethDataSources {
    suspend fun getHadethCategories(): Either<AppFailure, List<HadethCategories>>

}
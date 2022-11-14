package com.kamel.akra.data.repositories

import arrow.core.Either
import com.kamel.akra.data.datasources.HadethDataSources
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.domain.entities.HadethCategories
import com.kamel.akra.domain.repositories.HadethRepository
import javax.inject.Inject

class HadethRepositoryImpl @Inject constructor(private val hadethDataSources: HadethDataSources):HadethRepository {
    override suspend fun getHadethCategories(): Either<AppFailure, List<HadethCategories>> =
        hadethDataSources.getHadethCategories()
}
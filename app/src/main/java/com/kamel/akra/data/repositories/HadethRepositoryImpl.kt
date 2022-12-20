package com.kamel.akra.data.repositories

import arrow.core.Either
import com.kamel.akra.data.datasources.HadethDataSources
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.domain.entities.HadethCategories
import com.kamel.akra.domain.entities.HadethDetails
import com.kamel.akra.domain.entities.HadethListById
import com.kamel.akra.domain.repositories.HadethRepository
import javax.inject.Inject

class HadethRepositoryImpl @Inject constructor(private val hadethDataSources: HadethDataSources):HadethRepository {
    override suspend fun getHadethCategories(): Either<AppFailure, List<HadethCategories>> =
        hadethDataSources.getHadethCategories()

    override suspend fun getHadethListById(
        category_id: Int,
        page: Int
    ): Either<AppFailure, HadethListById> = hadethDataSources.getHadethListById(category_id, page)

    override suspend fun getHadethDetails(hadeth_id: Int): Either<AppFailure, HadethDetails> =
        hadethDataSources.getHadethDetails(hadeth_id)
}
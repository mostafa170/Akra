package com.kamel.akra.domain.repositories

import arrow.core.Either
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.domain.entities.HadethCategories
import com.kamel.akra.domain.entities.HadethDetails
import com.kamel.akra.domain.entities.HadethListById

interface HadethRepository {
    suspend fun getHadethCategories(): Either<AppFailure, List<HadethCategories>>
    suspend fun getHadethListById(category_id: Int,page: Int): Either<AppFailure, HadethListById>
    suspend fun getHadethDetails(hadeth_id: Int): Either<AppFailure, HadethDetails>

}
package com.kamel.akra.data.datasources

import arrow.core.Either
import com.kamel.akra.data.models.toEntity
import com.kamel.akra.data.models.toHadethDetailsEntity
import com.kamel.akra.data.models.toHadethListByIdEntity
import com.kamel.akra.data.network.NetworkHadeth
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.data.utils.SomethingWentWrongFailure
import com.kamel.akra.domain.entities.HadethCategories
import com.kamel.akra.domain.entities.HadethDetails
import com.kamel.akra.domain.entities.HadethListById
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HadethDataSourcesImpl @Inject constructor(private val dispatcher: CoroutineDispatcher):HadethDataSources {
    override suspend fun getHadethCategories(): Either<AppFailure, List<HadethCategories>> =
        withContext(dispatcher) {
            try {
                val response = NetworkHadeth.apiCalls.downloadHadethsCategoriesAsync().await()
                          return@withContext Either.Right(response.toEntity())

            } catch (e: Exception) {
                return@withContext Either.Left(SomethingWentWrongFailure(e.message ?: "Something went wrong!"))
            }
        }

    override suspend fun getHadethListById(
        category_id: Int,
        page: Int
    ): Either<AppFailure, HadethListById>  =
        withContext(dispatcher) {
            try {
                val response = NetworkHadeth.apiCalls.downloadHadethsListByIdAsync(category_id,page).await()
                return@withContext Either.Right(response.toHadethListByIdEntity())

            } catch (e: Exception) {
                return@withContext Either.Left(SomethingWentWrongFailure(e.message ?: "Something went wrong!"))
            }
        }

    override suspend fun getHadethDetails(category_id: Int): Either<AppFailure, HadethDetails> =
        withContext(dispatcher) {
            try {
                val response = NetworkHadeth.apiCalls.getHadethOneDetailsAsync(category_id).await()
                return@withContext Either.Right(response.toHadethDetailsEntity())

            } catch (e: Exception) {
                return@withContext Either.Left(SomethingWentWrongFailure(e.message ?: "Something went wrong!"))
            }
        }


}
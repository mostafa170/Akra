package com.kamel.akra.data.datasources

import arrow.core.Either
import com.kamel.akra.data.models.toEntity
import com.kamel.akra.data.network.RadioApiManager
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.data.utils.SUCCESS
import com.kamel.akra.data.utils.SomethingWentWrongFailure
import com.kamel.akra.data.utils.getErrors
import com.kamel.akra.domain.entities.RadioStation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RadioDataSourceImpl @Inject constructor(private val dispatcher: CoroutineDispatcher) :RadioDataSource{
    override suspend fun getRadioChannels(): Either<AppFailure, List<RadioStation>> =
        withContext(dispatcher) {
            try {
                val response = RadioApiManager.apiCalls.getRadioChannelsAsync().await()
                when {
                    response.code() == SUCCESS -> {
                        response.body()?.let { body ->
                            body.let{
                                return@withContext Either.Right(body.radios.toEntity())
                            }
                        } ?: return@withContext Either.Left(
                            SomethingWentWrongFailure("No Body!")
                        )
                    }

                    else -> return@withContext Either.Left(
                        SomethingWentWrongFailure(
                            response.errorBody()?.getErrors()
                                ?: "${response.code()}: ${response.message()}"
                        )
                    )
                }
            } catch (e: Exception) {
                return@withContext Either.Left(SomethingWentWrongFailure(e.message ?: "Something went wrong!"))
            }
        }
}
package com.kamel.akra.data.datasources

import arrow.core.Either
import com.google.gson.Gson
import com.kamel.akra.data.models.QuranListResponse
import com.kamel.akra.data.models.toDomainQuranListen
import com.kamel.akra.data.models.toHadethListByIdEntity
import com.kamel.akra.data.models.toQuranListDomain
import com.kamel.akra.data.network.NetworkHadeth
import com.kamel.akra.data.network.QuranApiManager
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.data.utils.FileUtils
import com.kamel.akra.data.utils.SomethingWentWrongFailure
import com.kamel.akra.domain.entities.Quran
import com.kamel.akra.domain.entities.QuranAudio
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

class QuranDataSourcesImpl @Inject constructor(private val dispatcher: CoroutineDispatcher) :QuranDataSources{
    override suspend fun getLocalNameOfSurah(): Either<AppFailure, List<Quran>> =
        withContext(dispatcher) {
            val jsonFileString = FileUtils.getJsonDataFromAsset("list_of_surah.json")
            jsonFileString?.let {
                val jsonObject = JSONObject(it)
                val gson = Gson()

                val quranDto = gson.fromJson(
                    jsonObject.toString(), QuranListResponse::class.java
                )
                return@withContext Either.Right(quranDto.data.toQuranListDomain())
            } ?: return@withContext Either.Left(SomethingWentWrongFailure("jsonFileString is null"))
        }

    override suspend fun getQuranListenList(readerId: Int): Either<AppFailure, List<QuranAudio>> =
        withContext(dispatcher) {
            try {
                val response = QuranApiManager.apiCalls.getQuranListenAsync(readerId).await()
                return@withContext Either.Right(response.data.toDomainQuranListen())

            } catch (e: Exception) {
                return@withContext Either.Left(SomethingWentWrongFailure(e.message ?: "Something went wrong!"))
            }
        }
}
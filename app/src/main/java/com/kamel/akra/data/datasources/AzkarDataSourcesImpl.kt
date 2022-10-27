package com.kamel.akra.data.datasources

import android.app.Application
import arrow.core.Either
import com.google.gson.Gson
import com.kamel.akra.data.models.AzkarResponse
import com.kamel.akra.data.models.toEntityAzkarDomain
import com.kamel.akra.data.room.getDatabase
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.data.utils.FileUtils.getJsonDataFromAsset
import com.kamel.akra.data.utils.SomethingWentWrongFailure
import com.kamel.akra.domain.entities.AzkarCategory
import com.kamel.akra.domain.entities.Zekr
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject


class AzkarDataSourcesImpl @Inject constructor(private val dispatcher: CoroutineDispatcher, private val application: Application) :AzkarDataSources{
    override suspend fun getAzkar(): Either<AppFailure, AzkarCategory> =
        withContext(dispatcher) {
            val jsonFileString = getJsonDataFromAsset("azkar_list.json")
            jsonFileString?.let {
                val jsonObject = JSONObject(it)
                val gson = Gson()
                val azkarDto = gson.fromJson(
                    jsonObject.getJSONObject("data")
                        .toString(), AzkarResponse::class.java
                )
                return@withContext Either.Right(azkarDto.toEntityAzkarDomain())
            } ?: return@withContext Either.Left(SomethingWentWrongFailure("jsonFileString is null"))
        }

    override suspend fun getLocalAzkar(): Either<AppFailure, List<Zekr>> =
        withContext(dispatcher) {
            val database = getDatabase(application)
            val list:List<Zekr> =  database.azkarDao.getAllZekr()
            list.let {
                return@withContext Either.Right(list)
            }
        }

    override suspend fun addLocalAzkar(zekr: Zekr) =
        withContext(dispatcher) {
            val database = getDatabase(application)
            database.azkarDao.insertZekr(zekr)
        }
}



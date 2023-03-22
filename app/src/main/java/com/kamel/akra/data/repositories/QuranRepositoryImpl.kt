package com.kamel.akra.data.repositories

import arrow.core.Either
import com.kamel.akra.data.datasources.QuranDataSources
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.domain.entities.Quran
import com.kamel.akra.domain.entities.QuranAudio
import com.kamel.akra.domain.repositories.QuranRepository
import javax.inject.Inject

class QuranRepositoryImpl @Inject constructor (private val quranDataSources: QuranDataSources): QuranRepository {
    override suspend fun getLocalNameOfSurah(): Either<AppFailure, List<Quran>> =
        quranDataSources.getLocalNameOfSurah()

    override suspend fun getQuranListenList(readerId: Int): Either<AppFailure, List<QuranAudio>> =
        quranDataSources.getQuranListenList(readerId)
}
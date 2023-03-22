package com.kamel.akra.domain.repositories

import arrow.core.Either
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.domain.entities.Quran
import com.kamel.akra.domain.entities.QuranAudio

interface QuranRepository {
    suspend fun getLocalNameOfSurah(): Either<AppFailure, List<Quran>>
    suspend fun getQuranListenList(readerId: Int): Either<AppFailure, List<QuranAudio>>

}
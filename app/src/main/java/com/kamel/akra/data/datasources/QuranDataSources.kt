package com.kamel.akra.data.datasources

import arrow.core.Either
import com.kamel.akra.data.utils.AppFailure
import com.kamel.akra.domain.entities.Quran
import com.kamel.akra.domain.entities.QuranAudio

interface QuranDataSources {
    suspend fun getLocalNameOfSurah(): Either<AppFailure, List<Quran>>
    suspend fun getQuranListenList(readerId: Int): Either<AppFailure, List<QuranAudio>>

}
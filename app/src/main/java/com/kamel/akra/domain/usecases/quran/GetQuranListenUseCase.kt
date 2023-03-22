package com.kamel.akra.domain.usecases.quran

import com.kamel.akra.domain.repositories.QuranRepository
import javax.inject.Inject

class GetQuranListenUseCase @Inject constructor(private val quranRepository: QuranRepository){
    suspend operator fun invoke(readerID: Int) = quranRepository.getQuranListenList(readerID)
}
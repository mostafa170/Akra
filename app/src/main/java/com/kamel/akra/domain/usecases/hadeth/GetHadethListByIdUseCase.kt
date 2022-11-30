package com.kamel.akra.domain.usecases.hadeth

import com.kamel.akra.domain.repositories.HadethRepository
import javax.inject.Inject

class GetHadethListByIdUseCase @Inject constructor (private val hadethRepository: HadethRepository){
    suspend operator fun invoke(category_id: Int,page: Int) = hadethRepository.getHadethListById(category_id, page)
}
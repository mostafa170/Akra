package com.kamel.akra.domain.usecases.hadeth

import com.kamel.akra.domain.repositories.HadethRepository
import javax.inject.Inject

class GetHadethCategoryUseCase @Inject constructor (private val hadethRepository: HadethRepository){
    suspend operator fun invoke() = hadethRepository.getHadethCategories()
}
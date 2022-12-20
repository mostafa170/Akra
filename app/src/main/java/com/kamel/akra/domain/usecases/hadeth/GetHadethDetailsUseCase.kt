package com.kamel.akra.domain.usecases.hadeth

import com.kamel.akra.domain.repositories.HadethRepository
import javax.inject.Inject

class GetHadethDetailsUseCase @Inject constructor (private val hadethRepository: HadethRepository){
    suspend operator fun invoke(hadeth_id : Int) = hadethRepository.getHadethDetails(hadeth_id)
}
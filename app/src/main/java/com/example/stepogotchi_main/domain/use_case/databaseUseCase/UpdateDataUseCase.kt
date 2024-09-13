package com.example.stepogotchi_main.domain.use_case.databaseUseCase

import com.example.stepogotchi_main.data.model.Monster
import com.example.stepogotchi_main.domain.repository.DatabaseRepository

class UpdateDataUseCase(private val databaseRepository: DatabaseRepository) {
    suspend operator fun invoke(expAdded: Int){
        databaseRepository.updateMonster(expAdded)
    }
}
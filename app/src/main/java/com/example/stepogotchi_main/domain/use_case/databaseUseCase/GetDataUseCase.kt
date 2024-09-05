package com.example.stepogotchi_main.domain.use_case.databaseUseCase

import com.example.stepogotchi_main.data.model.Monster
import com.example.stepogotchi_main.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow

class GetDataUseCase(private val repository: DatabaseRepository) {
    operator fun invoke(): Flow<Monster> {
        return repository.getData()
    }

}
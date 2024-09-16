package com.example.stepogotchi_main.domain.use_case.databaseUseCase

import com.example.stepogotchi_main.data.model.Exercise
import com.example.stepogotchi_main.data.repository.DatabaseRepository

class AddExerciseUseCase(private val databaseRepository: DatabaseRepository) {
    suspend operator fun invoke(exercise: Exercise){
        databaseRepository.addExercise(exercise)
    }
}
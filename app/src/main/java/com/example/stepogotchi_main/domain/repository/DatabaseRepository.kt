package com.example.stepogotchi_main.domain.repository

import com.example.stepogotchi_main.data.model.Exercise
import com.example.stepogotchi_main.data.model.Monster
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    fun getData(): Flow<Monster>
    suspend fun insertMonster(monster: Monster)
    suspend fun updateMonster(monster: Monster)
    suspend fun addExercise(exercise: Exercise)

}
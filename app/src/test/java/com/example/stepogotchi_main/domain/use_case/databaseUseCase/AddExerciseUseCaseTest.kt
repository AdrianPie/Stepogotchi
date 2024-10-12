package com.example.stepogotchi_main.domain.use_case.databaseUseCase

import com.example.stepogotchi_main.data.model.Exercise
import com.example.stepogotchi_main.domain.repository.DatabaseRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class AddExerciseUseCaseTest {
    @Mock
    private lateinit var databaseRepository: DatabaseRepository

    private lateinit var addExerciseUseCase: AddExerciseUseCase


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        addExerciseUseCase = AddExerciseUseCase(databaseRepository)
    }


    @Test
    fun `invoke should call addExercise on repository`(): Unit = runBlocking {
        val exercise = Exercise()

        addExerciseUseCase(exercise)

        verify(databaseRepository).addExercise(exercise)
    }
}
package com.example.stepogotchi_main.domain.use_case.databaseUseCase

import org.junit.Assert.*

import com.example.stepogotchi_main.data.model.Monster
import com.example.stepogotchi_main.domain.repository.DatabaseRepository
import com.example.stepogotchi_main.domain.use_case.databaseUseCase.InsertDataUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class InsertDataUseCaseTest {

    @Mock
    private lateinit var databaseRepository: DatabaseRepository

    private lateinit var insertDataUseCase: InsertDataUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        insertDataUseCase = InsertDataUseCase(databaseRepository)
    }

    @Test
    fun `invoke should call insertMonster on repository`() = runBlocking {
        val monster = Monster()

        insertDataUseCase(monster)

        verify(databaseRepository).insertMonster(monster)
    }
}
package com.example.stepogotchi_main.domain.use_case.databaseUseCase

import com.example.stepogotchi_main.domain.repository.DatabaseRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class UpdateDataUseCaseTest {

    @Mock
    private lateinit var databaseRepository: DatabaseRepository

    private lateinit var updateDataUseCase: UpdateDataUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        updateDataUseCase = UpdateDataUseCase(databaseRepository)
    }

    @Test
    fun `invoke should call updateMonster on repository`() = runBlocking {
        val expAdded = 10

        updateDataUseCase(expAdded)

        verify(databaseRepository).updateMonster(expAdded)
    }
}
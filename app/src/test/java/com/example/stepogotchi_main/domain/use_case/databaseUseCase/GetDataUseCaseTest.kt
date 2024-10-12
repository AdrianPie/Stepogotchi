package com.example.stepogotchi_main.domain.use_case.databaseUseCase

import com.example.stepogotchi_main.data.model.Monster
import org.junit.Assert.*

import com.example.stepogotchi_main.domain.repository.DatabaseRepository
import com.example.stepogotchi_main.domain.use_case.databaseUseCase.GetDataUseCase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetDataUseCaseTest {

    @Mock
    private lateinit var databaseRepository: DatabaseRepository

    private lateinit var getDataUseCase: GetDataUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getDataUseCase = GetDataUseCase(databaseRepository)
    }

    @Test
    fun `invoke should return data from repository`() = runBlocking {
        val monster = Monster(/* initialize with test data */)
        `when`(databaseRepository.getData()).thenReturn(flowOf(monster))

        val result = getDataUseCase()

        // Add assertions to verify the result
    }
}
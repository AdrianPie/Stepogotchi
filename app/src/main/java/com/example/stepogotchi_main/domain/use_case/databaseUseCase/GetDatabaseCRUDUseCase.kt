package com.example.stepogotchi_main.domain.use_case.databaseUseCase

import javax.inject.Inject

data class GetDatabaseCRUDUseCase @Inject constructor(
    val getData: GetDataUseCase,
    val insertData: InsertDataUseCase,
    val updateDate: UpdateDataUseCase,
    val addExercise: AddExerciseUseCase
)

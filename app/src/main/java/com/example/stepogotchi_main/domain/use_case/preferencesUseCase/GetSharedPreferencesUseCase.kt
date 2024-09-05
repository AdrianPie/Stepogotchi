package com.example.stepogotchi_main.domain.use_case.preferencesUseCase

import javax.inject.Inject

data class GetSharedPreferencesUseCase @Inject constructor(
    val getSteps: GetStepsUseCase,
    val getSystemSteps: GetSystemStepsUseCase,
    val saveSteps: SaveStepsUseCase,
    val saveSystemSteps: SaveSystemStepsUseCase,
    val resetSharedPreferences: ResetSharedPreferencesUseCase,
    val saveLeftStepsUseCase: SaveLeftStepsUseCase,
    val getLeftStepsUseCase: GetLeftStepsUseCase
)

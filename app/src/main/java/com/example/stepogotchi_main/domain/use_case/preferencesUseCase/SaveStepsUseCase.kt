package com.example.stepogotchi_main.domain.use_case.preferencesUseCase

import com.example.stepogotchi_main.data.repository.PreferencesRepository

class SaveStepsUseCase(private val preferencesRepository: PreferencesRepository) {
    operator fun invoke(steps: Int) {
        preferencesRepository.saveSteps(steps)
    }
}
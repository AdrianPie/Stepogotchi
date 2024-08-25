package com.example.stepogotchi_main.domain.use_case

import com.example.stepogotchi_main.domain.repository.PreferencesRepository

class SaveStepsUseCase(private val preferencesRepository: PreferencesRepository) {
    operator fun invoke(steps: Int) {
        preferencesRepository.saveSteps(steps)
    }
}
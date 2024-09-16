package com.example.stepogotchi_main.domain.use_case.preferencesUseCase

import com.example.stepogotchi_main.domain.repository.PreferencesRepository

class SaveLeftStepsUseCase(private val preferencesRepository: PreferencesRepository) {
    operator fun invoke(steps: Int) {
        preferencesRepository.saveStepsLeft(steps)
    }
}
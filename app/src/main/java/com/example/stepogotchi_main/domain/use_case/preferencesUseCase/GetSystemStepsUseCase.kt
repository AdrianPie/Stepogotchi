package com.example.stepogotchi_main.domain.use_case.preferencesUseCase

import com.example.stepogotchi_main.data.repository.PreferencesRepository

class GetSystemStepsUseCase(private val preferencesRepository: PreferencesRepository) {
    operator fun invoke(): Int {
        return preferencesRepository.getSystemSteps()
    }
}
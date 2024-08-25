package com.example.stepogotchi_main.domain.use_case

import com.example.stepogotchi_main.domain.repository.PreferencesRepository

class GetStepsUseCase(private val preferencesRepository: PreferencesRepository) {
    operator fun invoke(): Int {
        return preferencesRepository.getSteps()
    }
}
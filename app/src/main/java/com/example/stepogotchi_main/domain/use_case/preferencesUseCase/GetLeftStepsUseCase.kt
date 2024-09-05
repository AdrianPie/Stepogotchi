package com.example.stepogotchi_main.domain.use_case.preferencesUseCase

import com.example.stepogotchi_main.domain.repository.PreferencesRepository

class GetLeftStepsUseCase(private val preferencesRepository: PreferencesRepository) {
    operator fun invoke(): Int {
        return preferencesRepository.getStepsLeft()
    }
}
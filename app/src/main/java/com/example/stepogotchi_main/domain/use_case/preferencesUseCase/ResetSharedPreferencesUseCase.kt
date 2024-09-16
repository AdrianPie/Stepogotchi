package com.example.stepogotchi_main.domain.use_case.preferencesUseCase

import com.example.stepogotchi_main.domain.repository.PreferencesRepository

class ResetSharedPreferencesUseCase(private val preferencesRepository: PreferencesRepository) {
    operator fun invoke() {
        preferencesRepository.resetPreferences()
    }
}
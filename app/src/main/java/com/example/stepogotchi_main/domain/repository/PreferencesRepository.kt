package com.example.stepogotchi_main.domain.repository

interface PreferencesRepository {
    fun saveSteps(steps: Int)
    fun getSteps(): Int
    fun saveSystemSteps(systemSteps: Int)
    fun getSystemSteps(): Int
    fun resetPreferences()
}
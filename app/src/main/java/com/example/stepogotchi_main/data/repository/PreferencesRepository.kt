package com.example.stepogotchi_main.data.repository

interface PreferencesRepository {
    fun saveSteps(steps: Int)
    fun getSteps(): Int
    fun saveSystemSteps(systemSteps: Int)
    fun getSystemSteps(): Int
    fun saveStepsLeft(systemSteps: Int)
    fun getStepsLeft(): Int
    fun resetPreferences()
}
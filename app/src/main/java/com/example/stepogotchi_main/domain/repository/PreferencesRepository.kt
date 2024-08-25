package com.example.stepogotchi_main.domain.repository

interface PreferencesRepository {
    fun saveSteps(steps: Int)
    fun getSteps(): Int
}
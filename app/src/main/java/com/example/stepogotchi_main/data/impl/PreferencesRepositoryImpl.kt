package com.example.stepogotchi_main.data.impl

import android.content.Context
import android.content.SharedPreferences
import com.example.stepogotchi_main.domain.repository.PreferencesRepository


class PreferencesRepositoryImpl(context: Context) : PreferencesRepository {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "app_prefs"
        private const val KEY_STEPS = "key_steps"
        private const val KEY_SYSTEM_STEPS = "key_system_steps"
        private const val KEY_LEFT_STEPS = "key_left_steps"
    }

    override fun saveSteps(steps: Int) {
        sharedPreferences.edit().putInt(KEY_STEPS, steps).apply()
    }

    override fun getSteps(): Int {
        return sharedPreferences.getInt(KEY_STEPS, 0)
    }
    override fun saveSystemSteps(systemSteps: Int) {
        sharedPreferences.edit().putInt(KEY_SYSTEM_STEPS, systemSteps).apply()
    }

    override fun getSystemSteps(): Int {
        return sharedPreferences.getInt(KEY_SYSTEM_STEPS, 0)
    }

    override fun saveStepsLeft(systemSteps: Int) {
        sharedPreferences.edit().putInt(KEY_LEFT_STEPS, systemSteps).apply()
    }

    override fun getStepsLeft(): Int {
        return sharedPreferences.getInt(KEY_LEFT_STEPS, 0)
    }

    override fun resetPreferences() {
        sharedPreferences.edit().clear().apply()
    }
}
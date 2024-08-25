package com.example.stepogotchi_main.data

import android.content.Context
import android.content.SharedPreferences
import com.example.stepogotchi_main.domain.repository.PreferencesRepository


class PreferencesRepositoryImpl(context: Context) : PreferencesRepository {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "app_prefs"
        private const val KEY_STEPS = "key_steps"
    }

    override fun saveSteps(steps: Int) {
        sharedPreferences.edit().putInt(KEY_STEPS, steps).apply()
    }

    override fun getSteps(): Int {
        return sharedPreferences.getInt(KEY_STEPS, 0)
    }
}
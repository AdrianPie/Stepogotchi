package com.example.stepogotchi_main.presentation.screens.settingsScreen

import androidx.lifecycle.ViewModel
import com.example.stepogotchi_main.data.util.GlobalLogIn
import com.example.stepogotchi_main.data.repository.AuthRepository
import com.example.stepogotchi_main.data.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private var authRepository: AuthRepository,
    private var globalLogIn: GlobalLogIn
): ViewModel() {

    fun logout(){
        authRepository.logout()
        globalLogIn.changeLoggedState(false)
    }
}
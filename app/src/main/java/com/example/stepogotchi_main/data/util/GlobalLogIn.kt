package com.example.stepogotchi_main.data.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalLogIn @Inject constructor()  {
    private val _isLogged = MutableStateFlow(false)
    val isLogged = _isLogged.asStateFlow()

    fun changeLoggedState(logged: Boolean){
        _isLogged.value = logged
    }
}
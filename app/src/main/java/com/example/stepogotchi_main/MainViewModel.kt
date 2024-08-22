package com.example.stepogotchi_main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stepogotchi_main.data.util.GlobalLogIn
import com.example.stepogotchi_main.data.util.Screen
import com.example.stepogotchi_main.domain.repository.AuthRepository
import com.example.stepogotchi_main.sensor.MeasurableSensor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val globalLogInCheck: GlobalLogIn,
    private val authRepository: AuthRepository
): ViewModel() {

    val visiblePermissionDialogQueue = mutableListOf<String>()

    fun dismissDialog(){
        visiblePermissionDialogQueue.removeLast()
    }
    fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ){
        if (!isGranted){
            visiblePermissionDialogQueue.add(0,permission)
        }
    }
    val currentUser = authRepository.currentUser

    val logged = globalLogInCheck.isLogged

    private val _ready = MutableStateFlow(false)
    val ready = _ready.asStateFlow()

    private val _loggedIn = MutableStateFlow(false)
    val loggedIn = _loggedIn.asStateFlow()

    private val _startDest = MutableStateFlow(Screen.Login.name)
    val startDest = _startDest.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000L)
            _ready.value = true
        }
        if (currentUser != null) {
            globalLogInCheck.changeLoggedState(true)
            _loggedIn.value = true
            _startDest.value = Screen.Home.name
        }
    }
}
package com.example.stepogotchi_main.presentation.loginScreen

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stepogotchi_main.data.model.Monster
import com.example.stepogotchi_main.domain.model.LoginInputValidationType
import com.example.stepogotchi_main.domain.use_case.ValidateLoginInputUseCase
import com.example.stepogotchi_main.presentation.state.LoginState
import com.example.stepogotchi_main.data.util.GlobalLogIn
import com.example.stepogotchi_main.domain.repository.AuthRepository
import com.example.stepogotchi_main.domain.repository.MonsterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val globalLogInRepo: GlobalLogIn,
    private val validateLoginInputUseCase: ValidateLoginInputUseCase,
    private val authRepository: AuthRepository,
    private val monsterRepository: MonsterRepository
) : ViewModel() {



    private fun changeLoginStatus(){
        globalLogInRepo.changeLoggedState(true)
    }
    var loginState by mutableStateOf(LoginState())
        private set

    fun onEmailInputChange(newValue: String){
        loginState = loginState.copy(
            emailInput = newValue
        )
        checkInputValidation()
    }
    fun onPasswordInputChange(newValue: String){
        loginState = loginState.copy(passwordInput = newValue)
        checkInputValidation()
    }

    fun onToggleVisualTransformation(){
        loginState = loginState.copy(isPasswordShown = !loginState.isPasswordShown)
    }

    fun onLoginClick(){

        loginState = loginState.copy(isLoading = true)
        viewModelScope.launch {
            val loginResult = authRepository.login(
                email = loginState.emailInput,
                password = loginState.passwordInput
            )
            loginState = loginState.copy(isSuccessfullyLoggedIn = loginResult.success!!)

            if (loginState.isSuccessfullyLoggedIn){
                changeLoginStatus()
            }
            loginState = loginState.copy(errorMessageLoginProcess = loginResult.message)

            loginState = loginState.copy(isLoading = false)


        }
    }

    private fun checkInputValidation(){
        val validationResult = validateLoginInputUseCase(
            loginState.emailInput,
            loginState.passwordInput
        )
        processInputValidationType(validationResult)
    }

    private fun processInputValidationType(type: LoginInputValidationType){
        loginState = when(type){
            LoginInputValidationType.EmptyField -> {
                loginState.copy(errorMessageInput = "Empty fields left", isInputValid = false)
            }
            LoginInputValidationType.NoEmail -> {
                loginState.copy(errorMessageInput = "No valid email", isInputValid = false)
            }
            LoginInputValidationType.Valid -> {
                loginState.copy(errorMessageInput = null, isInputValid = true)
            }
        }
    }

}
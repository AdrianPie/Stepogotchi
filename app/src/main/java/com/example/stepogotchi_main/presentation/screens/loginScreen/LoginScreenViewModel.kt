package com.example.stepogotchi_main.presentation.screens.loginScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stepogotchi_main.domain.model.LoginInputValidationType
import com.example.stepogotchi_main.domain.use_case.validateUseCase.ValidateLoginInputUseCase
import com.example.stepogotchi_main.presentation.state.LoginState
import com.example.stepogotchi_main.data.util.GlobalLogIn
import com.example.stepogotchi_main.data.util.SnackBarController
import com.example.stepogotchi_main.data.util.SnackBarEvent
import com.example.stepogotchi_main.domain.repository.AuthRepository
import com.example.stepogotchi_main.domain.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val globalLogIn: GlobalLogIn,
    private val validateLoginInputUseCase: ValidateLoginInputUseCase,
    private val authRepository: AuthRepository,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {



    private fun changeLoginStatus(){
        globalLogIn.changeLoggedState(true)
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
            loginState = loginState.copy(
                isSuccessfullyLoggedIn = loginResult.success!!,
                errorMessageLoginProcess =  loginResult.message,
                isLoading = false
            )

            if (loginState.isSuccessfullyLoggedIn){ changeLoginStatus() }

            showSnackBar(loginState.errorMessageLoginProcess)

        }
    }
    private fun showSnackBar(inputMessage: String?){
        viewModelScope.launch {

            var snackbarMessage = ""

            if (inputMessage == null){
                snackbarMessage = "Sign in successful"
            } else {
                snackbarMessage = inputMessage
            }

            SnackBarController.sendEvent(
                event = SnackBarEvent(
                    message = snackbarMessage
                )
            )
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
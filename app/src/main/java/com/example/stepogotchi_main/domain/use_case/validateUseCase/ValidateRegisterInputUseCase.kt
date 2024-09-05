package com.example.stepogotchi_main.domain.use_case.validateUseCase

import com.example.stepogotchi_main.domain.model.RegisterInputValidationType
import com.example.stepogotchi_main.data.util.containsNumber
import com.example.stepogotchi_main.data.util.containsSpecialChar
import com.example.stepogotchi_main.data.util.containsUpperCase

class ValidateRegisterInputUseCase {

    operator fun invoke(
        email: String,
        password: String,
        passwordRepeated: String
    ): RegisterInputValidationType {

        if (email.isEmpty() || password.isEmpty() || passwordRepeated.isEmpty()){
            return RegisterInputValidationType.EmptyField
        }
        if("@" !in email){
            return RegisterInputValidationType.NoEmail
        }
        if(password!= passwordRepeated){
            return RegisterInputValidationType.PasswordsDoNotMatch
        }
        if(password.count() < 8){
            return RegisterInputValidationType.PasswordTooShort
        }
        if(!password.containsNumber()){
            return RegisterInputValidationType.PasswordNumberMissing
        }
        if(!password.containsUpperCase()){
            return RegisterInputValidationType.PasswordUpperCaseMissing
        }
        if(!password.containsSpecialChar()){
            return RegisterInputValidationType.PasswordSpecialCharMissing
        }
        return RegisterInputValidationType.Valid

    }
}
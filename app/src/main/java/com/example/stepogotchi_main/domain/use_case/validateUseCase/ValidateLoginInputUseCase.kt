package com.example.stepogotchi_main.domain.use_case.validateUseCase

import com.example.stepogotchi_main.domain.model.LoginInputValidationType

class ValidateLoginInputUseCase() {

    operator fun invoke(email: String, password:String): LoginInputValidationType {
        if(email.isEmpty() || password.isEmpty()){
            return LoginInputValidationType.EmptyField
        }
        if("@" !in email){
            return LoginInputValidationType.NoEmail
        }
        return LoginInputValidationType.Valid
    }

}
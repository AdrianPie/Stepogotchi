package com.example.stepogotchi_main.domain.use_case.validateUseCase

import com.example.stepogotchi_main.domain.model.LoginInputValidationType
import com.google.common.truth.Truth.assertThat

import org.junit.Test


class ValidateLoginInputUseCaseTest {

    private val validateLoginInputUseCase = ValidateLoginInputUseCase()

    @Test
    fun `test empty field return validation type empty field`() {
        val result = validateLoginInputUseCase(email = "", password = "password")
        assertThat(result).isEqualTo(LoginInputValidationType.EmptyField)
    }
    @Test
    fun `test no email return validation type  no email`(){
        val result = validateLoginInputUseCase(email = "blabla.com", password = "password")
        assertThat(result).isEqualTo(LoginInputValidationType.NoEmail)
    }
    @Test
    fun `test everything is correct return validation type valid`(){
        val result = validateLoginInputUseCase(email = "email@gmail.com", password = "password")
        assertThat(result).isEqualTo(LoginInputValidationType.Valid)
    }


}
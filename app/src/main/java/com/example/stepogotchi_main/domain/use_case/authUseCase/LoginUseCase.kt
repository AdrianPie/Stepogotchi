package com.example.stepogotchi_main.domain.use_case.authUseCase

import com.example.stepogotchi_main.data.util.Resource
import com.example.stepogotchi_main.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Resource<FirebaseUser> {
        return authRepository.login(email, password)
    }
}
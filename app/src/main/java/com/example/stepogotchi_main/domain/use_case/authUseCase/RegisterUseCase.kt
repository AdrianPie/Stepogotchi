package com.example.stepogotchi_main.domain.use_case.authUseCase

import com.example.stepogotchi_main.data.Resource
import com.example.stepogotchi_main.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class RegisterUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String, name: String): Resource<FirebaseUser> {
        return authRepository.register(email, password, name)
    }
}
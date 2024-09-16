package com.example.stepogotchi_main.domain.repository

import com.example.stepogotchi_main.data.util.Resource
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun register(email: String, password: String, name : String): Resource<FirebaseUser>
    fun logout()
}
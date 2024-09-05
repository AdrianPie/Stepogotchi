package com.example.stepogotchi_main.data.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stepogotchi_main.presentation.screens.homescreen.HomeScreen
import com.example.stepogotchi_main.presentation.screens.loginScreen.LoginScreen
import com.example.stepogotchi_main.presentation.screens.registerScreen.RegisterScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.name
    ){

        composable(Screen.Register.name){

        }

    }

}
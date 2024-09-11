package com.example.stepogotchi_main.presentation.screens.homescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stepogotchi_main.data.model.Monster
import com.example.stepogotchi_main.data.util.GlobalLogIn
import com.example.stepogotchi_main.domain.repository.AuthRepository
import com.example.stepogotchi_main.domain.repository.DatabaseRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
   private var globalLogIn: GlobalLogIn,
   private var authRepository: AuthRepository,
   private var databaseRepository: DatabaseRepository
): ViewModel() {

   private val _monster = MutableStateFlow(Monster())
   val monster = _monster.asStateFlow()

   init {
       viewModelScope.launch(Dispatchers.IO) {
          databaseRepository.getData().collect { storedMonster ->
             _monster.value = storedMonster
          }
       }
   }
   fun logout(){
      authRepository.logout()
      globalLogIn.changeLoggedState(false)
   }

}
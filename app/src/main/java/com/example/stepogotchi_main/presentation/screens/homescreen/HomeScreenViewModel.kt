package com.example.stepogotchi_main.presentation.screens.homescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stepogotchi_main.data.model.Monster
import com.example.stepogotchi_main.data.util.GlobalLogIn
import com.example.stepogotchi_main.domain.repository.AuthRepository
import com.example.stepogotchi_main.domain.repository.DatabaseRepository

import dagger.hilt.android.lifecycle.HiltViewModel
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
      Log.d("dupsko", ":122 ")
       viewModelScope.launch {
          Log.d("dupsko", ":1 3")
          //val dataExists = monsterRepository.getData().firstOrNull() != null
          Log.d("dupsko", ":1 ")
          if (false) {
             println("Dane istnieją w bazie danych.")
             Log.d("dupsko", ":2")
          } else {
             println("Brak danych w bazie danych.")
             databaseRepository.insertMonster(Monster())
             Log.d("dupsko", ":3xx ")
          }
       }
   }
   fun logout(){
      authRepository.logout()
      globalLogIn.changeLoggedState(false)
   }

}
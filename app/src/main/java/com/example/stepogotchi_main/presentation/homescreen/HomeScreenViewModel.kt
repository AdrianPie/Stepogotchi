package com.example.stepogotchi_main.presentation.homescreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stepogotchi_main.data.model.Monster
import com.example.stepogotchi_main.data.util.GlobalLogIn
import com.example.stepogotchi_main.domain.repository.AuthRepository
import com.example.stepogotchi_main.domain.repository.MonsterRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
   private var globalLogIn: GlobalLogIn,
   private var authRepository: AuthRepository,
   private var monsterRepository: MonsterRepository
): ViewModel() {

   private val _monster = MutableStateFlow(Monster())
   val monster = _monster.asStateFlow()

   init {
       viewModelScope.launch {
//          monsterRepository.getData().collect{
//             _monster.value = it
//          }
       }
   }
   fun logout(){
      authRepository.logout()
      globalLogIn.changeLoggedState(false)
   }

   val logged = globalLogIn.isLogged
}
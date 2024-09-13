package com.example.stepogotchi_main.presentation.screens.homescreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stepogotchi_main.R
import com.example.stepogotchi_main.data.model.Monster
import com.example.stepogotchi_main.data.util.GlobalLogIn
import com.example.stepogotchi_main.domain.repository.AuthRepository
import com.example.stepogotchi_main.domain.repository.DatabaseRepository
import com.example.stepogotchi_main.presentation.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
   private var globalLogIn: GlobalLogIn,
   private var authRepository: AuthRepository,
   private var databaseRepository: DatabaseRepository
): ViewModel() {


   var homeState by mutableStateOf(HomeState())
   init {
       viewModelScope.launch {
          databaseRepository.getData().collect { storedMonster ->
             calculateTotalStepsMade(storedMonster)
             parseMonsterStats(storedMonster)
             homeState = homeState.copy(
                monster = storedMonster
             )
          }
       }
   }


   private fun calculateTotalStepsMade(monster: Monster){
      var totalSteps = 0
      for (exercise in monster.exercises){
         totalSteps += exercise.steps
      }
      homeState = homeState.copy(totalSteps = totalSteps)
   }

   private fun parseMonsterStats(monster: Monster){
      Log.d("dupsko", "parseMonsterStats: 112")
      homeState = when(monster.exp) {
         in 0..100 -> homeState.copy(level = 1)
         in 101..500 -> homeState.copy(level = 2)
         in 501..100000 -> homeState.copy(level = 3)
         else -> homeState.copy(level = 3)
      }
      val expFloat = monster.exp.toFloat()

      homeState = when(homeState.level){
         1 ->{
             homeState.copy(
               progress = expFloat/100,
               monsterPicture = R.drawable.monster_level_one
            )
         }
         2 -> {
             homeState.copy(
               progress = expFloat/400,
               monsterPicture = R.drawable.monster_level_two
            )
         }
         3 -> {
             homeState.copy(
               progress = expFloat/95000,
               monsterPicture = R.drawable.monster_level_three
            )
         }
         else ->  homeState.copy(progress = 0.0f)
      }


   }

}
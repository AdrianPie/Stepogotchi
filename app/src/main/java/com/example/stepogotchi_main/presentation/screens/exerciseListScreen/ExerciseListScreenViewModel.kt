package com.example.stepogotchi_main.presentation.screens.exerciseListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stepogotchi_main.data.model.Monster
import com.example.stepogotchi_main.domain.use_case.databaseUseCase.GetDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseListScreenViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase
): ViewModel()
 {
    val _monster = MutableStateFlow(Monster())
    val monster = _monster.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            getDataUseCase().collect { storedMonster ->
                _monster.value = storedMonster
            }
        }
    }
}
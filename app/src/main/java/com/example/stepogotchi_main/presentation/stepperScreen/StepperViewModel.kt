package com.example.stepogotchi_main.presentation.stepperScreen
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stepogotchi_main.data.local.StepSensorService
import com.example.stepogotchi_main.data.model.Exercise
import com.example.stepogotchi_main.data.util.getCurrentDateString
import com.example.stepogotchi_main.domain.repository.MonsterRepository
import com.example.stepogotchi_main.domain.use_case.preferencesUseCase.GetSharedPreferencesUseCase
import com.example.stepogotchi_main.domain.use_case.preferencesUseCase.GetStepsUseCase
import com.example.stepogotchi_main.domain.use_case.preferencesUseCase.SaveStepsUseCase
import com.example.stepogotchi_main.presentation.state.StepperState
import com.example.stepogotchi_main.sensor.MeasurableSensor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StepperViewModel@Inject constructor(
    private val stepCounter: MeasurableSensor,
    private val sharedPreferencesUseCase: GetSharedPreferencesUseCase,
    private val monsterRepository: MonsterRepository

): ViewModel() {

    var stepsState by mutableStateOf(StepperState())
        private set


    private val _percent = MutableStateFlow(0)
    val percent = _percent.asStateFlow()

    private val _test = MutableStateFlow("2")
    val test = _test.asStateFlow()

    private val _test2 = MutableStateFlow("test 2")
    val test2 = _test2.asStateFlow()


    init {
        if (sharedPreferencesUseCase.getSteps() != 0){
            stepsState = stepsState.copy(
                systemStartingSteps = sharedPreferencesUseCase.getSystemSteps(),
                stepsGoal = sharedPreferencesUseCase.getSteps(),
                sensorSteps = 0,
                stepsGoalCreated = true
            )
            stepCounter.startListening()
            stepCounter.setOnSensorValueChangedListener { steps ->
                stepsState = stepsState.copy(
                    sensorSteps = steps[0].toInt()
                )
            }
        }

    }

    fun finishStepGoal(){
        viewModelScope.launch {

            val monster = monsterRepository.getData().first()

            val newExercise = Exercise().apply {
                date = getCurrentDateString()
                this.steps = stepsState.stepsGoal
            }

            monster.exercises.add(newExercise)

            monsterRepository.updateMonster(monster = monster)

            stepsState = StepperState()
        }
    }
    fun testResetShared(){
        sharedPreferencesUseCase.resetSharedPreferences()
        stepsState = StepperState()

    }
    fun addTestStep(){
        stepsState = stepsState.copy(
            stepsLeft = stepsState.stepsLeft - 1
        )
        _percent.value = ((stepsState.stepsLeft.toFloat() / stepsState.stepsGoal) * 100).toInt()
    }


    fun setStepsInputChange(steps: String){
        stepsState = stepsState.copy(stepsGoalInput = steps)
    }

    fun createStepsGoal(){
        sharedPreferencesUseCase.saveSteps(stepsState.stepsGoalInput.toInt())
        stepCounter.startListening()
        val systemStepsCollected = false
        stepCounter.setOnSensorValueChangedListener { steps ->
            if (!systemStepsCollected){
                sharedPreferencesUseCase.saveSystemSteps(steps[0].toInt())
                stepsState = stepsState.copy(
                    systemStartingSteps = steps[0].toInt()
                )
            }
            stepsState = stepsState.copy(
                sensorSteps = steps[0].toInt()
            )
        }

        stepsState = stepsState.copy(
            stepsGoal = stepsState.stepsGoalInput.toInt(),
            stepsGoalCreated = true,
        )


        stepsState = stepsState.copy(stepsLeft = stepsState.stepsGoal - stepsState.systemStartingSteps)

    }

    override fun onCleared() {
        super.onCleared()
        stepCounter.stopListening()
    }


}
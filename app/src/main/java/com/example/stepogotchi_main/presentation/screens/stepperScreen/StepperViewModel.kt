package com.example.stepogotchi_main.presentation.screens.stepperScreen
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stepogotchi_main.data.model.Exercise
import com.example.stepogotchi_main.data.model.Monster
import com.example.stepogotchi_main.data.util.getCurrentDateString
import com.example.stepogotchi_main.domain.repository.DatabaseRepository
import com.example.stepogotchi_main.domain.use_case.preferencesUseCase.GetSharedPreferencesUseCase
import com.example.stepogotchi_main.presentation.state.StepperState
import com.example.stepogotchi_main.sensor.MeasurableSensor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StepperViewModel@Inject constructor(
    private val stepCounter: MeasurableSensor,
    private val sharedPreferencesUseCase: GetSharedPreferencesUseCase,
    private val databaseRepository: DatabaseRepository

): ViewModel() {

    var stepsState by mutableStateOf(StepperState())
        private set


    private val _percent = MutableStateFlow(0)
    val percent = _percent.asStateFlow()

    var isDialogShown by mutableStateOf(false)
        private set





    init {
       countingStepsOnBeginIfExerciseExist()
    }

    fun finishStepGoal(){
        viewModelScope.launch {
            addFinishedExercise()
            databaseRepository.updateMonster(50)
            stepsState = StepperState()
            sharedPreferencesUseCase.resetSharedPreferences()
            isDialogShown = true
        }
    }

    fun setStepsInputChange(steps: String){
        stepsState = stepsState.copy(stepsGoalInput = steps)
    }
    fun onDismissDialog(){
        isDialogShown = false
    }

    fun createStepsGoal(){
        sharedPreferencesUseCase.saveSteps(stepsState.stepsGoalInput.toInt())
        stepCounter.startListening()
        var systemStepsCollected = false
        stepCounter.setOnSensorValueChangedListener { steps ->
            if (!systemStepsCollected){
                sharedPreferencesUseCase.saveSystemSteps(steps[0].toInt())
                saveState(systemStartingSteps = steps[0].toInt())
                systemStepsCollected = true
            }
            saveState(
                sensorSteps = steps[0].toInt(),
                stepsLeft = stepsState.stepsGoal - (steps[0].toInt() - stepsState.systemStartingSteps),
                percentDone = ((stepsState.stepsGoal - stepsState.stepsLeft)*100) / stepsState.stepsGoal
            )
            sharedPreferencesUseCase.saveLeftStepsUseCase(stepsState.stepsLeft)
        }
        saveState(
            stepsGoal = stepsState.stepsGoalInput.toInt(),
            stepsGoalCreated = true,
            stepsLeft = stepsState.stepsGoal - stepsState.systemStartingSteps
        )
    }
    private fun countingStepsOnBeginIfExerciseExist() {
        if (sharedPreferencesUseCase.getSteps() != 0) {
            saveState(
                systemStartingSteps = sharedPreferencesUseCase.getSystemSteps(),
                stepsGoal = sharedPreferencesUseCase.getSteps(),
                sensorSteps = 0,
                stepsGoalCreated = true,
                stepsLeft = sharedPreferencesUseCase.getLeftStepsUseCase()
            )
            saveState(percentDone = ((stepsState.stepsGoal - stepsState.stepsLeft)*100) / stepsState.stepsGoal)

            if (stepsState.stepsLeft <= 0) {
                saveState(goalReached = true)
            }
                stepCounter.startListening()
                stepCounter.setOnSensorValueChangedListener { steps ->
                    saveState(
                        sensorSteps = steps[0].toInt(),
                        stepsLeft = stepsState.stepsGoal - (steps[0].toInt() - stepsState.systemStartingSteps),
                        percentDone = ((stepsState.stepsGoal - stepsState.stepsLeft)*100) / stepsState.stepsGoal
                    )
                    sharedPreferencesUseCase.saveLeftStepsUseCase(stepsState.stepsLeft)
                    if (stepsState.stepsLeft <= 0) {
                        saveState(goalReached = true)
                    }
                }
        }
    }
    private fun saveState(
        stepsGoalInput: String? = null,
        stepsGoal: Int? = null,
        sensorSteps: Int? = null,
        systemStartingSteps: Int? = null,
        stepsGoalCreated: Boolean? = null,
        stepsLeft: Int? = null,
        percentDone: Int? = null,
        goalReached: Boolean? = null
    ) {
        stepsState = stepsState.copy(
            stepsGoalInput = stepsGoalInput ?: stepsState.stepsGoalInput,
            stepsGoal = stepsGoal ?: stepsState.stepsGoal,
            sensorSteps = sensorSteps ?: stepsState.sensorSteps,
            systemStartingSteps = systemStartingSteps ?: stepsState.systemStartingSteps,
            stepsGoalCreated = stepsGoalCreated ?: stepsState.stepsGoalCreated,
            stepsLeft = stepsLeft ?: stepsState.stepsLeft,
            percentDone = percentDone ?: stepsState.percentDone,
            goalReached = goalReached ?: stepsState.goalReached
        )
    }


    private fun addFinishedExercise(){
        viewModelScope.launch {
            val newExercise = Exercise().apply {
                date = getCurrentDateString()
                this.steps = stepsState.stepsGoal
            }
            databaseRepository.addExercise(newExercise)
        }
    }


    override fun onCleared() {
        sharedPreferencesUseCase.saveLeftStepsUseCase(stepsState.stepsLeft)
        super.onCleared()
        stepCounter.stopListening()
    }
}

package com.example.stepogotchi_main.presentation.screens.stepperScreen
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stepogotchi_main.data.model.Exercise
import com.example.stepogotchi_main.data.util.getCurrentDateString
import com.example.stepogotchi_main.domain.repository.DatabaseRepository
import com.example.stepogotchi_main.domain.use_case.preferencesUseCase.GetSharedPreferencesUseCase
import com.example.stepogotchi_main.presentation.state.StepperState
import com.example.stepogotchi_main.sensor.MeasurableSensor
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _test = MutableStateFlow("2")
    val test = _test.asStateFlow()

    private val _test2 = MutableStateFlow("test 2")
    val test2 = _test2.asStateFlow()


    init {
       countingStepsOnBeginIfExerciseExist()
    }

    fun finishStepGoal(){
        viewModelScope.launch {

            val newExercise = Exercise().apply {
                date = getCurrentDateString()
                this.steps = stepsState.stepsGoal
            }

            databaseRepository.addExercise(newExercise)

            stepsState = StepperState()

            sharedPreferencesUseCase.resetSharedPreferences()
        }
    }

    fun setStepsInputChange(steps: String){
        stepsState = stepsState.copy(stepsGoalInput = steps)
    }

    fun createStepsGoal(){
        sharedPreferencesUseCase.saveSteps(stepsState.stepsGoalInput.toInt())
        stepCounter.startListening()
        var systemStepsCollected = false
        stepCounter.setOnSensorValueChangedListener { steps ->
            Log.d("dupsko", "countingStepsOnBeginIfExerciseExist:11 ")
            if (!systemStepsCollected){
                Log.d("dupsko", "countingStepsOnBeginIfExerciseExist:11 ${steps[0].toInt()}")
                sharedPreferencesUseCase.saveSystemSteps(steps[0].toInt())
                stepsState = stepsState.copy(
                    systemStartingSteps = steps[0].toInt()
                )
                systemStepsCollected = true
            }
            Log.d("dupsko", "countingStepsOnBeginIfExerciseExist:12 ${stepsState.stepsGoal} /${steps[0].toInt()} /${stepsState.systemStartingSteps}")

            stepsState = stepsState.copy(
                sensorSteps = steps[0].toInt(),
                stepsLeft = stepsState.stepsGoal - (steps[0].toInt() - stepsState.systemStartingSteps),
                percentDone = ((stepsState.stepsGoal - stepsState.stepsLeft)*100) / stepsState.stepsGoal
            )

            sharedPreferencesUseCase.saveLeftStepsUseCase(stepsState.stepsLeft)
        }
        stepsState = stepsState.copy(
            stepsGoal = stepsState.stepsGoalInput.toInt(),
            stepsGoalCreated = true,
        )

        stepsState = stepsState.copy(stepsLeft = stepsState.stepsGoal - stepsState.systemStartingSteps)

    }
    private fun countingStepsOnBeginIfExerciseExist() {
        Log.d("dupsko", "countingStepsOnBeginIfExerciseExist:1 ")
        if (sharedPreferencesUseCase.getSteps() != 0) {

            Log.d(
                "dupsko",
                "countingStepsOnBeginIfExerciseExist:1232 ${sharedPreferencesUseCase.getLeftStepsUseCase()}"
            )
            stepsState = stepsState.copy(
                systemStartingSteps = sharedPreferencesUseCase.getSystemSteps(),
                stepsGoal = sharedPreferencesUseCase.getSteps(),
                sensorSteps = 0,
                stepsGoalCreated = true,
                stepsLeft = sharedPreferencesUseCase.getLeftStepsUseCase(),
            )
            stepsState = stepsState.copy(
                percentDone = ((stepsState.stepsGoal - stepsState.stepsLeft)*100) / stepsState.stepsGoal
            )
            if (stepsState.stepsLeft <= 0) {
                stepsState = stepsState.copy(
                    goalReached = true
                )
            }
                stepCounter.startListening()
                stepCounter.setOnSensorValueChangedListener { steps ->
                    Log.d("dupsko", "countingStepsOnBeginIfExerciseExist:2 ")

                    stepsState = stepsState.copy(
                        sensorSteps = steps[0].toInt(),
                        stepsLeft = stepsState.stepsGoal - (steps[0].toInt() - stepsState.systemStartingSteps),
                        percentDone = ((stepsState.stepsGoal - stepsState.stepsLeft)*100) / stepsState.stepsGoal
                    )
                    Log.d("dupsko", "countingStepsOnBeginIfExerciseExist:2 percent ${stepsState.percentDone}/ ${stepsState.stepsGoal}/ ${stepsState.stepsLeft}")

                    sharedPreferencesUseCase.saveLeftStepsUseCase(stepsState.stepsLeft)
                    if (stepsState.stepsLeft <= 0) {
                        stepsState = stepsState.copy(
                            goalReached = true
                        )
                        Log.d("dupsko", "countingStepsOnBeginIfExerciseExist:3 ")
                    }
                }
            }
        }


    override fun onCleared() {
        sharedPreferencesUseCase.saveLeftStepsUseCase(stepsState.stepsLeft)
        Log.d("dupsko", "countingStepsOnBeginIfExerciseExist:1232 ${sharedPreferencesUseCase.getLeftStepsUseCase()}  tutaj")
        super.onCleared()
        stepCounter.stopListening()
    }
}

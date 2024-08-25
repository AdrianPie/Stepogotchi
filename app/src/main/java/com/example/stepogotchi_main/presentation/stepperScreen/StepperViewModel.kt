package com.example.stepogotchi_main.presentation.stepperScreen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.stepogotchi_main.domain.use_case.GetStepsUseCase
import com.example.stepogotchi_main.domain.use_case.SaveStepsUseCase
import com.example.stepogotchi_main.presentation.state.StepperState
import com.example.stepogotchi_main.sensor.MeasurableSensor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class StepperViewModel@Inject constructor(
    private val stepCounter: MeasurableSensor,
    private val getStepsUseCase: GetStepsUseCase,
    private val saveStepsUseCase: SaveStepsUseCase

): ViewModel() {

    var stepsState by mutableStateOf(StepperState())
        private set

    private val _steps = MutableStateFlow(0)
    val steps = _steps.asStateFlow()

    private val _percent = MutableStateFlow(0)
    val percent = _percent.asStateFlow()


    fun addStep(){
        _steps.value += 1
        _percent.value = ((_steps.value.toFloat() / 200) * 100).toInt()
    }

    fun setStepsInputChange(steps: String){
        stepsState = stepsState.copy(stepsGoalInput = steps)
    }
    fun createStepsGoal(){
        saveStepsUseCase.invoke(stepsState.stepsGoalInput.toInt())
        stepsState = stepsState.copy(stepsGoal = stepsState.stepsGoalInput.toInt())
    }
    init {
        stepsState = stepsState.copy(stepsGoal = getStepsUseCase.invoke())

        stepCounter.startListening()
        stepCounter.setOnSensorValueChangedListener { values ->
            val steps = values[0]
            _steps.value = steps.toInt()
            _percent.value = ((_steps.value.toFloat() / 200) * 100).toInt()
        }
    }

}
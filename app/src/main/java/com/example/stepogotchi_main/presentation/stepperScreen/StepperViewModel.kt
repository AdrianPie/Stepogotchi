package com.example.stepogotchi_main.presentation.stepperScreen
import androidx.lifecycle.ViewModel
import com.example.stepogotchi_main.sensor.MeasurableSensor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class StepperViewModel@Inject constructor(
    private val stepCounter: MeasurableSensor

): ViewModel() {

    private val _steps = MutableStateFlow(0)
    val steps = _steps.asStateFlow()

    private val _percent = MutableStateFlow(0)
    val percent = _percent.asStateFlow()


    fun addStep(){
        _steps.value += 1
        _percent.value = ((_steps.value.toFloat() / 200) * 100).toInt()
    }
    init {
        stepCounter.startListening()
        stepCounter.setOnSensorValueChangedListener { values ->
            val steps = values[0]
            _steps.value = steps.toInt()
            _percent.value = ((_steps.value.toFloat() / 200) * 100).toInt()
        }
    }

}
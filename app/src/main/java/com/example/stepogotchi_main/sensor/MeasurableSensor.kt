package com.example.stepogotchi_main.sensor

import android.util.Log

abstract class MeasurableSensor(
    protected val sensorType: Int
) {

    protected var onSensorValueChanged: ((List<Float>) -> Unit)? = null
    abstract val doesSensorExist: Boolean

    abstract fun startListening()
    abstract fun stopListening()

    fun setOnSensorValueChangedListener(listener: (List<Float>) -> Unit) {
        Log.d("dupsko", ":3 ")
        onSensorValueChanged = listener
    }
}
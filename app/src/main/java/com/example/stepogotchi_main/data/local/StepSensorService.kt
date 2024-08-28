package com.example.stepogotchi_main.data.local

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.stepogotchi_main.R
import com.example.stepogotchi_main.sensor.MeasurableSensor
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StepSensorService(): Service() {

    private var stepsLeft = 1

    @Inject
    lateinit var stepCounterSensor: MeasurableSensor

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            Actions.START.name -> start(intent.getIntExtra("STEPS_GOAL",0))
            Actions.STOP.name -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start(stepsGoal: Int){
        startSensor(stepsGoal)
    }

    enum class Actions{
        START,
        STOP
    }
    private fun startSensor(stepsGoal: Int){
        var startingSteps = 0
        var test = 0
        var startingStepsCollected = false
        stepCounterSensor.startListening()

            stepCounterSensor.setOnSensorValueChangedListener { stepsFromDevice ->
                if (!startingStepsCollected){
                    startingSteps = stepsFromDevice[0].toInt()
                    startingStepsCollected = true
                }
                test++
                stepsLeft = stepsGoal - (stepsFromDevice[0].toInt() - startingSteps)
                Log.d("dupsko", "startSensor: DZIALA ")
                val notification = NotificationCompat.Builder(this, "stepper_channel")
                    .setSmallIcon(R.drawable.ape_logo)
                    .setContentTitle("Step quest is active")
                    .setContentText("Steps left: $stepsLeft/$stepsGoal /${stepsFromDevice[0].toInt()} / t:$test")
                    .build()
                startForeground(1,notification)
            }
    }
}
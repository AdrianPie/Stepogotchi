package com.example.stepogotchi_main.presentation.state

data class StepperState(
    val stepsGoalInput: String = "",
    val stepsGoal: Int = 0,
    val sensorSteps: Int = 0,
    val systemStartingSteps: Int = 0,
    val stepsGoalCreated: Boolean = false,
    val stepsLeft: Int = 0,
    val goalReached: Boolean = false
)

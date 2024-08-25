package com.example.stepogotchi_main.presentation.stepperScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stepogotchi_main.presentation.components.CustomCircularProgressIndicator
import com.example.stepogotchi_main.ui.theme.lightGray
import com.example.stepogotchi_main.ui.theme.orange

@Composable
fun StepperScreen(
    modifier: Modifier = Modifier,
    viewModel: StepperViewModel = hiltViewModel(),
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        val steps = viewModel.steps.collectAsState()
        val percentDone by viewModel.percent.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Stepper")
            TextField(
                value = viewModel.stepsState.stepsGoalInput ,
                onValueChange = viewModel::setStepsInputChange
            )

            Text(text = steps.value.toString())

            Spacer(modifier = Modifier.height(60.dp))

            Text(text = "GOAL: ${viewModel.stepsState.stepsGoal} STEPS")
            
            Button(onClick = { viewModel.addStep() }) {
                Text(text = "test")
            }
            Button(onClick =   viewModel::createStepsGoal) {
                Text(text = "create Goal")
            }

            CustomCircularProgressIndicator(
                initialValue = percentDone,
                primaryColor = orange,
                secondaryColor = lightGray,
                circleRadius = 300f
            ) {

            }
        }

    }

    }


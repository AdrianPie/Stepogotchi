package com.example.stepogotchi_main.presentation.stepperScreen

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stepogotchi_main.data.local.StepSensorService
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


        val percentDone by viewModel.percent.collectAsState()
        val test by viewModel.test.collectAsState()
        val test2 by viewModel.test2.collectAsState()
        val context = LocalContext.current



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

            Text(text = "Steps left : ${viewModel.stepsState.stepsLeft.toString()}")

            Spacer(modifier = Modifier.height(60.dp))

            Text(text = "GOAL: ${viewModel.stepsState.stepsGoal} STEPS")
            

            Button(onClick = {
                Intent(context, StepSensorService::class.java).also {
                    it.action = StepSensorService.Actions.START.name
                    it.putExtra("STEPS_GOAL", viewModel.stepsState.stepsGoalInput.toInt())
                    context.startService(it)
                }
            }) {
                Text(text = "create Goal")
            }
            Button(onClick =   viewModel::testResetShared) {
                Text(text = "clean sharedpreferences")
            }
            Button(onClick =   viewModel::addTestStep) {
                Text(text = "test step")
            }
            if(viewModel.stepsState.stepsLeft <= 0 && viewModel.stepsState.stepsGoalCreated){
                Button(onClick = viewModel::finishStepGoal) {
                    Text(text = "Claim reward")
                }
            }
            Text(text = "test: $test")
            Text(text = "test2: $test2")

            CustomCircularProgressIndicator(
                progressValue = percentDone,
                primaryColor = orange,
                secondaryColor = lightGray,
                circleRadius = 300f
            ) {

            }
        }

    }

    }


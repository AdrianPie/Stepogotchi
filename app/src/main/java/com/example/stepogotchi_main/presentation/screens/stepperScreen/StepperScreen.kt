package com.example.stepogotchi_main.presentation.screens.stepperScreen

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SportsTennis
import androidx.compose.material3.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stepogotchi_main.R
import com.example.stepogotchi_main.data.local.StepSensorService
import com.example.stepogotchi_main.presentation.components.CustomCircularProgressIndicator
import com.example.stepogotchi_main.presentation.components.TextEntryModule
import com.example.stepogotchi_main.ui.theme.gray
import com.example.stepogotchi_main.ui.theme.lightGray
import com.example.stepogotchi_main.ui.theme.orange
import com.example.stepogotchi_main.ui.theme.white

@Composable
fun StepperScreen(
    viewModel: StepperViewModel = hiltViewModel(),
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Goal : ${viewModel.stepsState .stepsGoal}",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Steps left : ${viewModel.stepsState.stepsLeft}",
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraLight
            )

            CustomCircularProgressIndicator(
                progressValue = viewModel.stepsState.percentDone,
                primaryColor = orange,
                secondaryColor = lightGray,
                circleRadius = 360f
            ) {

            }

            TextEntryModule(
                modifier = Modifier.fillMaxWidth(0.5f),
                keyboardType = KeyboardType.Number,
                description = "",
                hint = "Enter steps goal",
                leadingIcon = Icons.Default.SportsTennis,
                textValue = viewModel.stepsState.stepsGoalInput,
                textColor = gray,
                cursorColor = orange,
                onValueChanged = viewModel::setStepsInputChange,
                onTrailingIconClick = null)

            Spacer(modifier = Modifier.height(30.dp))

            if (!viewModel.stepsState.stepsGoalCreated){
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                    ,
                    colors = ButtonColors(
                        containerColor = orange,
                        disabledContentColor = orange,
                        contentColor = white,
                        disabledContainerColor = orange
                    ),
                    onClick = {
                        viewModel.createStepsGoal()
//                Intent(context, StepSensorService::class.java).also {
//                    it.action = StepSensorService.Actions.START.name
//                    it.putExtra("STEPS_GOAL", viewModel.stepsState.stepsGoalInput.toInt())
//                    context.startService(it)
//                }
                    }) {
                    Text(text = "create Goal")
                }
            }else {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                    ,
                    colors = ButtonColors(
                        containerColor = orange,
                        disabledContentColor = white,
                        contentColor = white,
                        disabledContainerColor = gray
                    ),
                    enabled = viewModel.stepsState.goalReached,
                    onClick = viewModel::finishStepGoal) {
                    Text(text = "Claim reward")
                }
            }
        }
    }
}


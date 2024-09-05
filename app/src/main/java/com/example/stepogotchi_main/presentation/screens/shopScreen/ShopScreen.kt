package com.example.stepogotchi_main.presentation.screens.shopScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stepogotchi_main.data.model.Exercise

@Composable
fun ShopScreen(
    modifier: Modifier = Modifier,
    viewModel: ShopScreenViewModel = hiltViewModel()
) {
    val monster by viewModel.monster.collectAsState()

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
            Text(
                text = "Shop")

            LazyColumn() {
                items(monster.exercises){  exercise ->
                    ExerciseItem(exercise = exercise)
                }
            }

        }

    }

    }
@Composable
fun ExerciseItem(exercise: Exercise) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Date: ${exercise.date}")
        Text(text = "Steps: ${exercise.steps}")
    }
}


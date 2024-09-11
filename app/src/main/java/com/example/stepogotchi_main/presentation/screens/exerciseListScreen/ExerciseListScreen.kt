package com.example.stepogotchi_main.presentation.screens.exerciseListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stepogotchi_main.data.model.Exercise
import com.example.stepogotchi_main.ui.theme.gray
import com.example.stepogotchi_main.ui.theme.orange
import com.example.stepogotchi_main.ui.theme.white

@Composable
fun ExerciseListScreen(
    viewModel: ExerciseListScreenViewModel = hiltViewModel()
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
            verticalArrangement = Arrangement.Top
        ) {

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
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = orange)
            .padding(4.dp)

    ) {
        Row(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(white)
                .padding(6.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = exercise.date,
                style = MaterialTheme.typography.bodyMedium,
            )
            Row {
                Icon(imageVector = Icons.Filled.DirectionsRun, contentDescription = "Icon of running")
                Text(text = exercise.steps.toString(), style = MaterialTheme.typography.bodyMedium)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseItemPreview(){
    val previewExercise = Exercise().apply {
        date = "2023-09-05"
        steps = 12000
    }
    ExerciseItem(exercise = previewExercise)
}

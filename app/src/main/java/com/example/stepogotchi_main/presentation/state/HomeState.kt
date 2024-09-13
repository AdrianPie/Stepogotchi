package com.example.stepogotchi_main.presentation.state

import androidx.annotation.DrawableRes
import com.example.stepogotchi_main.R
import com.example.stepogotchi_main.data.model.Monster

data class HomeState(
    val monster: Monster = Monster(),
    val progress: Float = 0.0f,
    val totalSteps: Int = 0,
    val level: Int = 0,
    @DrawableRes
    val monsterPicture: Int = R.drawable.monster_level_one
)
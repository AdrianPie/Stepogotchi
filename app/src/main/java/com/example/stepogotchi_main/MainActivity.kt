package com.example.stepogotchi_main

import android.Manifest
import android.animation.ObjectAnimator
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.stepogotchi_main.presentation.components.BottomNavigationBar

import com.example.stepogotchi_main.ui.theme.Stepogotchi_mainTheme
import com.example.stepogotchi_main.data.util.Navigation
import com.example.stepogotchi_main.data.util.Screen
import dagger.hilt.android.AndroidEntryPoint




@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val mainViewModel: MainViewModel by viewModels()
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSplashScreen()
       

            setContent {
                Stepogotchi_mainTheme {
                    val stepCounterPermissionResultLauncher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.RequestPermission(),
                            onResult = { isGranted ->
                               mainViewModel.onPermissionResult(
                                   permission = Manifest.permission.ACTIVITY_RECOGNITION,
                                   isGranted = isGranted
                               )
                            }
                    )
                    val startDest by mainViewModel.startDest.collectAsState()

                    BottomNavigationBar(
                        startDest = startDest,
                        permission = {
                            stepCounterPermissionResultLauncher.launch(
                            Manifest.permission.ACTIVITY_RECOGNITION)
                        }
                    )
                }
            }
        }

    private fun setupSplashScreen() {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !mainViewModel.ready.value
            }
            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.8f,
                    0.0f
                )
                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = 1000L
                zoomX.doOnEnd { screen.remove() }

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.8f,
                    0.0f
                )
                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = 1000L
                zoomY.doOnEnd { screen.remove() }

                zoomY.start()
                zoomX.start()
            }
        }
    }




}






package com.example.stepogotchi_main.presentation.components

import android.hardware.SensorEventListener
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.stepogotchi_main.MainViewModel
import com.example.stepogotchi_main.presentation.homescreen.HomeScreen
import com.example.stepogotchi_main.presentation.loginScreen.LoginScreen
import com.example.stepogotchi_main.presentation.registerScreen.RegisterScreen
import com.example.stepogotchi_main.presentation.shopScreen.ShopScreen
import com.example.stepogotchi_main.presentation.stepperScreen.StepperScreen
import com.example.stepogotchi_main.data.util.Screen
import com.example.stepogotchi_main.ui.theme.PurpleGrey40
import com.example.stepogotchi_main.ui.theme.gray
import com.example.stepogotchi_main.ui.theme.lightGray
import com.example.stepogotchi_main.ui.theme.orange
import com.example.stepogotchi_main.ui.theme.white
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.utils.noRippleClickable

@Composable
fun BottomNavigationBar(
    viewModel: MainViewModel = hiltViewModel(),
    startDest: String,
    permission:()->Unit
) {
    val navController = rememberNavController()

    val showBottomNavBar by viewModel.logged.collectAsState()

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    Scaffold(
        modifier = Modifier.padding(all = 12.dp),
        bottomBar = {
            if (showBottomNavBar) {
                AnimatedNavigationBar(
                    selectedIndex = selectedItemIndex,
                    cornerRadius = shapeCornerRadius(cornerRadius = 34.dp),
                    ballAnimation = Parabolic(tween(300)),
                    indentAnimation = Height(tween(300)),
                    barColor = orange,
                    ballColor = orange
                ) {

                    BottomNavigationItem().bottomNavigationItems().forEachIndexed() { index, item ->

                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .noRippleClickable {
                                    selectedItemIndex = index
                                    navController.navigate(item.title)
                                                   },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(26.dp),
                                imageVector = item.selectedItem,
                                contentDescription = "Bottom Bar Icon",
                                tint = if (selectedItemIndex == index) lightGray
                                else white
                            )

                        }

                    }
                }
            }

        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = startDest,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            composable(Screen.Login.name){
                LoginScreen(
                    onLoginSuccessNavigation = {
                        navController.navigate(Screen.Home.name){
                            popUpTo(0)
                        }
                    },
                    onNavigateToRegisterScreen = {
                        navController.navigate(Screen.Register.name){
                            popUpTo(0)
                        }
                    },
                    permission = permission
                )
            }
            composable(Screen.Register.name){
                RegisterScreen(
                    onRegisterSuccessNavigation = {
                        navController.navigate(Screen.Home.name){
                            popUpTo(0)
                        }
                    },
                    onNavigateToLoginScreen = {
                        navController.navigate(Screen.Login.name){
                            popUpTo(0)
                        }
                    },
                    permissionRequest = permission
                )
            }
            composable(Screen.Home.name) {
                HomeScreen(
                    onLogoutClick = {
                        navController.navigate(Screen.Login.name) {
                            popUpTo(0)
                        }
                    }
                )
            }
            composable(Screen.Shop.name) {
                ShopScreen(
                )
            }
            composable(Screen.Stepper.name) {
                StepperScreen(
                )

            }

        }
    }
}


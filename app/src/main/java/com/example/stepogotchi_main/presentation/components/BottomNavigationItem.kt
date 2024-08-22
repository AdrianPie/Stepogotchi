package com.example.stepogotchi_main.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.stepogotchi_main.data.util.Screen

data class BottomNavigationItem(
    val title: String = "",
    val selectedItem: ImageVector = Icons.Filled.Home,
    val unselectedItem: ImageVector = Icons.Outlined.Home
) {
    fun bottomNavigationItems(): List<BottomNavigationItem>{
        return listOf(
            BottomNavigationItem(
                title = Screen.Home.name,
                selectedItem = Icons.Filled.AccountCircle,
                unselectedItem = Icons.Outlined.AccountCircle
            ),
           BottomNavigationItem(
                title = Screen.Stepper.name,
                selectedItem = Icons.Filled.Explore,
                unselectedItem = Icons.Outlined.Explore
            ),
            BottomNavigationItem(
                title = Screen.Shop.name,
                selectedItem = Icons.Filled.ShoppingBasket,
                unselectedItem = Icons.Outlined.ShoppingBasket
            )
        )
    }
}

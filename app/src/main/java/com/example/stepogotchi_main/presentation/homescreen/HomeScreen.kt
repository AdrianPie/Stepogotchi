package com.example.stepogotchi_main.presentation.homescreen

import android.util.Log
import android.widget.ProgressBar
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.stepogotchi_main.R
import com.example.stepogotchi_main.data.model.Monster
import com.example.stepogotchi_main.data.util.Screen
import com.example.stepogotchi_main.presentation.components.StripedProgressIndicator
import com.example.stepogotchi_main.ui.theme.Pink80
import com.example.stepogotchi_main.ui.theme.Pink80l
import com.example.stepogotchi_main.ui.theme.blue
import com.example.stepogotchi_main.ui.theme.bluel
import com.example.stepogotchi_main.ui.theme.green
import com.example.stepogotchi_main.ui.theme.lightGray
import com.example.stepogotchi_main.ui.theme.orange
import com.example.stepogotchi_main.ui.theme.white
import com.example.stepogotchi_main.ui.theme.yellow
import com.example.stepogotchi_main.ui.theme.yellowl
import com.google.firebase.auth.FirebaseUser




@Composable
fun HomeScreen(
 viewModel: HomeScreenViewModel = hiltViewModel(),
 onLogoutClick:()-> Unit
) {

    val monster by viewModel.monster.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Text(text = "Name")
            Spacer(modifier = Modifier.height(30.dp))

            Text(text = "Level")

            StripedProgressIndicator(
                progress = 0.12f,
                stripeColor = yellow,
                stripeColorSecondary = yellowl,
                backgroundColor = lightGray
            )

            Spacer(modifier = Modifier.height(30.dp))

            Box(
                modifier = Modifier
                    .size(250.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(lightGray)
                    .border(BorderStroke(4.dp, orange), shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(200.dp),
                    painter = painterResource(id = monster.monsterPicture),
                    contentDescription = "Monster Image"
                )
            }
            Spacer(modifier = Modifier.height(40.dp))

            Text(text = "Sleep")

            Spacer(modifier = Modifier.height(16.dp))

            StripedProgressIndicator(
                    progress = 0.12f,
                    stripeColor = blue,
                    stripeColorSecondary = bluel,
                    backgroundColor = lightGray
                )
            Spacer(modifier = Modifier.height(40.dp))

            Text(text = "Hunger")

            Spacer(modifier = Modifier.height(16.dp))

            StripedProgressIndicator(
                progress = 0.12f,
                stripeColor = Pink80,
                stripeColorSecondary = Pink80l,
                backgroundColor = lightGray
            )



            Button(onClick = {
                onLogoutClick()
                viewModel.logout()}
            ) {
                Text(text = "logout")
            }

            }


        }
    }



package com.example.stepogotchi_main.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stepogotchi_main.ui.theme.orange
import com.example.stepogotchi_main.ui.theme.white

@Composable
fun AuthButton(
    modifier: Modifier = Modifier,
    text: String,
    containerColorEnabled: Color,
    contentColorUnenabled: Color,
    contentColor: Color,
    enabled:Boolean = true,
    onButtonClick: () -> Unit,
    isLoading: Boolean,
) {
    Button(
        modifier = modifier,
        onClick = {
            onButtonClick()
        },
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColorEnabled,
            contentColor = contentColor,
            disabledContentColor = contentColor,
            disabledContainerColor = contentColorUnenabled



        ),
        enabled = enabled
    ){
        if(isLoading){
            CircularProgressIndicator(
                color = contentColor,
                modifier = Modifier.size(20.dp)
            )
        }else{
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


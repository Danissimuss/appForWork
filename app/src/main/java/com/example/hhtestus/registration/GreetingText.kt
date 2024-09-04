package com.example.hhtestus.registration

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hhtestus.ui.theme.Standart

@Composable
fun GreetingTexts() {
    Text(
        text = "Вход в личный кабинет",
        color = Color.White,
        fontSize = 20.sp,
        style = Standart,
        modifier = Modifier.padding(bottom = 144.dp, top = 48.dp, start = 16.dp, end = 16.dp)
    )
}
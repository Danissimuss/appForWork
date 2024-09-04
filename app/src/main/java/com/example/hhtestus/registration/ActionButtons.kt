package com.example.hhtestus.registration

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hhtestus.ui.theme.Blue
import com.example.hhtestus.ui.theme.DarkBlue
import com.example.hhtestus.ui.theme.Standart

@Composable
fun ActionButtons(email: String, onSubmit: (String)-> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Button(onClick = { onSubmit(email)},
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth()
                .weight(1f),
            colors = ButtonDefaults.buttonColors(
                    containerColor = if (email.isNotEmpty()) Blue else DarkBlue,// Цвет фона кнопки
                    contentColor = Color.White
            )

        ) {
            Text(text = "Продолжить",
                style = Standart)
        }

        Spacer(modifier = Modifier.width(24.dp))

        Text(
            text = "Войти с паролем",
            style = Standart,
            color = Blue,
            modifier = Modifier
                .clickable(onClick = { })
                .padding(top = 13.dp),
            fontSize = 16.sp
        )
    }
}
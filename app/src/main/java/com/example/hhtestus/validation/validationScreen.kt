package com.example.hhtestus.validation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hhtestus.downPanel.ApiSide.RetrofitClient
import com.example.hhtestus.Navigation.SharedViewModel
import com.example.hhtestus.Navigation.responceAnsw
import com.example.hhtestus.ui.theme.Blue
import com.example.hhtestus.ui.theme.Standart
import com.example.hhtestus.ui.theme.White01

@Preview
@Composable
fun validationScreen(email: String?, navController:NavController, viewModel: SharedViewModel) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Отправили код на ${email}",
            style = Standart,
            color = White01,
            modifier = Modifier
                .padding(start = 15.dp, top = 162.dp, end = 15.dp)
                .width(IntrinsicSize.Max),
            fontSize = 24.sp,
            maxLines = 1
        )
        Text(
            text = "Напишите его, чтобы подтвердить, что это вы, а не кто-то другой входит в личный кабинет",
            style = Standart,
            color = White01,
            modifier = Modifier
                .padding(start = 15.dp, top = 16.dp, end = 15.dp)
                .fillMaxWidth(),
            fontSize = 20.sp
        )

        codeDetector(viewModel, navController)
    }
}
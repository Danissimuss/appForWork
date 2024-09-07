package com.example.hhtestus.downPanel.plug

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hhtestus.Navigation.SharedViewModel
import com.example.hhtestus.downPanel.downPanel
import com.example.hhtestus.ui.theme.Standart

@Composable
fun bladerunnerScreen(navController:NavController, viewModel:SharedViewModel){

    Column(modifier = Modifier.fillMaxSize()) {

        Text(text = "Let's Drive...", style = Standart, fontSize = 40.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 350.dp, bottom = 310.dp))

        Spacer(modifier = Modifier.weight(1f))

        downPanel(navController = navController, viewModel = viewModel)
    }

}
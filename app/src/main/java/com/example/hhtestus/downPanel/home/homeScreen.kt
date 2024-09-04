package com.example.hhtestus.downPanel.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hhtestus.Navigation.SharedViewModel
import com.example.hhtestus.downPanel.downPanel
import com.example.hhtestus.ui.theme.Standart

@Composable
fun homeScreen(viewModel: SharedViewModel, navController: NavController){

    val mainContent = mainContent()

    Column {

        Search()

        lazyRow(viewModel = viewModel)

        Text(text = " Вакансии для вас", style = Standart, fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp, start = 16.dp))

        mainContent.mainView(viewModel = viewModel)

    }

}
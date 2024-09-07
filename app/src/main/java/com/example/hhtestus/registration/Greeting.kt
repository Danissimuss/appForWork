package com.example.hhtestus.registration

import JobSearchCard
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.hhtestus.Navigation.SharedViewModel
import com.example.hhtestus.downPanel.downPanel

@Composable
fun Greeting(navController: NavController, viewModel: SharedViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GreetingTexts()
        EmailInputField(navController)
        JobSearchCard()

        Spacer(modifier = Modifier.weight(1f))

        downPanel(navController, viewModel = viewModel )
    }
}
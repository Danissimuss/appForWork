package com.example.hhtestus.registration

import JobSearchCard
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.hhtestus.downPanel.downPanel

@Composable
fun Greeting(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GreetingTexts()
        EmailInputField(navController)
        JobSearchCard()
        downPanel(navController)
    }
}
package com.example.hhtestus.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun HHtestusTheme(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Black01
    ) {
        content()
    }
}
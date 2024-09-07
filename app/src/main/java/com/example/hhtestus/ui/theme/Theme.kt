package com.example.hhtestus.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun HHtestusTheme(content: @Composable () -> Unit) {

    Surface(
        modifier = Modifier.fillMaxSize()
            .padding(top = 20.dp),
        color = Black01
    ) {
        content()
    }
}
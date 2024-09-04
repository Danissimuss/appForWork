package com.example.hhtestus.registration

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hhtestus.ui.theme.DarkGrey01

@Composable
fun CardContainer(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp, start = 16.dp, bottom = 16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(containerColor = DarkGrey01)
    ) {
        content()
    }
}
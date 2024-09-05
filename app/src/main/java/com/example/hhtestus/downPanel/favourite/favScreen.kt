package com.example.hhtestus.downPanel.favourite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hhtestus.Navigation.SharedViewModel
import com.example.hhtestus.downPanel.ApiSide.Vacancy

@Composable

fun favScreen(viewModel: SharedViewModel){

    val favVacancies by viewModel.getFavVac().observeAsState(emptyList())

    // Отображаем данные в колонне
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(favVacancies) { vacancy ->
            VacancyItem(vacancy)
        }
    }
}

@Composable
fun VacancyItem(vacancy: Vacancy) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = vacancy.title
            )
            Text(
                text = "Salary: ${vacancy.salary.short}"
            )
            Text(
                text = "Location: ${vacancy.address.town}"
            )
            Text(
                text = "Experience: ${vacancy.experience.previewText}"
            )
        }
    }
}
package com.example.hhtestus.downPanel.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hhtestus.Navigation.SharedViewModel
import com.example.hhtestus.downPanel.ApiSide.Vacancy
import com.example.hhtestus.downPanel.downPanel
import com.example.hhtestus.ui.theme.Black01
import com.example.hhtestus.ui.theme.DarkGrey02
import com.example.hhtestus.ui.theme.Green
import com.example.hhtestus.ui.theme.LightGrey02
import com.example.hhtestus.ui.theme.Standart

class mainContent() {

    @Composable
    fun mainView(viewModel: SharedViewModel) {

        val vacancies by viewModel.vacancies.observeAsState(emptyList())

        Scaffold(
            bottomBar = { downPanel()},
            containerColor = Black01
        ) { innerPadding ->

            LazyColumn(
                modifier = Modifier
                    .padding(start = 8.dp, end = 16.dp, bottom = innerPadding.calculateBottomPadding())
                    .fillMaxWidth(),
            ) {

                val vacToShow = if (vacancies.size >= 3) {
                    vacancies.take(3)
                } else {
                    vacancies
                }

                items(vacToShow) { vac ->
                    vacList(vacancy = vac)
                }
            }
        }

    }

    @Composable
    fun vacList(vacancy: Vacancy) {

        val watchers = vacancy.lookingNumber

        val annotatedText = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Green)) {
                val ending = when {
                    watchers % 100 in 11..14 -> "человек"
                    watchers % 10 in 2..4 -> "человека"
                    else -> "человек"
                }
                append("Сейчас просматривает $watchers $ending")
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, start = 8.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(DarkGrey02)
        ) {

            Column(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .fillMaxWidth(),
            ) {


                Text(
                    text = annotatedText,
                    style = Standart,
                    fontSize = 12.sp
                )
                Text(
                    text = vacancy.title,
                    modifier = Modifier.padding(top = 10.dp, end = 16.dp),
                    style = Standart
                )
                Text(
                    text = vacancy.address.town,
                    modifier = Modifier.padding(top = 10.dp),
                    style = Standart,
                    fontSize = 14.sp
                )
                Text(
                    text = vacancy.company,
                    modifier = Modifier.padding(top = 4.dp),
                    style = Standart,
                    fontSize = 14.sp
                )
                Text(
                    text = vacancy.experience.previewText,
                    modifier = Modifier.padding(top = 10.dp),
                    style = Standart,
                    fontSize = 14.sp
                )
                Text(
                    text = "Опубликовано: " + vacancy.publishedDate,
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp ),
                    style = Standart,
                    color = LightGrey02,
                    fontSize = 14.sp
                )

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Green,  // Цвет фона кнопки
                        contentColor = Color.White)) {

                    Text(text = "Откликнуться", style = Standart)

                }

            }
        }

    }
}
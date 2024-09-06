package com.example.hhtestus.downPanel.favourite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hhtestus.Navigation.SharedViewModel
import com.example.hhtestus.downPanel.downPanel
import com.example.hhtestus.downPanel.home.mainContent
import com.example.hhtestus.downPanel.home.textForSearch
import com.example.hhtestus.ui.theme.Black01
import com.example.hhtestus.ui.theme.DarkGrey02
import com.example.hhtestus.ui.theme.Green
import com.example.hhtestus.ui.theme.LightGrey02
import com.example.hhtestus.ui.theme.Standart
import com.example.hhtestus.ui.theme.White01

@Composable
fun favScreen(viewModel: SharedViewModel, navController: NavController) {

    val favVacancies by viewModel.getFavVac().observeAsState(emptyList())

    val mainContent = mainContent()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp)
    ) {
        Text(
            text = "Избранное",
            color = White01,
            fontSize = 24.sp,
            style = Standart,
            modifier = Modifier.padding(bottom = 24.dp, start = 16.dp)
        )

        Text(
            text = "${mainContent.formatVac(favVacancies.size)}",
            modifier = Modifier.padding(bottom = 16.dp, start = 16.dp),
            style = Standart,
            color = LightGrey02
        )

        Scaffold(
            bottomBar = { downPanel(navController) },
            containerColor = Black01
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        end = 16.dp,
                        bottom = innerPadding.calculateBottomPadding()
                    )
                    .fillMaxWidth(),
            ) {
                items(favVacancies) { vacancy ->
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
                            textForSearch(
                                viewModel = viewModel,
                                vacancy = vacancy
                            )

                            Button(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Green,  // Цвет фона кнопки
                                    contentColor = Color.White
                                )
                            ) {

                                Text(text = "Откликнуться", style = Standart)

                            }
                        }
                    }
                }
            }
        }
    }
}
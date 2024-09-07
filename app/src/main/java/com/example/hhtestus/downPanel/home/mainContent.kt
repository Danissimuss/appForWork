package com.example.hhtestus.downPanel.home

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.lifecycle.viewModelScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hhtestus.Navigation.SharedViewModel
import com.example.hhtestus.Navigation.responceAnsw
import com.example.hhtestus.downPanel.ApiSide.ApiService
import com.example.hhtestus.downPanel.ApiSide.RetrofitClient
import com.example.hhtestus.downPanel.ApiSide.Vacancy
import com.example.hhtestus.downPanel.downPanel
import com.example.hhtestus.downPanel.response.responseApi
import com.example.hhtestus.ui.theme.Black01
import com.example.hhtestus.ui.theme.Blue
import com.example.hhtestus.ui.theme.DarkGrey02
import com.example.hhtestus.ui.theme.Green
import com.example.hhtestus.ui.theme.Standart
import com.example.hhtestus.ui.theme.White01
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class mainContent {

    fun formateDate(inputDate: String): String {

        val date = org.threeten.bp.LocalDate.parse(inputDate)
        val formatter = org.threeten.bp.format
            .DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))

        return date.format(formatter)
    }

    fun formatVac(count: Int): String {

        return when {
            count % 10 == 1 && count % 100 != 11 -> "$count вакансия"
            count % 10 in 2..4 && (count % 100 !in 12..14) -> "$count вакансии"
            else -> "$count вакансий"
        }

    }

    @Composable
    fun mainView(viewModel: SharedViewModel, navController: NavController) {

        val listState = rememberLazyListState()
        val showButton = remember { mutableStateOf(false) }

        val vacancies by viewModel.vacancies.observeAsState(emptyList())

        LaunchedEffect(listState) {

            snapshotFlow {
                listState
                    .layoutInfo
                    .visibleItemsInfo
                    .lastOrNull()?.index == listState.layoutInfo.totalItemsCount - 1
            }
                .collect { isABottom ->
                    showButton.value = isABottom
                }
        }

        Scaffold(
            bottomBar = { downPanel(navController, viewModel) },
            containerColor = Black01
        ) { innerPadding ->

            Box(modifier = Modifier.fillMaxSize()) {

                LazyColumn(

                    state = listState,
                    modifier = Modifier
                        .padding(
                            start = 8.dp,
                            end = 16.dp,
                            bottom = innerPadding.calculateBottomPadding()
                        )
                        .fillMaxWidth()
                ) {

                    val vacToShow = if (vacancies.size >= 3) {
                        vacancies.take(3)
                    } else {
                        vacancies
                    }

                    items(vacToShow) { vac ->
                        vacList(vacancy = vac, viewModel, navController)
                    }

                    item {
                        Spacer(modifier = Modifier.height(60.dp))
                    }
                }

                if (showButton.value) {
                    Button(
                        onClick = { responseApi(viewModel,navController) },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(start = 15.dp, end = 15.dp, bottom = 108.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Blue,
                            contentColor = White01
                        )
                    ) {

                        Text(
                            text = "Еще ${formatVac(vacancies.size)}",
                            style = Standart)


                    }
                }
            }

        }

    }

    @Composable
    fun vacList(vacancy: Vacancy, viewModel: SharedViewModel, navController: NavController) {

        var isFavorite by remember { mutableStateOf(vacancy.isFavorite) }
        var showDialog by remember { mutableStateOf(false)}


            LaunchedEffect(isFavorite){

                isFavorite = vacancy.isFavorite

                viewModel.updateFav(
                    vacancy.copy(isFavorite = isFavorite),
                    isFavorite
                )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, start = 8.dp)
                .clickable {
                    viewModel.selectedVacancy = vacancy
                    navController.navigate("vacancyCard")
                },
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(DarkGrey02)
        ) {

            Column(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .fillMaxWidth(),
            ) {

                textForSearch(viewModel = viewModel, vacancy = vacancy)

                Button(
                    onClick = { showDialog = true },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Green,
                        contentColor = White01
                    )
                ) {

                    Text(text = "Откликнуться", style = Standart)

                }

                if (showDialog) {

                    responseBottomSheet(vacancy, onDismiss = { showDialog = false })

                }

            }

        }

    }
}
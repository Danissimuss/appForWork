package com.example.hhtestus.downPanel.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.hhtestus.Navigation.SharedViewModel
import com.example.hhtestus.R
import com.example.hhtestus.downPanel.ApiSide.Vacancy
import com.example.hhtestus.downPanel.downPanel
import com.example.hhtestus.ui.theme.Black01
import com.example.hhtestus.ui.theme.Blue
import com.example.hhtestus.ui.theme.DarkGrey02
import com.example.hhtestus.ui.theme.Green
import com.example.hhtestus.ui.theme.LightGrey01
import com.example.hhtestus.ui.theme.LightGrey02
import com.example.hhtestus.ui.theme.Standart
import com.example.hhtestus.ui.theme.White01
import kotlinx.coroutines.launch
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
            bottomBar = { downPanel(navController) },
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
                        .fillMaxWidth(),
                ) {

                    val vacToShow = if (vacancies.size >= 3) {
                        vacancies.take(3)
                    } else {
                        vacancies
                    }

                    items(vacToShow) { vac ->
                        vacList(vacancy = vac, viewModel)
                    }

                    item {
                        Spacer(modifier = Modifier.height(60.dp)) // Измените высоту по необходимости
                    }
                }

                if (showButton.value) {
                    Button(
                        onClick = { /*TODO*/ },
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
    fun vacList(vacancy: Vacancy, viewModel: SharedViewModel) {

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
                .padding(bottom = 16.dp, start = 8.dp),
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

                    responceBottomSheet(vacancy, onDismiss = { showDialog = false })

                }

            }

        }

    }
}
package com.example.hhtestus.downPanel.home.vacCard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.hhtestus.Navigation.SharedViewModel
import com.example.hhtestus.R
import com.example.hhtestus.downPanel.ApiSide.Vacancy
import com.example.hhtestus.downPanel.downPanel
import com.example.hhtestus.downPanel.favourite.clickableImage
import com.example.hhtestus.downPanel.home.responseBottomSheet
import com.example.hhtestus.imageBuilder.imageLoader
import com.example.hhtestus.textCorrector.textCorrection
import com.example.hhtestus.ui.theme.DarkGreen
import com.example.hhtestus.ui.theme.DarkGrey01
import com.example.hhtestus.ui.theme.DarkGrey02
import com.example.hhtestus.ui.theme.Green
import com.example.hhtestus.ui.theme.LightGrey02
import com.example.hhtestus.ui.theme.Standart
import com.example.hhtestus.ui.theme.White01

@Composable
fun VacancyCard(viewModel: SharedViewModel, vacancy: Vacancy, navController: NavController){

    val vacancies by viewModel.vacancies.observeAsState(emptyList())
    val currentVacancy = vacancies.find { it.id == vacancy.id } ?: vacancy
    var isFavorite by remember { mutableStateOf(currentVacancy.isFavorite) }
    var showDialog by remember { mutableStateOf(false)}

    LaunchedEffect(currentVacancy.isFavorite) {
        isFavorite = currentVacancy.isFavorite
    }

    val context = LocalContext.current
    val imageLoader = imageLoader(context = context)


    Column {


        Row(

            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 50.dp, bottom = 24.dp)

        ) {

            AsyncImage(

                model = ImageRequest.Builder(context)
                    .data("android.resource://${LocalContext.current.packageName}/raw/back")
                    .build(),

                contentDescription = "back Icon",
                imageLoader = imageLoader,

                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.popBackStack() },
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.weight(1f))

            AsyncImage(

                model = ImageRequest.Builder(context)
                    .data("android.resource://${LocalContext.current.packageName}/raw/eye")
                    .build(),

                contentDescription = "accordance Icon",
                imageLoader = imageLoader,

                modifier = Modifier
                    .size(28.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(16.dp))

            AsyncImage(

                model = ImageRequest.Builder(context)
                    .data("android.resource://${LocalContext.current.packageName}/raw/share")
                    .build(),

                contentDescription = "Share Icon",
                imageLoader = imageLoader,

                modifier = Modifier
                    .size(24.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(16.dp))

            val imageRes = if (isFavorite) R.raw.favourite_filled else R.raw.favourite

            clickableImage(
                context = context,
                imageRes = imageRes,
                isFavorite = isFavorite,
                imageLoader = imageLoader,
                viewModel = viewModel,
                vacancy = vacancy
            )

        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(

            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                .weight(1f)

        ) {

            item {

                Text(text = vacancy.title, style = Standart, fontSize = 22.sp,
                    modifier = Modifier.padding(bottom = 16.dp))
                
                Text(text = vacancy.salary.full, style = Standart,
                    modifier = Modifier.padding(bottom = 8.dp))

                Text(text = "Требуемый опыт: ${vacancy.experience.text}", style = Standart,
                    modifier = Modifier.padding(bottom = 16.dp))

                Text(text = vacancy.schedules.joinToString().replaceFirstChar{ char ->
                    if (char.isLowerCase()) char.titlecase() else char.toString()
                },
                    style = Standart,
                    modifier = Modifier.padding(bottom = 24.dp))

                upViewCard(vacancy = vacancy)

                    Spacer(modifier = Modifier.height(24.dp))

                    Card(

                        modifier = Modifier
                            .weight(1f),
                        colors = CardColors(
                            containerColor = DarkGrey01,
                            disabledContainerColor = DarkGrey01,
                            contentColor = White01,
                            disabledContentColor = White01
                        )

                    ) {

                        Column(

                            modifier = Modifier
                                .padding(top = 12.dp, bottom = 12.dp, start = 16.dp, end = 16.dp)

                        ) {

                            Row(

                                modifier = Modifier.padding(bottom = 8.dp)

                            ) {


                                Text(
                                    text = vacancy.company,
                                    style = Standart,
                                    modifier = Modifier.padding(end = 10.dp)
                                )

                                Box(modifier = Modifier.padding(top = 2.dp)) {
                                    AsyncImage(

                                        model = ImageRequest.Builder(context)
                                            .data(
                                                "android.resource://${LocalContext.current
                                                    .packageName}/raw/valid"
                                            )
                                            .build(),

                                        contentDescription = "valid Icon",
                                        imageLoader = imageLoader,

                                        modifier = Modifier
                                            .size(16.dp),
                                        contentScale = ContentScale.Fit
                                    )
                                }

                            }

                            Box(

                                modifier = Modifier.padding(bottom = 8.dp)

                            ) {
                                AsyncImage(

                                    model = ImageRequest.Builder(context)
                                        .data(
                                            "android.resource://${
                                                LocalContext.current
                                                    .packageName
                                            }/raw/map"
                                        )
                                        .build(),

                                    contentDescription = "map Icon",
                                    imageLoader = imageLoader,

                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    contentScale = ContentScale.Fit
                                )
                            }

                            Text(text = "${vacancy.address.town}, " +
                                    "${vacancy.address.street}, " +
                                    "${vacancy.address.house}")

                        }
                        
                    }
                
                Box(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)){

                    val formDesc = vacancy.description?.replace("\n", "\n")

                Text(text = "$formDesc", style = Standart)

                }
                
                Text(text = "Ваши задачи", style = Standart, fontSize = 24.sp)
                
                Box(modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)){

                    val formResp = vacancy.responsibilities?.replace("\n", "\n")

                    Text(text = "$formResp", style = Standart, fontSize = 14.sp)

                }

                Box(modifier = Modifier.padding(bottom = 8.dp)) {
                    Text(text = "Задайте вопрос работодателю", style = Standart, fontSize = 14.sp)
                }

                Box(modifier = Modifier.padding(bottom = 16.dp)) {
                    Text(text = "Он получит его с откликом на вакансию",
                        style = Standart, fontSize = 14.sp, color = LightGrey02)
                }

                Card(

                    modifier = Modifier.padding(bottom = 8.dp),
                    colors = CardColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Transparent,
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent
                    )

                ) {

                    vacancy.questions.forEach { question ->

                        Surface(

                            shape = RoundedCornerShape(16.dp),
                            color = DarkGrey01,
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                                .clickable { showDialog = true }

                        ) {
                            
                            Box(
                                modifier = Modifier.padding(
                                    start = 12.dp,
                                    end = 12.dp,
                                    top = 8.dp,
                                    bottom = 8.dp
                                )
                            ) {
                                Text(text = question, color = White01)
                            }

                        }

                    }

                }
                
            }

        }

        Button(
            onClick = { showDialog = true },
            modifier = Modifier
                .padding(end = 16.dp, start = 16.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Green,
                contentColor = White01
            ),
            shape = RoundedCornerShape(8.dp)
        ) {

            Text(text = "Откликнуться", style = Standart)

        }

        if (showDialog) {

            responseBottomSheet(vacancy, onDismiss = { showDialog = false })

        }

        Spacer(modifier = Modifier.height(16.dp))

        downPanel(navController = navController, viewModel = viewModel)

    }
}
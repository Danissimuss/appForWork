package com.example.hhtestus.downPanel.home.vacCard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.hhtestus.Navigation.SharedViewModel
import com.example.hhtestus.R
import com.example.hhtestus.downPanel.ApiSide.Vacancy
import com.example.hhtestus.imageBuilder.imageLoader
import com.example.hhtestus.ui.theme.Standart

@Composable
fun vacancyCard(viewModel: SharedViewModel, vacancy: Vacancy, navController: NavController){

    val vacancies by viewModel.vacancies.observeAsState(emptyList())
    val currentVacancy = vacancies.find { it.id == vacancy.id } ?: vacancy
    var isFavorite by remember { mutableStateOf(currentVacancy.isFavorite) }

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

            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data("android.resource://${context.packageName}/raw/$imageRes")
                    .build(),
                contentDescription = if (isFavorite) "Fav" else "NotFav",
                imageLoader = imageLoader,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        viewModel.updateFav(
                            vacancy.copy(isFavorite = !isFavorite),
                            !isFavorite
                        )
                    }
            )

        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(

            modifier = Modifier.padding(start = 16.dp, end = 16.dp)

        ) {

            item {

                Text(text = vacancy.title, style = Standart)

            }

        }
    }
}
package com.example.hhtestus.downPanel.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.hhtestus.Navigation.SharedViewModel
import com.example.hhtestus.R
import com.example.hhtestus.downPanel.ApiSide.Vacancy
import com.example.hhtestus.ui.theme.Green
import com.example.hhtestus.ui.theme.LightGrey02
import com.example.hhtestus.ui.theme.Standart

@Composable
fun textForSearch(viewModel: SharedViewModel, vacancy: Vacancy){

    val isFavorite = vacancy.isFavorite

    val watchers = vacancy.lookingNumber
    val context = LocalContext.current

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
    val imageHolder = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()


    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = annotatedText,
            style = Standart,
            fontSize = 12.sp
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 300.dp)
        ) {

            val imageRes = if (isFavorite) R.raw.favourite_filled else R.raw.favourite

            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data("android.resource://${context.packageName}/raw/$imageRes")
                    .build(),
                contentDescription = if (isFavorite) "Fav" else "NotFav",
                imageLoader = imageHolder,
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
    }

    Text(
        text = vacancy.title,
        modifier = Modifier.padding(top = 3.dp, end = 16.dp),
        style = Standart
    )

    vacancy.salary.short?.let {
        Text(
            text = it,
            modifier = Modifier.padding(top = 10.dp), style = Standart, fontSize = 20.sp
        )
    }

    Text(
        text = vacancy.address.town,
        modifier = Modifier.padding(top = 10.dp),
        style = Standart,
        fontSize = 14.sp
    )

    Row (modifier = Modifier.padding(top = 5.dp)) {

        Text(
            text = vacancy.company,
            modifier = Modifier.padding(top = 4.dp, end = 6.dp),
            style = Standart,
            fontSize = 14.sp
        )

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(
                    "android.resource://${context.packageName}" +
                            "/raw/valid"
                )
                .build(),
            contentDescription = "Valid",
            imageLoader = imageHolder,
            modifier = Modifier
                .size(18.dp)
                .padding(end = 6.dp, top = 6.dp)
        )

    }

    Row(
        modifier = Modifier.padding(top = 5.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(
                    "android.resource://${context.packageName}" +
                            "/raw/work"
                )
                .build(),
            contentDescription = "Work",
            imageLoader = imageHolder,
            modifier = Modifier
                .size(20.dp)
                .padding(end = 6.dp, top = 6.dp)
        )

        Text(
            text = vacancy.experience.previewText,
            modifier = Modifier.padding(top = 5.dp),
            style = Standart,
            fontSize = 14.sp
        )
    }
    Text(
        text = "Опубликовано: " + mainContent().formateDate(vacancy.publishedDate),
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
        style = Standart,
        color = LightGrey02,
        fontSize = 14.sp
    )

}
package com.example.hhtestus.downPanel.home.vacCard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.hhtestus.downPanel.ApiSide.Vacancy
import com.example.hhtestus.imageBuilder.imageLoader
import com.example.hhtestus.textCorrector.textCorrection
import com.example.hhtestus.ui.theme.DarkGreen
import com.example.hhtestus.ui.theme.Standart
import com.example.hhtestus.ui.theme.White01

@Composable
fun upViewCard(vacancy: Vacancy){

    val context = LocalContext.current
    val imageLoader = imageLoader(context = context)

    val appliedText = textCorrection(vacancy.appliedNumber, White01)
    val watchers = textCorrection(vacancy.lookingNumber, White01)

    Row {

        if (vacancy.appliedNumber != 0) {
            Card(

                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(1f),
                colors = CardColors(
                    containerColor = DarkGreen,
                    disabledContainerColor = DarkGreen,
                    contentColor = White01,
                    disabledContentColor = White01
                )

            ) {

                Row(

                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
                ) {

                    Box {
                        Text(
                            text = "$appliedText уже откликнулись", style = Standart,
                            fontSize = 14.sp
                        )

                        AsyncImage(

                            model = ImageRequest.Builder(context)
                                .data(
                                    "android.resource://${LocalContext.current.packageName}/raw/greenprofile"
                                )
                                .build(),

                            contentDescription = "applied Icon",
                            imageLoader = imageLoader,

                            modifier = Modifier
                                .size(16.dp)
                                .align(Alignment.TopEnd),
                            contentScale = ContentScale.Fit
                        )

                    }
                }

            }
        }

        Card(

            modifier = Modifier
                .weight(1f),
            colors = CardColors(
                containerColor = DarkGreen,
                disabledContainerColor = DarkGreen,
                contentColor = White01,
                disabledContentColor = White01
            )

        ) {

            Row(

                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
            ) {

                Box {
                    Text(
                        text = "$watchers сейчас смотрят", style = Standart,
                        fontSize = 14.sp
                    )

                    AsyncImage(

                        model = ImageRequest.Builder(context)
                            .data(
                                "android.resource://${LocalContext.current.packageName}/raw/greeneye"
                            )
                            .build(),

                        contentDescription = "watchers Icon",
                        imageLoader = imageLoader,

                        modifier = Modifier
                            .size(16.dp)
                            .align(Alignment.TopEnd),
                        contentScale = ContentScale.Fit
                    )
                }
            }

        }
    }
}
package com.example.hhtestus.downPanel.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.hhtestus.ui.theme.DarkGrey01
import com.example.hhtestus.ui.theme.DarkGrey02

@Preview
@Composable
fun Search() {

    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    Row(
        modifier = Modifier
            .padding(top = 38.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .background(DarkGrey02, shape = RoundedCornerShape(8.dp))
                .weight(1f)
                .height(40.dp)

        ) {

            AsyncImage(

                model = ImageRequest.Builder(context)
                    .data("android.resource://${context.packageName}/raw/search") // Замените на ваш ресурс
                    .build(),

                contentDescription = "Search Icon",
                imageLoader = imageLoader,

                modifier = Modifier
                    .size(width = 24.dp, height = 24.dp)
                    .align(Alignment.CenterStart)
                    .padding(start = 6.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "Должность, ключевые слова",
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 32.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Box {
            AsyncImage(

                model = ImageRequest.Builder(context)
                    .data("android.resource://${context.packageName}/raw/filter") // Замените на ваш ресурс
                    .build(),

                contentDescription = "Email Icon",
                imageLoader = imageLoader,

                modifier = Modifier
                    .size(width = 40.dp, height = 40.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
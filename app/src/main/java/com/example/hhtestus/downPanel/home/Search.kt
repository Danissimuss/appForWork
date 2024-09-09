package com.example.hhtestus.downPanel.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.hhtestus.imageBuilder.imageLoader
import com.example.hhtestus.ui.theme.DarkGrey02

@Composable
fun Search(
    navController: NavController,
    searchText: String = "Должность, ключевые слова",
    searchIcon: String = "android.resource://${LocalContext.current.packageName}/raw/search"
) {

    val context = LocalContext.current
    val imageLoader = imageLoader(context = context)

    val backIcon = "android.resource://${LocalContext.current.packageName}/raw/back"

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
                    .data(searchIcon)
                    .build(),

                contentDescription = "Search Icon",
                imageLoader = imageLoader,

                modifier = Modifier
                    .size(width = 24.dp, height = 24.dp)
                    .align(Alignment.CenterStart)
                    .padding(start = 6.dp)
                    .clickable {

                        if (searchIcon == backIcon) {
                            navController.popBackStack()
                        }

                    },
                contentScale = ContentScale.Fit
            )

            Text(
                text = searchText,
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
                    .data("android.resource://${LocalContext.current.packageName}/raw/filter")
                    .build(),

                contentDescription = "Filter Icon",
                imageLoader = imageLoader,

                modifier = Modifier
                    .size(width = 40.dp, height = 40.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
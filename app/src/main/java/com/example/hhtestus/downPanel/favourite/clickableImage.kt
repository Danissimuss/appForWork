package com.example.hhtestus.downPanel.favourite

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.hhtestus.Navigation.SharedViewModel
import com.example.hhtestus.downPanel.ApiSide.Vacancy

@Composable
fun clickableImage (context: Context,
                    imageRes:Int,
                    isFavorite: Boolean,
                    imageLoader: ImageLoader,
                    viewModel: SharedViewModel,
                    vacancy: Vacancy
){

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
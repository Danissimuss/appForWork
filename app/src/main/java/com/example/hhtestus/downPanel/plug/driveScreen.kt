package com.example.hhtestus.downPanel.plug

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.hhtestus.Navigation.SharedViewModel
import com.example.hhtestus.downPanel.downPanel
import com.example.hhtestus.imageBuilder.imageLoader
import com.example.hhtestus.ui.theme.Standart

@Composable
fun driveScreen(viewModel: SharedViewModel, navController:NavController){

    val context = LocalContext.current
    val imageHolder = imageLoader(context = context)

    Column(modifier = Modifier.fillMaxSize()) {

        Text(text = "Let's Drive...", style = Standart, fontSize = 40.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 350.dp, bottom = 100.dp))

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(
                    "android.resource://${context.packageName}" +
                            "/raw/rayangosling"
                )
                .build(),
            contentDescription = "Valid",
            imageLoader = imageHolder,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.weight(1f))

        downPanel(navController = navController, viewModel = viewModel)
    }

}
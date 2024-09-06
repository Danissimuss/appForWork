package com.example.hhtestus.downPanel.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.hhtestus.downPanel.ApiSide.Vacancy
import com.example.hhtestus.ui.theme.DarkGrey02
import com.example.hhtestus.ui.theme.Green
import com.example.hhtestus.ui.theme.LightGrey01
import com.example.hhtestus.ui.theme.LightGrey02
import com.example.hhtestus.ui.theme.Standart
import com.example.hhtestus.ui.theme.White01
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun responceBottomSheet(vacTitle: Vacancy, onDismiss: () -> Unit) {

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    LaunchedEffect(sheetState) {
        if (sheetState.isVisible) {
            sheetState.hide()
        }
    }

    ModalBottomSheet(
        sheetState = sheetState,
        containerColor = DarkGrey02,
        onDismissRequest = {
            coroutineScope.launch {
                sheetState.hide()
                onDismiss()
            }
        },
        modifier = Modifier.height(324.dp) // Установка максимальной высоты
    ) {
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)) {

            Row {

                AsyncImage(

                    model = ImageRequest.Builder(context)
                        .data("android.resource://${context.packageName}/raw/profphot") // Замените на ваш ресурс
                        .build(),

                    contentDescription = "LazyRow",
                    imageLoader = imageLoader,

                    modifier = Modifier
                        .size(width = 48.dp, height = 48.dp)
                )

                Column {

                    Text("Резюме для отклика", style = Standart, color = LightGrey02,
                        modifier = Modifier.padding(start = 8.dp,bottom = 8.dp))

                    Text(vacTitle.title, style = Standart, fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 16.dp, start = 8.dp))
                }
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                color = LightGrey01
            )

            Text(
                text = "Добавить сопроводительное",
                style = Standart,
                fontSize = 18.sp,
                color = Green,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 32.dp, bottom = 20.dp)
            )

            Button(onClick = {
                coroutineScope.launch {
                    sheetState.hide()
                    onDismiss()
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
                shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = Green,
                    contentColor = White01
                )) {
                Text("Откликнуться", style = Standart, color = White01, fontSize = 18.sp)
            }
        }
    }
}
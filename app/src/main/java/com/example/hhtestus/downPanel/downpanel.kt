package com.example.hhtestus.downPanel

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.hhtestus.ui.theme.Blue
import com.example.hhtestus.ui.theme.DarkGrey02

@Preview
@Composable
fun downPanel() {

    var selectedItem by remember { mutableStateOf(0) }

    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    Row {

        NavigationBar(
            containerColor = DarkGrey02,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .align(Alignment.Bottom)
        ) {

            val icons = listOf("search", "favourite", "response", "message", "profile")

            val iconsSelected = listOf("search_selected", "favourite", "response_selected",
                "message", "profile_selected")

            val descriptions = listOf("Поиск", "Избранное", "Отклики", "Сообщения", "Профиль")

            icons.forEachIndexed { index, icon ->
                NavigationBarItem(
                    icon = {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data("android.resource://${context.packageName}" +
                                        "/raw/${if (selectedItem == index) iconsSelected[index]
                                        else icon}")
                                .build(),
                            contentDescription = descriptions[index],
                            imageLoader = imageLoader,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    selected = selectedItem == index,
                    onClick = { selectedItem = index },
                    label = {Text(text = descriptions[index],
                        fontSize = 8.sp)},
                    modifier = Modifier.padding(top = 18.dp),
                    colors = NavigationBarItemDefaults.colors(
                        unselectedIconColor = Color.White,
                        selectedIconColor = Color.Blue,
                        unselectedTextColor = Color.White,
                        selectedTextColor = Blue,
                        indicatorColor = Color.Transparent
                    ),
                )
            }
        }
    }

}
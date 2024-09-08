package com.example.hhtestus.downPanel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.hhtestus.Navigation.SharedViewModel
import com.example.hhtestus.imageBuilder.imageLoader
import com.example.hhtestus.ui.theme.Blue
import com.example.hhtestus.ui.theme.DarkGrey02
import com.example.hhtestus.ui.theme.Standart
import com.example.hhtestus.ui.theme.White01
import com.example.hhtestus.ui.theme.panel

@Composable
fun downPanel(navController: NavController, viewModel: SharedViewModel) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val favourites by viewModel.getFavVac().observeAsState(emptyList())

    val favouritesCount = favourites.size

    val context = LocalContext.current
    val imageLoader = imageLoader(context = context)

    val isStartScreen = currentRoute == "greeting"

    Row {

        NavigationBar(
            containerColor = DarkGrey02,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .align(Alignment.Bottom)
        ) {

            val items = listOf(
                BottomNavItems.Home,
                BottomNavItems.Fav,
                BottomNavItems.Responce,
                BottomNavItems.Message,
                BottomNavItems.Profile
            )

            items.forEachIndexed { _, item ->

                val iconRes = if (currentRoute == item.route) {
                    item.iconSelected
                } else if (currentRoute == "allVac" && item is BottomNavItems.Home) {
                    BottomNavItems.Home.iconSelected
                } else {
                    item.icon
                }

                NavigationBarItem(
                    icon = {
                        Box {
                            AsyncImage(
                                model = ImageRequest.Builder(context)
                                    .data("android.resource://${context.packageName}/raw/$iconRes")
                                    .build(),
                                contentDescription = item.label,
                                imageLoader = imageLoader,
                                modifier = Modifier.size(24.dp)
                            )

                            if (item == BottomNavItems.Fav && favouritesCount > 0) {
                                Box (modifier = Modifier.padding(start = 14.dp)) {
                                    AsyncImage(
                                        model = ImageRequest.Builder(context)
                                            .data(
                                                "android.resource://${context.packageName}/raw/" +
                                                        "bubble"
                                            )
                                            .build(),
                                        contentDescription = item.label,
                                        imageLoader = imageLoader,
                                        modifier = Modifier
                                            .size(13.dp)
                                    )

                                    Text(
                                        text = favouritesCount.toString(),
                                        style = Standart,
                                        fontSize = 10.sp,
                                        modifier = Modifier.padding(start = 4.dp)
                                    )
                                }
                            }
                        }
                    },
                    selected = currentRoute == item.route,
                    onClick = {
                        if (!isStartScreen){
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                        }

                              },
                    label = {Text(text = item.label,
                        fontSize = 12.sp, style = panel)},
                    modifier = Modifier.padding(top = 18.dp),
                    colors = NavigationBarItemDefaults.colors(
                        unselectedIconColor = White01,
                        selectedIconColor = Blue,
                        unselectedTextColor = White01,
                        selectedTextColor = Blue,
                        indicatorColor = Color.Transparent
                    ),
                )
            }
        }
    }
}
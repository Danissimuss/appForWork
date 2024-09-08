package com.example.hhtestus.downPanel.home.allVac

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.hhtestus.Navigation.SharedViewModel
import com.example.hhtestus.downPanel.downPanel
import com.example.hhtestus.downPanel.home.Search
import com.example.hhtestus.downPanel.home.mainContent
import com.example.hhtestus.imageBuilder.imageLoader
import com.example.hhtestus.ui.theme.Black01
import com.example.hhtestus.ui.theme.Blue
import com.example.hhtestus.ui.theme.DarkGrey01
import com.example.hhtestus.ui.theme.Standart

@Composable
fun allVac(navController: NavController, viewModel: SharedViewModel){

    val backIcon = "android.resource://${LocalContext.current.packageName}/raw/back"
    val mainContent = mainContent()
    val vacancies by viewModel.vacancies.observeAsState(emptyList())
    val listState = rememberLazyListState()

    val formatVac = viewModel.vacancies.value?.size?.let { mainContent.formatVac(it) } ?: "Вакансий не обнаружено"

    val context = LocalContext.current
    val imageLoader = imageLoader(context = context)

    val showIcon = remember {
        derivedStateOf {
            listState.firstVisibleItemIndex >= 1
        }
    }

    Column(
        
        modifier = Modifier.fillMaxSize()
        
    ) {

    Search(searchIcon = backIcon, searchText = "Должность по подходящим вакан...",
        navController = navController)

        Row(

            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 25.dp)
                .fillMaxWidth()

        ) {
            Text(text = formatVac,
                style = Standart)
            
            Spacer(modifier = Modifier.weight(1f))
            
            Text(text = "По соответствию", style = Standart, color = Blue,
                modifier = Modifier
                    .padding(end = 6.dp)
                    .align(Alignment.CenterVertically))

            Box(modifier = Modifier
                .padding(top = 3.dp, end = 16.dp)) {
                AsyncImage(

                    model = ImageRequest.Builder(context)
                        .data("android.resource://${LocalContext.current.packageName}/raw/accordance")
                        .build(),

                    contentDescription = "accordance Icon",
                    imageLoader = imageLoader,

                    modifier = Modifier
                        .size(16.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }

        Scaffold(
            bottomBar = { downPanel(navController, viewModel) },
            containerColor = Black01
        ) { innerPadding ->

            Box(modifier = Modifier.fillMaxSize()) {

                LazyColumn(

                    state = listState,
                    modifier = Modifier
                        .padding(
                            start = 8.dp,
                            end = 16.dp,
                            bottom = innerPadding.calculateBottomPadding()
                        )
                        .fillMaxWidth()
                ) {

                    items(vacancies) { vac ->
                        mainContent.vacList(vacancy = vac, viewModel, navController)
                    }

                }
            }

            if (showIcon.value) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = innerPadding.calculateBottomPadding())
                ) {

                    Surface(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                            .align(Alignment.BottomEnd),
                        shape = RoundedCornerShape(18.dp),
                        color = DarkGrey01
                    ) {

                        Box {

                            Row(modifier = Modifier.padding(12.dp)) {

                                AsyncImage(

                                    model = ImageRequest.Builder(context)
                                        .data(
                                            "android.resource://${
                                                LocalContext
                                                    .current.packageName
                                            }/raw/minimap"
                                        )
                                        .build(),

                                    contentDescription = "minimap Icon",
                                    imageLoader = imageLoader,

                                    modifier = Modifier
                                        .size(24.dp),
                                    contentScale = ContentScale.Fit
                                )

                                Spacer(modifier = Modifier.padding(8.dp))

                                Text(
                                    text = "Карта", style = Standart,
                                    modifier = Modifier.padding(top = 2.dp)
                                )


                            }

                        }
                    }
                }
            }
        }

    }
}
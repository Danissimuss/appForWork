package com.example.hhtestus.downPanel.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hhtestus.Navigation.SharedViewModel

@Composable
fun offersList(viewModel: SharedViewModel){

    val offers by viewModel.offers.observeAsState(emptyList())

    LazyRow(
        modifier = Modifier
            .padding(top = 16.dp, start = 8.dp, end = 16.dp, bottom = 32.dp)
            .fillMaxWidth(),

        ) {
        items(offers) { offer ->
            OfferCard(offer = offer)
        }
    }

}
package com.example.hhtestus.downPanel.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.hhtestus.downPanel.ApiSide.Offer
import com.example.hhtestus.imageBuilder.imageLoader
import com.example.hhtestus.ui.theme.DarkGrey02
import com.example.hhtestus.ui.theme.Green
import com.example.hhtestus.ui.theme.White01

@Composable
fun OfferCard(offer: Offer) {

    val context = LocalContext.current

    val imageLoader = imageLoader(context = context)

    val annotatedText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Green, textDecoration = TextDecoration.Underline)) {
            append("Поднять")
        }
    }

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("${offer.link}"))

    Card(
        modifier = Modifier
            .padding(start = 8.dp)
            .clickable { context.startActivity(intent) },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(DarkGrey02)
    ) {
        Column(
            modifier = Modifier
                .padding(end = 8.dp)
                .width(132.dp)
                .height(120.dp),
        ) {

            AsyncImage(

                model = ImageRequest.Builder(context)
                    .data("android.resource://${context.packageName}/raw/${offer.id}") // Замените на ваш ресурс
                    .build(),

                contentDescription = "LazyRow",
                imageLoader = imageLoader,

                modifier = Modifier
                    .size(width = 42.dp, height = 42.dp)
                    .fillMaxSize()
                    .padding(top = 10.dp, start = 4.dp)
            )




            Text(text = offer.title.trim(), color = White01, fontSize = 14.sp,
                modifier = Modifier.padding(top = 16.dp, start = 8.dp))
            
            if (offer.id == "level_up_resume"){
                ClickableText(text = annotatedText, modifier = Modifier.padding(
                    top = 4.dp,
                    start = 8.dp),
                    onClick = {
                        context.startActivity(intent)
                    })

            }

        }
    }
}
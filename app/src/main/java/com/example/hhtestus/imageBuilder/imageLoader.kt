package com.example.hhtestus.imageBuilder

import android.content.Context
import androidx.compose.runtime.Composable
import coil.ImageLoader
import coil.decode.SvgDecoder

@Composable
fun imageLoader (context: Context): ImageLoader {

    return ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

}
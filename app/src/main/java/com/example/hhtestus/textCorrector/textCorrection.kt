package com.example.hhtestus.textCorrector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

fun textCorrection(watchers: Int, color: Color): AnnotatedString {

    val annotatedText = buildAnnotatedString {
        withStyle(SpanStyle(color = color)){
            val ending = when{

                watchers % 100 in 11 .. 14 -> "человек"
                watchers % 10 in 2 .. 4 -> "человека"
                else -> "человек"
            }
            append("$watchers $ending")
        }
    }
    return annotatedText
}
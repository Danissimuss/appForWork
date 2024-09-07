package com.example.hhtestus.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.hhtestus.R


val Standart = TextStyle(
    fontFamily = FontFamily(Font(R.font.sf_pro_display)),
    fontWeight = FontWeight.Normal,
    color = White01,
    fontSize = 16.sp
)
val input = TextStyle(
    fontFamily = FontFamily(Font(R.font.sf_pro_display)),
    fontWeight = FontWeight.Normal,
    color = White01,
    fontSize = 18.sp
)
val panel = TextStyle(
    fontFamily = FontFamily(Font(R.font.sf_pro_display)),
    fontWeight = FontWeight.Normal,
    textAlign = TextAlign.Center,
    fontSize = 16.sp
)
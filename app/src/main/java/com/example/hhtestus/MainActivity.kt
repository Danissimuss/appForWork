package com.example.hhtestus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.hhtestus.Navigation.appNavigation
import com.example.hhtestus.ui.theme.HHtestusTheme
import com.jakewharton.threetenabp.AndroidThreeTen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidThreeTen.init(this)
        enableEdgeToEdge()

        setContent {
                HHtestusTheme {
                    appNavigation()
                }
        }
    }
}

